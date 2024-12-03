package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HealthSafetyPage {
    private WebDriver driver;
    private WebDriverWait wait;



    //healthsafety
    private By healthSafetyButton = By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div/div/div/nb-sidebar/div/div/nb-menu/ul/li[8]/a/span");
    private By addHealthSafety = By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div/div/div/div/div/nb-layout-column/div/ngx-safety-and-health-committee/div[2]/div/button[2]");
    private By titleHealthSafety = By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div/div/div/div/div/nb-layout-column/div/ngx-new-minutes/div/div/nb-card[1]/nb-card-body/form/div[1]/div[1]/div/div[3]/input");
    private By officenameHealthSafety = By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div[1]/div/div/div/div/nb-layout-column/div/ngx-new-minutes/div/div/nb-card[1]/nb-card-body/form/div[2]/div[2]/div/div[3]/nb-select/button");
    private By createHealthSafety = By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div[1]/div/div/div/div/nb-layout-column/div/ngx-new-minutes/div/div/div/div/button[1]");
    private By confirmHealthSafety = By.xpath("//*[@id=\"cdk-overlay-3\"]/nb-dialog-container/nb-card/nb-card-body/div[4]/div[1]/div[2]/div/button");
    private By sucessHealthSafety = By.xpath("//*[@id=\"cdk-overlay-4\"]/nb-toastr-container");




    public HealthSafetyPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }





    public void clickhealthSafety(){
        WebElement healthSafetybuttonElement =  wait.until(ExpectedConditions.visibilityOfElementLocated(healthSafetyButton));
        healthSafetybuttonElement.click();
        WebElement addHealthSafetyElement =  wait.until(ExpectedConditions.visibilityOfElementLocated(addHealthSafety));
        addHealthSafetyElement.click();


    }
    public void settitlehealthSafety(String titlehealthSafety){
        WebElement healthSafetybuttonElement =  wait.until(ExpectedConditions.visibilityOfElementLocated(titleHealthSafety));
        healthSafetybuttonElement.sendKeys(titlehealthSafety);

    }




    public void setofficenameHealthSafety(String officeNameSelect) {
        WebElement officenameElement = driver.findElement(officenameHealthSafety);
        officenameElement.click();

        WebElement officeNameElement = driver.findElement(By.xpath("//*[contains(text(), '" + officeNameSelect + "')]"));
        officeNameElement.click();
    }


    public void submithealthSafety(){
        WebElement createHealthSafetyElement =  wait.until(ExpectedConditions.visibilityOfElementLocated(createHealthSafety));
        createHealthSafetyElement.click();
        WebElement confirmHealthSafetyElement =  wait.until(ExpectedConditions.visibilityOfElementLocated(confirmHealthSafety));
        confirmHealthSafetyElement.click();




    }
    public String sucessHealthSafety(){
        WebElement message =  wait.until(ExpectedConditions.visibilityOfElementLocated(sucessHealthSafety));

        String msg = message.getText();
        return msg;







    }

}