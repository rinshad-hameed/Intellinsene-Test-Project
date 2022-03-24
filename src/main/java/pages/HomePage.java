package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.List;

public class HomePage  extends TestBase{
    By dashboardIcon = By.xpath("(//li/a/i[@title='Dashboards'])[1]");
    By dashBoardName = By.xpath("(//a/li[contains(text(),'11')])[1]");
    By USER_ICON = By.xpath("//button[@title='menna+testproject@intellisense.io']");
    By SIGN_OUT = By.xpath("//button[contains(text(),'Sign Out')]");
    By BUTTON_OK  = By.xpath("//button[@class='btn btn-primary']");

    By grid   = By.className("assets");


    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver=driver;
    }

    @Step("verify navigation to the dashboard successfully")
    public void navigateToDashBoard() throws InterruptedException {
        driver.findElement(dashboardIcon).click();
        driver.findElement(dashBoardName).click();
        driver.get("https://reference-test.intellisense.io/#!/id/dashboards/1471");

        Assert.assertTrue(driver.findElement(grid).isDisplayed());
        Thread.sleep(2000);
    }

    @Step("SignOut the page")
    public void signOutThePage() throws InterruptedException {
        driver.findElement(USER_ICON).click();
        Thread.sleep(2000);
        driver.findElement(SIGN_OUT).click();
        Thread.sleep(2000);
        driver.findElement(BUTTON_OK).click();
    }





}
