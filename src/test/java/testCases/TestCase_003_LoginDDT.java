package testCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TestCase_003_LoginDDT extends BaseClass {

    @Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "DataDriven")
    public void verify_loginDDT(String username, String pwd, String exp) throws InterruptedException {

        logger.info("***** Starting Data Driven Login Test *****");

        driver.get("https://parabank.parasoft.com/parabank/index.htm");

        LoginPage lp = new LoginPage(driver);
        lp.setUsername(username);
        lp.setPassword(pwd);
        lp.clickLoginButton();

        Thread.sleep(2000);

        boolean loginSuccess;

        try {
            loginSuccess = driver.findElement(By.linkText("Log Out")).isDisplayed();
        }
        catch (Exception e) {
            loginSuccess = false;
        }

        // ============================
        // DECISION LOGIC
        // ============================

        if (exp.equalsIgnoreCase("Valid")) {

            if (loginSuccess) {
                logger.info("VALID data → Login SUCCESS → Test PASS");
                driver.findElement(By.linkText("Log Out")).click();
                Assert.assertTrue(true);
            } else {
                logger.error("VALID data → Login FAILED → Test FAIL");
                Assert.fail();
            }

        } else if (exp.equalsIgnoreCase("Invalid")) {

            if (!loginSuccess) {
                logger.info("INVALID data → Login FAILED → Test PASS");
                Assert.assertTrue(true);
            } else {
                logger.error("INVALID data → Login SUCCESS → Test FAIL");
                driver.findElement(By.linkText("Log Out")).click();
                Assert.fail();
            }
        }

        logger.info("***** Finished Data Driven Login Test *****");
    }
}
