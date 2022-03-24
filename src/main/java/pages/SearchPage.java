package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.List;

public class SearchPage {

    By BUTTON_SEARCH = By.xpath("//a[@title='SEARCH']");
    By SEARCH_BOX = By.xpath("//input[@ng-model='searchString']");
    By SEARCH_RESULTS = By.xpath("//span[@ng-bind='res.fullName']");
    By grid   = By.className("assets");
    WebDriver driver;

    public SearchPage(WebDriver driver) {
        this.driver=driver;
    }

    @Step("verify search results")
    public void verifySearchResults(String searchKey) throws InterruptedException {
        driver.findElement(BUTTON_SEARCH).click();
        Actions action = new Actions(driver);
        WebElement searchBox = driver.findElement(SEARCH_BOX);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        // set value using javascript to handle text changed event
        jsExecutor.executeScript("arguments[0].value='"+searchKey+"'", searchBox);

        Thread.sleep(3000);
        ((JavascriptExecutor) driver).executeScript(
                "$(arguments[0]).change(); return true;"
                , searchBox);
        Thread.sleep(3000);


        List<WebElement> lstResults = driver.findElements(SEARCH_RESULTS);
        for (WebElement element : lstResults){
            String myText = element.getText();
            boolean contains = myText.toUpperCase().contains(searchKey.toUpperCase());
            if (contains) {
                Assert.assertTrue(true);
            } else {
                Assert.fail("Search text not found in the results");
            }
        }

    }
}
