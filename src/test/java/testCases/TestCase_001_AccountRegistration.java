package testCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBase.BaseClass;

import java.util.UUID;

public class TestCase_001_AccountRegistration extends BaseClass {

    @Test(groups={"Regression", "Master" })
    public void verify_account_registration() {
        logger.info("*****Starting Verify Account Registration****");
     try {
         driver.get("https://parabank.parasoft.com/parabank/register.htm");

         RegistrationPage reg = new RegistrationPage(driver);
         reg.setFirstName(randomString().toUpperCase());
         logger.info("Entered the First Name");
         reg.setLastName(randomString().toUpperCase());
         logger.info("Entered the Last Name");
         reg.setAddress("Street" + randomNumber());
         logger.info("Entered the Address");
         reg.setCity("Gurgaon");
         logger.info("Entered the City");
         reg.setState("Haryana");
         logger.info("Entered the State");
         reg.setZipcode("randomNumber");
         logger.info("Entered the Zip Code");
         reg.setPhone("randomNumber");
         logger.info("Entered the Phone Number");
         reg.setSSN("randomNumber");
         logger.info("Entered the SSN");


         reg.setUsername(randomString() + randomNumber());
         String password = randomAlphaNumeric();

         reg.setPassword(password);
         reg.setConfirmPassword(password);

         reg.clickRegisterButton();
         Thread.sleep(2000);

         String msg = reg.getSuccessMessage();
         if(msg.equals("Your account was created successfully. You are now logged in."))
         {
             Assert.assertTrue(true);
         }
         else {
             logger.error("Test Failed");
             logger.debug("Debug Logs");
             Assert.assertTrue(false);
         }



        // Assert.assertTrue(msg.contains("created successfully"), "Registration Failed!");
     }
     catch (Exception e) {

         Assert.fail();
     }
        logger.info("Finished Verify Account Registration");

        }

}
