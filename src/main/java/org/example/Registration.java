package org.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;

public class Registration {
    private static ExtentReports extentReports;
    private static ExtentTest extentTest;

    public static void main(String[] args) {
        extentReports = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extent-report.html");
        extentReports.attachReporter(sparkReporter);

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        Actions action = new Actions(driver);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        try {
            // Start a new test
            extentTest = extentReports.createTest("Employee Registration Automation");

            driver.get("https://qa.pht.hsenidjapan.com/auth/login");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            // Test Login
            WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-email\"]")));
            WebElement password = driver.findElement(By.xpath("//*[@id=\"input-password\"]"));
            WebElement submitButton = driver.findElement(By.xpath("//button[contains(text(),'ログイン')]"));

            username.clear();
            password.clear();
            username.sendKeys("hashini@hsenidlanka.com");
            password.sendKeys("123456");
            submitButton.click();
            extentTest.log(Status.PASS, "Login test passed");

            // Handle device switch warning, if present
            try {
                WebElement switchDevice = driver.findElement(By.xpath("//*[@id=\"cdk-overlay-0\"]/nb-dialog-container/ngx-login-warning/nb-card/nb-card-body/div[2]/div[1]/button"));
                switchDevice.click();
            } catch (NoSuchElementException e) {
                extentTest.log(Status.INFO, "No device switch warning displayed");
            }

            WebElement employee = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div/div/div/nb-sidebar/div/div/nb-menu/ul/li[2]/a")));
            employee.click();
            extentTest.log(Status.PASS, "Navigated to employee section");

            WebElement newRegistration = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div/div/div/div/div/nb-layout-column/div/ngx-employee-list-main/div/div[6]/button")));
            newRegistration.click();
            extentTest.log(Status.PASS, "Navigated to New Registration form");

            WebElement lName = driver.findElement(By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div[1]/div/div/div/div/nb-layout-column/div/ngx-employee-reg/nb-card/nb-card-body/form/div/div[1]/div[1]/div[1]/div[2]/input"));
            lName.sendKeys("AA");
            WebElement fName = driver.findElement(By.xpath("/html[1]/body[1]/ngx-app[1]/ngx-pages[1]/ngx-one-column-layout[1]/nb-layout[1]/div[1]/div[1]/div[1]/div[1]/div[1]/nb-layout-column[1]/div[1]/ngx-employee-reg[1]/nb-card[1]/nb-card-body[1]/form[1]/div[1]/div[1]/div[1]/div[2]/div[2]/input[1]"));
            fName.sendKeys("TT");
            WebElement DOB = driver.findElement(By.xpath("/html[1]/body[1]/ngx-app[1]/ngx-pages[1]/ngx-one-column-layout[1]/nb-layout[1]/div[1]/div[1]/div[1]/div[1]/div[1]/nb-layout-column[1]/div[1]/ngx-employee-reg[1]/nb-card[1]/nb-card-body[1]/form[1]/div[1]/div[1]/div[4]/div[2]/div[1]/input[1]"));
            DOB.sendKeys("2002-10-10");
            DOB.sendKeys(Keys.ENTER);
            WebElement radioBtn = driver.findElement(By.xpath("/html[1]/body[1]/ngx-app[1]/ngx-pages[1]/ngx-one-column-layout[1]/nb-layout[1]/div[1]/div[1]/div[1]/div[1]/div[1]/nb-layout-column[1]/div[1]/ngx-employee-reg[1]/nb-card[1]/nb-card-body[1]/form[1]/div[1]/div[1]/div[3]/div[2]/div[1]/nb-radio-group[1]/nb-radio[1]/label[1]/span[2]"));
            radioBtn.click();

            WebElement office = driver.findElement(By.xpath("/html[1]/body[1]/ngx-app[1]/ngx-pages[1]/ngx-one-column-layout[1]/nb-layout[1]/div[1]/div[1]/div[1]/div[1]/div[1]/nb-layout-column[1]/div[1]/ngx-employee-reg[1]/nb-card[1]/nb-card-body[1]/form[1]/div[1]/div[2]/div[1]/div[2]/nb-select[1]/button[1]"));
            office.click();

            action.scrollToElement(driver.findElement(By.xpath("//*[@id=\"nb-option-137\"]")));
            WebElement officeSelect = driver.findElement(By.xpath("//*[@id=\"nb-option-137\"]"));
            officeSelect.click();

            WebElement department = driver.findElement(By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div[1]/div/div/div/div/nb-layout-column/div/ngx-employee-reg/nb-card/nb-card-body/form/div/div[2]/div[2]/div[2]/nb-select/button"));
            department.click();

            action.scrollToElement(driver.findElement(By.xpath("//*[@id=\"nb-option-157\"]")));
            WebElement depSelect = driver.findElement(By.xpath("//*[@id=\"nb-option-157\"]"));
            depSelect.click();

            WebElement empNo = driver.findElement(By.xpath("/html[1]/body[1]/ngx-app[1]/ngx-pages[1]/ngx-one-column-layout[1]/nb-layout[1]/div[1]/div[1]/div[1]/div[1]/div[1]/nb-layout-column[1]/div[1]/ngx-employee-reg[1]/nb-card[1]/nb-card-body[1]/form[1]/div[1]/div[2]/div[7]/div[2]/input[1]"));
            empNo.sendKeys("00120117");

            WebElement status = driver.findElement(By.xpath("/html[1]/body[1]/ngx-app[1]/ngx-pages[1]/ngx-one-column-layout[1]/nb-layout[1]/div[1]/div[1]/div[1]/div[1]/div[1]/nb-layout-column[1]/div[1]/ngx-employee-reg[1]/nb-card[1]/nb-card-body[1]/form[1]/div[1]/div[2]/div[15]/nb-radio-group[1]/nb-radio[1]/label[1]/span[2]"));
            status.click();

            WebElement saveBtn = driver.findElement(By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div[1]/div/div/div/div/nb-layout-column/div/ngx-employee-reg/nb-card/nb-card-body/div[2]/div[1]/button"));
            saveBtn.click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"cdk-overlay-4\"]/nb-dialog-container/nb-card/nb-card-body")));
            action.scrollToElement(driver.findElement(By.xpath("//*[@id=\"cdk-overlay-4\"]/nb-dialog-container/nb-card/nb-card-body/div[3]/div/button")));
            WebElement regBtn = driver.findElement(By.xpath("//*[@id=\"cdk-overlay-4\"]/nb-dialog-container/nb-card/nb-card-body/div[3]/div/button"));
            regBtn.click();

            extentTest.log(Status.PASS, "Employee registration form filled successfully");

        } catch (Exception e) {
            extentTest.log(Status.FAIL, "Test failed: " + e.getMessage());
            takeScreenshot(driver, "test-failure.png");
            extentTest.addScreenCaptureFromPath("test-failure.png");
        } finally {
//            driver.quit();
            extentReports.flush();
        }
    }

    public static void takeScreenshot(WebDriver driver, String fileName) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(fileName));
        } catch (IOException e) {
            extentTest.log(Status.WARNING, "Failed to capture screenshot: " + e.getMessage());
        }
    }
}
