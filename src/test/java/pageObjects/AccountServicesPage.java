package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountServicesPage extends BasePage {

    public AccountServicesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//b[contains(text(),'Welcome')]")
    WebElement welcomeMessage;

    @FindBy(linkText = "Log Out")
    WebElement lnkLogout;

    public String getWelcomeMessage() {
        return welcomeMessage.getText();
    }

    public void clickLogout() {
        lnkLogout.click();
    }
}
