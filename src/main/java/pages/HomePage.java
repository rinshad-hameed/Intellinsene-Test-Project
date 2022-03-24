package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.testng.Assert;

public class HomePage  extends TestBase{
    By dashboardIcon = By.xpath("(//li/a/i[@title='Dashboards'])[1]");
    By dashBoardName = By.xpath("(//a/li[contains(text(),'11')])[1]");
    By USER_ICON = By.xpath("//button[@title='menna+testproject@intellisense.io']");
    By SIGN_OUT = By.xpath("//button[contains(text(),'Sign Out')]");
    By BUTTON_OK  = By.xpath("//button[@class='btn btn-primary']");
    By grid   = By.className("assets");
    By FIRST_DASHBOARD = By.xpath("//*[@id='DASHBOARDS-collapse']/a[1]/li");
    By NEW_DASHBOARD = By.xpath("(//li[contains(text(),'New Dashboard')])[1]");
    By NAME = By.id("name");
    By SUBMIT = By.xpath("//div[2]/form/input");

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

    @Step("verify adding duplicate dashboard")
    public void verifyDuplicateDashboard() throws InterruptedException {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        WebElement dashBoardIcon = driver.findElement(dashboardIcon);
        executor.executeScript("arguments[0].click();", dashBoardIcon);
        Thread.sleep(3000);
        String dbName=  driver.findElement(FIRST_DASHBOARD).getText();
        Thread.sleep(3000);
        WebElement newDashboard = driver.findElement(NEW_DASHBOARD);

        executor.executeScript("arguments[0].click();", newDashboard);
        driver.findElement(NAME).sendKeys(dbName);
        Thread.sleep(3000);
        driver.findElement(SUBMIT).click();
        Assert.assertFalse(driver.findElement(NAME).isDisplayed(),"The dashboard added with duplicate name");
        Thread.sleep(2000);
    }





}
