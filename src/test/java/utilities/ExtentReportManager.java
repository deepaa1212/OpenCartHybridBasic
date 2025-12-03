package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {

    private static ExtentReports extent;
    private static ExtentTest test;
    private static String reportPath;   // To auto-open report

    public static ExtentReports createInstance(String filePath, ITestContext context) {

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String repName = "TestReport-" + timeStamp + ".html";

        // Create reports folder if not exist
        String reportsDir = System.getProperty("user.dir") + "/reports/";
        File folder = new File(reportsDir);
        if (!folder.exists()) folder.mkdir();

        reportPath = reportsDir + repName;  // Full path for auto-open

        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);

        reporter.config().setDocumentTitle("Hybrid Automation Report");
        reporter.config().setReportName("Hybrid Test Execution");
        reporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(reporter);

        // SYSTEM INFO
        extent.setSystemInfo("Application", "OpenCart");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User", System.getProperty("user.name"));
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("Browser", context.getCurrentXmlTest().getParameter("browser"));

        return extent;
    }

    @Override
    public void onStart(ITestContext context) {
        createInstance("Report.html", context);
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();

        // AUTO OPEN REPORT AFTER EXECUTION
        try {
            Desktop.getDesktop().browse(new File(reportPath).toURI());
        } catch (Exception e) {
            System.out.println("Unable to open Extent report automatically.");
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, result.getMethod().getMethodName() + " PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, result.getThrowable().getMessage());

        // ADD SCREENSHOT
        try {
            BaseClass base = (BaseClass) result.getInstance();
            String screenshotPath = base.captureScreenshot(result.getName());
            test.addScreenCaptureFromPath(screenshotPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, result.getMethod().getMethodName() + " SKIPPED");
    }
}
