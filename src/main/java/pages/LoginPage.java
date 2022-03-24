package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class LoginPage extends TestBase {
    By userNameLocator = By.id("emailAddress");
    By passwordLocator = By.id("password");
    By signIn = By.xpath("//button/span[1]/h6");
    By alertMessage = By.xpath("//div[@class='MuiAlert-message']");
    String MESSAGE = "Failed to sign in - incorrect details provided, try again.";



    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
       // wait = new WebDriverWait(driver,5,5);
    }
    @Step("Login to the application")
    public void login(String userName,String password) {

        driver.findElement(userNameLocator).sendKeys(userName);
        driver.findElement(passwordLocator).sendKeys(password);
        driver.findElement(signIn).click();
    }

    @Step("Verify the alert message")
    public void verifyAlertMessage() throws InterruptedException {
        Assert.assertTrue(driver.findElement(alertMessage).getText().toUpperCase().contains(MESSAGE.toUpperCase()));
        Thread.sleep(3000);
    }

}
