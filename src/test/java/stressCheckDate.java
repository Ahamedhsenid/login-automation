import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pages.DashboardPage;
import org.example.pages.LoginPage;
import org.example.pages.StressAssessmentPage;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class stressCheckDate {
    private static ExtentReports extentReports;
    private static ExtentTest extentTest;
    private static WebDriver driver;
    private static WebDriverWait wait;

    public static void main(String[] args) {
        setupExtentReports();
        setupWebDriver();

        try {
            extentTest = extentReports.createTest(" Stress check invalid date test");

            driver.get("https://logsiru-dev.practechs.com/auth/login");
            LoginPage loginPage = new LoginPage(driver);
            DashboardPage dashboardPage = new DashboardPage(driver);
            StressAssessmentPage stressAssessmentPage = new StressAssessmentPage(driver);

            performLogin(loginPage);
            handleDeviceSwitch(loginPage);
            navigateToStressAssessment(dashboardPage);
            //createStressCheck(stressAssessmentPage);
            invalidDate(stressAssessmentPage);
            //validateCreateStressCheck(stressAssessmentPage);

        } catch (Exception e) {
            logFailure("Test failed: " + e.getMessage());
        } finally {
            tearDown();
        }
    }

    private static void setupExtentReports() {
        extentReports = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("Stress-check-invalid-date test-report.html");
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
        loginPage.setUsername("hashini@hsenidlanka.com");
        loginPage.setPassword("123456");
        loginPage.clickLogin();
        extentTest.log(Status.PASS, "Login test passed");
    }

    private static void handleDeviceSwitch(LoginPage loginPage) {
        try {
            loginPage.setSwitchDeviceButtonReg();
            extentTest.log(Status.INFO, "Handled device switch warning");
        } catch (NoSuchElementException e) {
            extentTest.log(Status.INFO, "No device switch warning displayed");
        }
    }

    private static void navigateToStressAssessment(DashboardPage dashboardPage) {
        dashboardPage.setLanguage();
        dashboardPage.clickStressAssessment();
        extentTest.log(Status.PASS, "Navigated to Stress Assessment section");

        dashboardPage.clickCreateStressBtn();
        extentTest.log(Status.PASS, "Navigated to Create a New Stress Assessment page");
    }

    private static void createStressCheck(StressAssessmentPage stressAssessmentPage){
        stressAssessmentPage.stressName("Automation Test");
        stressAssessmentPage.stressStartDate("2002-12-10");
        stressAssessmentPage.stressEndDate("2002-12-09");
        stressAssessmentPage.stressQuestion("57 stress check items");
        stressAssessmentPage.clickSubmitBtn();
        stressAssessmentPage.clickConfirmationBtn();
    }

    private static void invalidDate(StressAssessmentPage stressAssessmentPage){
        stressAssessmentPage.stressName("Automation Test");
        stressAssessmentPage.stressStartDate("2002-12-09");
        stressAssessmentPage.stressEndDate("2002-12-10");
        stressAssessmentPage.stressQuestion("57 stress check items");

        try {
            // Check if the submit button is disabled
            if (!stressAssessmentPage.submitBtnEnabled()) {
                extentTest.log(Status.PASS, "Submit button is disabled for invalid date, test passed.");
            } else {
                extentTest.log(Status.FAIL, "Submit button is enabled for invalid date, test failed.");
            }
        } catch (Exception e) {
            extentTest.log(Status.WARNING, "Error occurred while verifying invalid date logic: " + e.getMessage());
        }



    }

    private static void validateCreateStressCheck(StressAssessmentPage stressAssessmentPage) {
        try {
            String message = stressAssessmentPage.stressErrorMessage();
            if (message.contains("Error")) {
                extentTest.log(Status.FAIL, "Date Error");
            } else {
                extentTest.log(Status.PASS,"Created Successfully");
            }

        } catch (TimeoutException e){
            logFailure("Timing Loadout");
        }
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