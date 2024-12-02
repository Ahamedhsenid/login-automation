package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InterviewManagementPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By interviewManagement = By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div/div/div/nb-sidebar/div/div/nb-menu/ul/li[6]/a/span");
    private By Addinterview = By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div/div/div/div/div/nb-layout-column/div/ngx-interview-management/div[2]/div/div[1]/button");

    private By EmpNum = By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div[2]/div[2]/div/nb-dialog-container/nb-card/nb-card-body/form/div[2]/div[3]/nb-form-field/div[2]/input");



    public InterviewManagementPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }


    public void clickinterviewManagement(){
        WebElement interviewManagementElement =  wait.until(ExpectedConditions.visibilityOfElementLocated(interviewManagement));
        interviewManagementElement.click();
    }

    public void clickaddinterview(){
        WebElement clickaddinterviewElement =  wait.until(ExpectedConditions.visibilityOfElementLocated(Addinterview));
        clickaddinterviewElement.click();
    }

    public void setemployeeNum(String employeeNum){
        WebElement employeeNumElement =  wait.until(ExpectedConditions.visibilityOfElementLocated(EmpNum));
//        employeeNumElement.clear();
        employeeNumElement.sendKeys(employeeNum);


    }








}