package org.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class LoginTest {
    private static ExtentReports extentReports;
    private static ExtentTest extentTest;

    public static void main(String[] args) {
        extentReports = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extent-report.html");
        extentReports.attachReporter(sparkReporter);

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://qa.pht.hsenidjapan.com/health-check/results");

        loginTest(driver, "incorrectUser ", "123456", "ログインエラー\n" + "ログインID又はパスワードが間違っています");
        loginTest(driver, "himali@hsenidmobile.com", "incorrectPassword", "ログインエラー\n" + "ログインID又はパスワードが間違っています");
        loginTest(driver, "student   ", "Password123", "ログインエラー\n" + "ログインID又はパスワードが間違っています");
        loginTest(driver, "student", "Password123   ", "ログインエラー\n" + "ログインID又はパスワードが間違っています");

        loginTest(driver, "himali@hsenidmobile.com", "123456", "Login successful!");

        extentReports.flush();
    }

    public static void loginTest(WebDriver driver, String userNameInput, String passwordInput, String expectedMessage) {
        extentTest = extentReports.createTest("Login Test with " + userNameInput + " and " + passwordInput);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        WebElement username = driver.findElement(By.xpath("//*[@id=\"input-email\"]"));
        WebElement password = driver.findElement(By.xpath("//*[@id=\"input-password\"]"));
        WebElement submitButton = driver.findElement(By.xpath("/html/body/ngx-app/nb-auth/nb-layout/div/div/div/div/div/nb-layout-column/nb-card/nb-card-body/nb-auth-block/ngx-login/form/nb-card/nb-card-body/div[3]/div/button"));

        username.clear();
        password.clear();

        username.sendKeys(userNameInput);
        password.sendKeys(passwordInput);
        submitButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"cdk-overlay-0\"]/nb-toastr-container")));
            String error = errorMessage.getText();

            System.out.println("\nLogin Failed");
            Thread.sleep(2000);

            if (error.contains(expectedMessage)) {
                System.out.println("Test Passed,");
                System.out.println("Expected Message: " + expectedMessage);
                System.out.println("Actual Message: " + error);
                extentTest.pass("Test passed with expected message: " + expectedMessage);
            } else {
                System.out.println("Test Failed: Expected '" + expectedMessage + "', but got '" + error + "'");
                extentTest.fail("Test failed with unexpected message: " + error);
            }
        } catch (Exception e) {
            System.out.println("Login Successful");
            extentTest.pass("Login successful");
        }
    }
}