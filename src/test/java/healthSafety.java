import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.example.pages.DashboardPage;
import org.example.pages.HealthSafetyPage;
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

public class healthSafety {
    private static ExtentReports extentReports;
    private static ExtentTest extentTest;
    private static WebDriver driver;
    private static WebDriverWait wait;

    public static void main(String[] args) {
        setupExtentReports();
        setupWebDriver();

        try {
            extentTest = extentReports.createTest("health Safety Create Automation");

            driver.get("https://logsiru-dev.practechs.com/auth/login");
            LoginPage loginPage = new LoginPage(driver);
            DashboardPage dashboardPage = new DashboardPage(driver);
            HealthSafetyPage healthSafetyPage = new HealthSafetyPage(driver);


            performLogin(loginPage);
            handleDeviceSwitch(loginPage);
         //  navigateToSettingsPage(dashboardPage);
          // physicianLogin(dashboardPage, loginPage);
          //  phtlogout(dashboardPage, loginPage);
            healthSafetyCreate(dashboardPage, healthSafetyPage);





        } catch (Exception e) {
            logFailure("Test failed: " + e.getMessage());
        } finally {
            tearDown();
        }
    }

    private static void setupExtentReports() {
        extentReports = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("health Safety Create-report.html");
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

    private static void healthSafetyCreate(DashboardPage dashboardPage,HealthSafetyPage healthSafetyPage ) {

        try {
            dashboardPage.setLanguage();
            healthSafetyPage.clickhealthSafety();
            healthSafetyPage.settitlehealthSafety("Health Safety test ");
            healthSafetyPage.setofficenameHealthSafety("tokyo");
            healthSafetyPage.submithealthSafety();
            String message = healthSafetyPage.sucessHealthSafety();
            if (message.contains("Created")) {
                extentTest.pass("health and safety creation succesfully created" );
            } else {
                extentTest.log(Status.FAIL, "health and safety creation failed to create");
            }

        } catch (NoSuchElementException e) {
            extentTest.log(Status.FAIL, "health and safety creation failed to create");
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



