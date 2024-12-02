import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.example.pages.DashboardPage;
import org.example.pages.LoginPage;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class PhysicianTest {
    private static ExtentReports extentReports;
    private static ExtentTest extentTest;
    private static WebDriver driver;
    private static WebDriverWait wait;

    public static void main(String[] args) {
        setupExtentReports();
        setupWebDriver();

        try {
            extentTest = extentReports.createTest("Physician Login Check Automation");

            driver.get("https://logsiru-dev.practechs.com/auth/login");
            LoginPage loginPage = new LoginPage(driver);
            DashboardPage dashboardPage = new DashboardPage(driver);


            performLogin(loginPage);
            handleDeviceSwitch(loginPage);
         //  navigateToSettingsPage(dashboardPage);
           physicianLogin(dashboardPage, loginPage);



//            addNewOfficeValidation(officeCreationPage);

        } catch (Exception e) {
            logFailure("Test failed: " + e.getMessage());
        } finally {
            tearDown();
        }
    }

    private static void setupExtentReports() {
        extentReports = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("physician-login-report.html");
        extentReports.attachReporter(sparkReporter);
    }

    private static void setupWebDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    private static void performLogin(LoginPage loginPage) {

        loginPage.setUsername("nimasha@hsenidoutsourcing.com");
        loginPage.setPassword("123456");
        loginPage.clickLogin();
        extentTest.log(Status.PASS, "Login test passed");
    }

    private static void physicianLogin(DashboardPage dashboardPage, LoginPage loginPage) {

        try {
            dashboardPage.setLanguage();
            dashboardPage.clickHealthCheck();
            dashboardPage.clickEmployeeJudgement();
            dashboardPage.EmployeeJudgementTitle();
            dashboardPage.healthcheckTitle();

            String currentUrl = loginPage.getCurrentUrl();
            if (currentUrl.equals("https://logsiru-dev.practechs.com/health-check/decision/list")) {
                extentTest.pass("Physician Login successful with correct URL: " + currentUrl);
            } else {
                extentTest.fail("Physician Login failed, incorrect URL: " + currentUrl);
            }

        } catch (NoSuchElementException e) {
            extentTest.log(Status.INFO, "Physician login failed");
        }
    }

    private static void handleDeviceSwitch(LoginPage loginPage) {
        try {
            loginPage.setSwitchDeviceButtonReg();
            extentTest.log(Status.INFO, "Handled device switch warning");
        } catch (NoSuchElementException e) {
            extentTest.log(Status.INFO, "No device switch warning displayed");
        }
    }

    private static void navigateToSettingsPage(DashboardPage dashboardPage) {
        dashboardPage.setLanguage();
        dashboardPage.clickSettings();
        extentTest.log(Status.PASS, "Navigated to settings page");

        dashboardPage.clickAdd();
        extentTest.log(Status.PASS, "Navigated to Basic Info Add/Edit Page");
    }





    private static void logFailure(String message) {
        extentTest.log(Status.FAIL, message);
        takeScreenshot("test-failure.png");
        try {
            extentTest.addScreenCaptureFromPath("test-failure.png");
        } catch (Exception e) {
            extentTest.log(Status.WARNING, "Failed to attach screenshot: " + e.getMessage());
        }
    }

    private static void takeScreenshot(String fileName) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(fileName));
        } catch (IOException e) {
            extentTest.log(Status.WARNING, "Failed to capture screenshot: " + e.getMessage());
        }
    }

    private static void tearDown() {
        if (driver != null) {
//            driver.quit();
        }
        extentReports.flush();
    }

}



