package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class TestBase {

    public static WebDriver driver;
  //  String url = "https://reference-test.intellisense.io/";
    private final String propertyFilePath= "C://Users//rinsh//IdeaProjects//Intellinsene-Test-Project//configs//Configuation.properties";
    public static Properties properties;

    @BeforeTest
    public void setup(ITestContext context) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(propertyFilePath));
        properties = new Properties();
        properties.load(reader);
        driver = getDriver();
        driver.get(properties.getProperty("url"));
        context.setAttribute("WebDriver", driver);
    }

    public WebDriver getDriver(){
        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true"); // This suppresses the Severe Timed out receiving messages
        System.setProperty("webdriver.chrome.driver", "C://Drivers//chromedriver_win32//chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return  driver;
    }

    @AfterTest(alwaysRun=true)
    public void tearDown() {
        driver.quit();
    }
}
