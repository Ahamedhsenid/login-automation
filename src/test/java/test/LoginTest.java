package test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
    private static ExtentReports extentReports;
    private static ExtentTest extentTest;

    public static void main(String[] args) {
        // Initialize Extent Reports
        extentReports = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("login-report.html");
        extentReports.attachReporter(sparkReporter);

        // Setup WebDriver
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://qa.pht.hsenidjapan.com/auth/login");

        // Create an instance of the LoginPage class
        LoginPage loginPage = new LoginPage(driver);

        // Perform login tests
        performLoginTests(driver, loginPage);

        // Flush the extent report
        extentReports.flush();
        driver.quit();
    }

    private static void performLoginTests(WebDriver driver, LoginPage loginPage) {
        // Test cases with expected error messages
        runLoginTest(driver, loginPage, "incorrectUser", "123456", "ログインエラー\nログインID又はパスワードが間違っています");
        runLoginTest(driver, loginPage, "himali@hsenidmobile.com", "incorrectPassword", "ログインエラー\nログインID又はパスワードが間違っています");
        runLoginTest(driver, loginPage, "student", "Password123", "ログインエラー\nログインID又はパスワードが間違っています");

        // Valid login
        runLoginTest(driver, loginPage, "hashini@hsenidlanka.com", "123456", "Login Successful!");
    }

    private static void runLoginTest(WebDriver driver, LoginPage loginPage, String username, String password, String expectedMessage) {
        extentTest = extentReports.createTest("Login Test with " + username + " and " + password);

        // Perform the login steps
        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.clickLogin();

        // Handle potential error message
        String actualMessage = loginPage.getErrorMessage();
        if (actualMessage != null && actualMessage.contains(expectedMessage)) {
            extentTest.pass("Test passed with expected message: " + expectedMessage);
        } else {
            // Validate successful login or handle device switch
            validateLoginFlow(driver, loginPage, extentTest, expectedMessage);
        }
    }

    private static void validateLoginFlow(WebDriver driver, LoginPage loginPage, ExtentTest extentTest, String expectedMessage) {
        // Check if we need to handle device switch
        if (loginPage.isDeviceSwitchPromptDisplayed()) {
            loginPage.clickSwitchDevice();
        }

        // Now check if the dashboard is loaded
        if (loginPage.isDashboardLoaded()) {
            extentTest.pass("Login successful and dashboard loaded!");
        } else {
            String currentUrl = loginPage.getCurrentUrl();
            if (currentUrl.equals("https://qa.pht.hsenidjapan.com/dashboard")) {
                extentTest.pass("Login successful with correct URL: " + currentUrl);
            } else {
                extentTest.fail("Login failed, incorrect URL: " + currentUrl);
            }
        }
    }
}
