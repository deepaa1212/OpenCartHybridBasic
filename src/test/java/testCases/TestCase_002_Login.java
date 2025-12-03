package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountServicesPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

import java.time.Duration;

public class TestCase_002_Login extends BaseClass {

    @Test(groups={"Sanity", "Master" })
    public void verify_login_test() {

        logger.info("***** Starting Login Test *****");

        // ALWAYS USE index.htm (correct login page)
        driver.get("https://parabank.parasoft.com/parabank/index.htm");

        LoginPage lp = new LoginPage(driver);

        lp.setUsername(p.getProperty("username"));
        lp.setPassword(p.getProperty("password"));
        lp.clickLogin();

        logger.info("Credentials entered");

        AccountServicesPage asp = new AccountServicesPage(driver);

        String msg = asp.getWelcomeMessage();
        Assert.assertTrue(msg.contains("Welcome"), "Login Failed!");

        logger.info("Welcome message verified");

        asp.clickLogout();

        logger.info("***** Login Test Passed *****");
    }



}

