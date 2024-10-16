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

        driver.get("https://qa.pht.hsenidjapan.com/auth/login");

        loginTest(driver, "incorrectUser ", "123456", "ログインエラー\n" + "ログインID又はパスワードが間違っています");
        loginTest(driver, "himali@hsenidmobile.com", "incorrectPassword", "ログインエラー\n" + "ログインID又はパスワードが間違っています");
        loginTest(driver, "student   ", "Password123", "ログインエラー\n" + "ログインID又はパスワードが間違っています");
        loginTest(driver, "student", "Password123   ", "ログインエラー\n" + "ログインID又はパスワードが間違っています");

        loginTest(driver, "hashini@hsenidlanka.com", "123456", "Login Successful!");

        extentReports.flush();
    }

    public static void loginTest(WebDriver driver, String userNameInput, String passwordInput, String expectedMessage) {
        extentTest = extentReports.createTest("Login Test with " + userNameInput + " and " + passwordInput);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"input-email\"]")));
        WebElement password = driver.findElement(By.xpath("//*[@id=\"input-password\"]"));
        WebElement submitButton = driver.findElement(By.xpath("/html/body/ngx-app/nb-auth/nb-layout/div/div/div/div/div/nb-layout-column/nb-card/nb-card-body/nb-auth-block/ngx-login/form/nb-card/nb-card-body/div[3]/div/button"));

        username.clear();
        password.clear();

        username.sendKeys(userNameInput);
        password.sendKeys(passwordInput);
        submitButton.click();

        try {
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"cdk-overlay-0\"]/nb-toastr-container")));
            String error = errorMessage.getText();

            System.out.println("\nLogin Failed");
            errorMessage.click();

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
            wait.withTimeout(Duration.ofSeconds(2));
            String url = driver.getCurrentUrl();
            if (url.equals("https://qa.pht.hsenidjapan.com/dashboard")) {
                System.out.println("\nLogin Successful");
                System.out.println("Login successful with correct URL: " + url);
                extentTest.pass("Login successful with correct URL: " + url);

            } else {
                try{
                    WebElement switchDevice = driver.findElement(By.xpath("//*[@id=\"cdk-overlay-1\"]/nb-dialog-container/ngx-login-warning/nb-card/nb-card-body/div[2]/div[1]/button"));
                    switchDevice.click();
//                    Thread.sleep(Duration.ofSeconds(15));
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div/div/div/div/nb-layout-header/nav/ngx-header/div[1]/label")));
                    url = driver.getCurrentUrl();

                    if (url.equals("https://qa.pht.hsenidjapan.com/dashboard")) {
                        System.out.println("\nLogin Successful");
                        System.out.println("Login successful with correct URL: " + url);
                        extentTest.pass("Login successful with correct URL: " + url);
                    } else {
                        System.out.println("\nTest Failed: Unexpected URL '" + url + "'");
                        extentTest.fail("Test failed with unexpected URL: " + url);
                    }
                } catch (Exception ex) {
                    System.out.println("\nTest Failed: Expected URL 'https://qa.pht.hsenidjapan.com/dashboard', but got '" + url + "'");
                    extentTest.fail("Test failed with unexpected URL: " + url);
                }
            }
        }
    }
}