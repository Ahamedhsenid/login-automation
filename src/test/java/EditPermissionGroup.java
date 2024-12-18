import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pages.DashboardPage;
import org.example.pages.LoginPage;
import org.example.pages.PermissionGroupPage;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class EditPermissionGroup {
    private static ExtentReports extentReports;
    private static ExtentTest extentTest;
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static Actions action;

    public static void main(String[] args) {
        setupExtentReports();
        setupWebDriver();

        try {
            extentTest = extentReports.createTest("Create Permission Group");

//            driver.get("https://qa.pht.hsenidjapan.com/auth/login");
            driver.get("https://logsiru-dev.practechs.com/auth/login");
            LoginPage loginPage = new LoginPage(driver);
            DashboardPage dashboardPage = new DashboardPage(driver);
            PermissionGroupPage permissionGroupPage = new PermissionGroupPage(driver);

            performLogin(loginPage);
            handleDeviceSwitch(loginPage);
            navigateToEmployeeSection(dashboardPage);
            editPermissionGroup(dashboardPage, permissionGroupPage);
         //   createPermissionManagerAdd(permissionGroupPage);

        } catch (Exception e) {
            logFailure("Test failed: " + e.getMessage());
        } finally {
            tearDown();
        }
    }

    private static void setupExtentReports() {
        extentReports = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("create-permission-group-report.html");
        extentReports.attachReporter(sparkReporter);
    }

    private static void setupWebDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        action = new Actions(driver);
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

    private static void navigateToEmployeeSection(DashboardPage dashboardPage) {
        dashboardPage.setLanguage();
        dashboardPage.clickSettingsPermission();
        extentTest.log(Status.PASS, "Navigated to Permissions Settings section");


//        dashboardPage.clickEmployeeInfoUpload();
//        extentTest.log(Status.PASS, "Navigated to upload page");
    }

    private static void editPermissionGroup(DashboardPage dashboardPage, PermissionGroupPage permissionGroupPage) {
        try {
            Thread.sleep(2000);
            dashboardPage.editPermission();
            extentTest.log(Status.PASS, "Navigated to role Settings section");
            Thread.sleep(500);
            permissionGroupPage.setRole("open3");
            java.util.List<String> labels = java.util.List.of("tokyo");
            permissionGroupPage.setOffice(labels);

           // permissionGroupPage.clickkeepBtn();


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private static void createPermissionManagerAdd(PermissionGroupPage permissionGroupPage){
        permissionGroupPage.setName("4 Share");

        permissionGroupPage.setEmail("john@doe.com");
        permissionGroupPage.setRole("open9");
        java.util.List<String> labels = java.util.List.of("tokyo");
        permissionGroupPage.setOffice(labels);
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