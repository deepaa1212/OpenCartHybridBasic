package pageObjects;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {

    WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // --- Registration Form Fields ---
    @FindBy(id = "customer.firstName")
    WebElement txtFirstName;

    @FindBy(id = "customer.lastName")
    WebElement txtLastName;

    @FindBy(id = "customer.address.street")
    WebElement txtAddress;

    @FindBy(id = "customer.address.city")
    WebElement txtCity;

    @FindBy(id = "customer.address.state")
    WebElement txtState;

    @FindBy(id = "customer.address.zipCode")
    WebElement txtZipcode;

    @FindBy(id = "customer.phoneNumber")
    WebElement txtPhone;

    @FindBy(id = "customer.ssn")
    WebElement txtSSN;

    @FindBy(id = "customer.username")
    WebElement txtUsername;

    @FindBy(id = "customer.password")
    WebElement txtPassword;

    @FindBy(id = "repeatedPassword")
    WebElement txtConfirmPassword;

    @FindBy(xpath = "//input[@value='Register']")
    WebElement btnRegister;

    // --- Success Message ---
    @FindBy(xpath = "//p[contains(text(),'Your account was created successfully')]")
    WebElement successMsg;


    // --- Action Methods ---
    public void setFirstName(String fname) {
        txtFirstName.sendKeys(fname);
    }

    public void setLastName(String lname) {
        txtLastName.sendKeys(lname);
    }

    public void setAddress(String addr) {
        txtAddress.sendKeys(addr);
    }

    public void setCity(String city) {
        txtCity.sendKeys(city);
    }

    public void setState(String state) {
        txtState.sendKeys(state);
    }

    public void setZipcode(String zip) {
        txtZipcode.sendKeys(zip);
    }

    public void setPhone(String phone) {
        txtPhone.sendKeys(phone);
    }

    public void setSSN(String ssn) {
        txtSSN.sendKeys(ssn);
    }

    public void setUsername(String user) {
        txtUsername.sendKeys(user);
    }

    public void setPassword(String pwd) {
        txtPassword.sendKeys(pwd);
    }

    public void setConfirmPassword(String pwd) {
        txtConfirmPassword.sendKeys(pwd);
    }

    public void clickRegisterButton() {
        btnRegister.click();
    }

    public String getSuccessMessage() {
        return successMsg.getText();
    }



}
