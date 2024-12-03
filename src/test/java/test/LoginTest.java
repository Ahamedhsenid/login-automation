package test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest {
    private static ExtentReports extentReports;
    private static ExtentTest extentTest;
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        // Initialize Extent Reports
        extentReports = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("login-report.html");
        extentReports.attachReporter(sparkReporter);

        // Setup WebDriver
//        WebDriverManager.chromedriver().setup();
 //      driver = new ChromeDriver();
      // driver.manage().window().maximize();

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://logsiru-dev.practechs.com/auth/login");
    }

    @Test(dataProvider = "csvLoginCredentials", dataProviderClass = CSVDataProvider.class)
    public void testLogin(String username, String password, String expectedMessage) {
        extentTest = extentReports.createTest("Login Test with username: " + username);

        // Navigate to the login page
        //driver.get("https://qa.pht.hsenidjapan.com/auth/login");

        // Create an instance of the LoginPage class
        LoginPage loginPage = new LoginPage(driver);

        // Perform login
        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.clickLogin();

        // Handle potential error message or continue to device switch and dashboard validation
        handleLoginFlow(loginPage, expectedMessage);



    }

    private void handleLoginFlow(LoginPage loginPage, String expectedMessage) {
        // Check if an error message appears
        String actualMessage = loginPage.getErrorMessage();
        System.out.println(actualMessage);
        if (actualMessage != null && actualMessage.contains(expectedMessage)) {
            extentTest.pass("Test passed with expected error message: " + expectedMessage);
        } else {
            validateLoginFlow(loginPage, expectedMessage);
        }
    }

    private void validateLoginFlow(LoginPage loginPage, String expectedMessage) {
        // Check if device switch prompt is displayed
        if (loginPage.isDeviceSwitchPromptDisplayed()) {
            loginPage.clickSwitchDevice();
        }

        // Check if the dashboard is loaded or if the current URL matches the expected dashboard URL
        if (loginPage.isDashboardLoaded()) {
            extentTest.pass("Login successful and dashboard loaded!");
        } else {
            String currentUrl = loginPage.getCurrentUrl();
            if (currentUrl.equals("https://logsiru-dev.practechs.com/dashboard")) {
                extentTest.pass("Login successful with correct URL: " + currentUrl);
            } else {
                extentTest.fail("Login failed, incorrect URL: " + currentUrl);
            }
        }
    }

    @AfterClass
    public void tearDown() {
        // Flush the extent report and close the driver
        extentReports.flush();
        driver.quit();
    }
}
