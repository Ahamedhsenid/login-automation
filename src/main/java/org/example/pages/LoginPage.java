package org.example.pages;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By usernameField = By.xpath("//*[@id=\"input-email\"]");
    private By passwordField = By.xpath("//*[@id=\"input-password\"]");
    private By loginButton = By.xpath("/html/body/ngx-app/nb-auth/nb-layout/div/div/div/div/div/nb-layout-column/nb-card/nb-card-body/nb-auth-block/ngx-login/form/nb-card/nb-card-body/div[3]/div/button");
    private By errorMessageLocator = By.xpath("//*[@id=\"cdk-overlay-0\"]/nb-toastr-container");
    //*[@id="cdk-overlay-0"]/nb-toastr-container
    private By switchDeviceButton = By.xpath("//*[@id=\"cdk-overlay-1\"]/nb-dialog-container/ngx-login-warning/nb-card/nb-card-body/div[2]/div[1]/button");
    private By switchDeviceButtonReg = By.xpath("//*[@id=\"cdk-overlay-0\"]/nb-dialog-container/ngx-login-warning/nb-card/nb-card-body/div[2]/div[1]/button");


    //logout
    private By loginLogo = By.xpath("/html/body/ngx-app/nb-auth/nb-layout/div/div/div/div/div/nb-layout-column/nb-card/nb-card-body/nb-auth-block/ngx-login/div/div[1]");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // Method to perform login
    public void setUsername(String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        WebElement usernameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        usernameElement.clear();
        usernameElement.sendKeys(username);
    }

    public void setPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        WebElement passwordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        passwordElement.clear();
        passwordElement.sendKeys(password);
    }

    public void clickLogin() {
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginBtn.click();
    }

    public String getErrorMessage() {
        try {
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator));

            String message = errorMessage.getText();

//            errorMessage.click(); // Dismiss error
            return message;
        } catch (Exception e) {
            return null; // Return null if no error message is displayed
        }
    }

    public boolean isDeviceSwitchPromptDisplayed() {
        try {
            WebElement switchDevice = wait.until(ExpectedConditions.visibilityOfElementLocated(switchDeviceButton));
            return switchDevice.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSwitchDevice() {
        WebElement switchDevice = wait.until(ExpectedConditions.elementToBeClickable(switchDeviceButton));
        switchDevice.click();
    }
    public void setSwitchDeviceButtonReg(){
        WebElement switchDeviceRegElement = wait.until(ExpectedConditions.elementToBeClickable(switchDeviceButtonReg));
        switchDeviceRegElement.click();
    }
    public boolean isDashboardLoaded() {
        try {
            // Wait for the dashboard page to load by checking some element unique to it
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div/div/div/div/nb-layout-header/nav/ngx-header/div[1]/label")));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getCurrentUrl() {
       // wait.until(ExpectedConditions.visibilityOfElementLocated(loginLogo));
        return driver.getCurrentUrl();
    }
    // Method to clear the form fields
    public void clearFields() {
        driver.findElement(usernameField).clear();
        driver.findElement(passwordField).clear();
    }
}
