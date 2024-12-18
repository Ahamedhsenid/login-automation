package test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.pages.DashboardPage;
import org.example.pages.EmployeeCreationPage;
import org.example.pages.LoginPage;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Registration {
    private static ExtentReports extentReports;
    private static ExtentTest extentTest;
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static Actions action;

    @BeforeClass
    public void setup() {
        setupExtentReports();
        setupWebDriver();
    }

    @DataProvider(name = "csvRegistrationData")
    public Object[][] getRegistrationData() throws IOException {
        return new CSVDataProvider().readRegistrationCsvData();
    }

    private static void setupExtentReports() {
        extentReports = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("employee-creation-report1.html");
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

    @Test(dataProvider = "csvRegistrationData")
    public void employeeRegistrationTest(String firstName, String lastName, String dob, String gender, String office, String department, String employeeNumber, String operationStatus) {
        try {
            extentTest = extentReports.createTest("Employee Registration Automation 1");

            driver.get("https://logsiru-dev.practechs.com/auth/login");
            LoginPage loginPage = new LoginPage(driver);
            DashboardPage dashboardPage = new DashboardPage(driver);
            EmployeeCreationPage creationPage = new EmployeeCreationPage(driver, action);

            performLogin(loginPage);
            handleDeviceSwitch(loginPage);
            navigateToEmployeeSection(dashboardPage);
            fillEmployeeRegistrationForm(creationPage, firstName, lastName, dob, gender, office, department, employeeNumber, operationStatus);
            validateEmployeeRegistration(creationPage);


        } catch (Exception e) {
            logFailure("Test failed: " + e.getMessage());
        }
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
        dashboardPage.setEmployeeList();
        extentTest.log(Status.PASS, "Navigated to employee section");

        dashboardPage.setNewRegistration();
        extentTest.log(Status.PASS, "Navigated to New Registration form");
    }

    private static void fillEmployeeRegistrationForm(EmployeeCreationPage creationPage, String firstName, String lastName, String dob, String gender, String office, String department, String employeeNumber, String operationStatus) {
        creationPage.setlName(lastName);
        creationPage.setfName(firstName);
        creationPage.setDOB(dob);
        creationPage.selectGender(gender);
        creationPage.setOfficeSelect(office);
        creationPage.setDepSelect(department);
        creationPage.setEmpNo(employeeNumber);
        creationPage.selectStatus(operationStatus);
        creationPage.setSaveBtn();
        creationPage.setRegBtn();
        extentTest.log(Status.PASS, "Employee registration form filled successfully");
    }

    private static void validateEmployeeRegistration(EmployeeCreationPage creationPage) {
        try {
            creationPage.setRegError();
            logFailure("Registration error");
        } catch (NoSuchElementException e) {
            extentTest.log(Status.INFO, "No registration error warning displayed");

            if (creationPage.success()) {
                extentTest.log(Status.PASS, "Employee Added Successfully");
            } else {
                extentTest.log(Status.FAIL, "Failed to Add Employee");
            }
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

    @AfterClass
    public void tearDown() {
        if (driver != null) {
//            driver.quit();
        }
        extentReports.flush();
    }
}