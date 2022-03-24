package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.testng.Assert;


public class DashBoardPage {
    By ADD_BUTTON = By.xpath("(//*[@id='data-input-add-icon-id'])[1]");
    By VALUE_BOX = By.xpath("//*[@id='value']");
    By SUBMIT_BUTTON = By.xpath("//span[contains(text(),'Submit')]");
    By VALUE_FIELD = By.xpath("(//td[@class='MuiTableCell-root MuiTableCell-body'])[5]");



    WebDriver driver;

    public DashBoardPage(WebDriver driver) {
        this.driver=driver;
    }

    @Step("verify adding singular data")
    public void verifyAddSingularData(String value) throws InterruptedException {
        driver.findElement(ADD_BUTTON).click();
        Thread.sleep(4000);
        driver.findElement(VALUE_BOX).sendKeys(value);
        Thread.sleep(3000);
        driver.findElement(SUBMIT_BUTTON).click();
        Thread.sleep(3000);
        String fieldValue  = driver.findElement(VALUE_FIELD).getText();
        Assert.assertTrue(fieldValue.toUpperCase().contains(value.toUpperCase()),"The added values is not matching");
        Thread.sleep(3000);
    }
}
