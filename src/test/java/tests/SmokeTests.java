package tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.*;
import utils.ConfigFileReader;

;

public class SmokeTests extends TestBase {


    @BeforeTest
    @Parameters({"username","password"})
    public void Login(String userName, String password) throws InterruptedException {
        String decodedPass = ConfigFileReader.decryptText(password);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(userName,decodedPass);

    //    Assert.assertTrue(homePage.isCartPageLoaded());
    }

    @Test(priority=2)
    public void verifySearchResults() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        SearchPage searchPage = new SearchPage(driver);
        //homePage.navigateToDashBoard();
        searchPage.verifySearchResults("stockpile");
        //    Assert.assertTrue(homePage.isCartPageLoaded());
    }
    @Test(priority=1)
    public void verifyAddSingularData() throws InterruptedException {
        DashBoardPage dashPage = new DashBoardPage(driver);
        HomePage homePage = new HomePage(driver);
        homePage.navigateToDashBoard();
        dashPage.verifyAddSingularData("78");
        //    Assert.assertTrue(homePage.isCartPageLoaded());
    }
    //Negative tests
    @Test(priority=4)
    public void verifyInvalidLogin() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.signOutThePage();
        Thread.sleep(2000);
        LoginPage login = new LoginPage(driver);
        login.login("menna+testproject1@intellisense.io","123124123123");
        login.verifyAlertMessage();
    }

    @Test(priority=3)
    public void verifyDuplicateDashboard() throws InterruptedException {
        DashBoardPage dashPage = new DashBoardPage(driver);
        HomePage homePage = new HomePage(driver);
    //    homePage.navigateToDashBoard();
        homePage.verifyDuplicateDashboard();

    }





}
