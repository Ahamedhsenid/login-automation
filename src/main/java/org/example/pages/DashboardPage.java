package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By employeeList = By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div/div/div/nb-sidebar/div/div/nb-menu/ul/li[2]/a");
    private By newRegistration = By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div/div/div/div/div/nb-layout-column/div/ngx-employee-list-main/div/div[6]/button");
    private By language = By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div/div/div/div/nb-layout-header/nav/ngx-header/div[2]/nb-actions/nb-action/div");
    private By langSelect = By.xpath("//*[contains(text(), 'English')]");
    private By settings = By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div/div/div/nb-sidebar/div/div/nb-menu/ul/li[14]/a");
    private By basicInfoSettings = By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div[1]/div/div/nb-sidebar/div/div/nb-menu/ul/li[14]/ul/li[3]/a");
    private By add = By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div/div/div/div/div/nb-layout-column/div/ngx-basic-information-setting/nb-card/nb-card-body/nb-tabset/ul/li[2]/a");
    private By updateEmployee = By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div[1]/div/div/div/div/nb-layout-column/div/ngx-employee-list-main/nb-card/nb-card-body/div/ngx-data-table/div/div[2]/div/div/ng2-smart-table/table/tbody/tr[2]/td[1]/ng2-smart-table-cell/table-cell-view-mode/div/custom-view-component/ngx-table-view-employee/div[3]/label/u");
    private By employeeEditBtn = By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div[1]/div/div/div/div/nb-layout-column/div/ngx-employee-details/nb-card/nb-card-body/div[2]/button");
    private By accountInfoIcon = By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div/div/div/nb-sidebar/div/div/ngx-toggle-button/div[2]/div/span/img");
    private By emailAddressField = By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div/div/div/div/div/nb-layout-column/div/ngx-profile-management/div/div/div/nb-card[1]/nb-card-body/div[5]/div[2]/input");
    private By infoUploadBtn = By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div[1]/div/div/div/div/nb-layout-column/div/ngx-employee-list-main/div/div[5]/button");

   //for physician login
    private By healthCheck = By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div/div/div/nb-sidebar/div/div/nb-menu/ul/li[3]/a/span");
    private By employeeJudgement = By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div/div/div/nb-sidebar/div/div/nb-menu/ul/li[3]/ul/li/a");
    private By employeeJudgementTitle = By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div/div/div/div/nb-layout-header/nav/ngx-header/div[1]/label");
    private By healthcheckTitle = By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div[1]/div/div/div/div/nb-layout-column/div/ngx-emp-decision-list/nb-card/nb-card-body/nb-tabset/ul/li[1]/a");

    //for data check employees

    //no 01 has data
    // private By healthCheckEmployee= By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div[1]/div/div/div/div/nb-layout-column/div/ngx-employee-list-main/nb-card/nb-card-body/div/ngx-data-table/div/div[2]/div/div/ng2-smart-table/table/tbody/tr[2]/td[1]/ng2-smart-table-cell/table-cell-view-mode/div/custom-view-component/ngx-table-view-employee/div[3]/label/u");
    //no 02 no data
//    private By healthCheckEmployee= By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div/div/div/div/div/nb-layout-column/div/ngx-employee-list-main/nb-card/nb-card-body/div/ngx-data-table/div/div[2]/div/div/ng2-smart-table/table/tbody/tr[1]/td[1]/ng2-smart-table-cell/table-cell-view-mode/div/custom-view-component/ngx-table-view-employee/div[3]/label/u");
//no 03 - has data
    private By healthCheckEmployee= By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div[1]/div/div/div/div/nb-layout-column/div/ngx-employee-list-main/nb-card/nb-card-body/div/ngx-data-table/div/div[2]/div/div/ng2-smart-table/table/tbody/tr[3]/td[1]/ng2-smart-table-cell/table-cell-view-mode/div/custom-view-component/ngx-table-view-employee/div[3]/label");

    private By healthResults= By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div[1]/div/div/div/div/nb-layout-column/div/ngx-employee-details/nb-card/nb-card-body/div[5]/nb-tabset/ul/li[1]/a/span");


    //    private By healthData1= By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div[1]/div/div/div/div/nb-layout-column/div/ngx-employee-details/nb-card/nb-card-body/div[5]/nb-tabset/nb-tab[1]/ngx-health-checkup-result/div/div/div/ngx-data-table/div/div[2]/div/div/ng2-smart-table/table/tbody/tr[1]/td[5]/ng2-smart-table-cell/table-cell-view-mode/div/custom-view-component/ngx-details-btn/div/div/div/div[1]/div");
    private By healthData1 = By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div[1]/div/div/div/div/nb-layout-column/div/ngx-employee-details/nb-card/nb-card-body/div[5]/nb-tabset/nb-tab[1]/ngx-health-checkup-result/div/div/div/ngx-data-table/div/div[2]/div/div/ng2-smart-table/table/tbody/tr[1]/td[2]/ng2-smart-table-cell/table-cell-view-mode/div/custom-view-component/ngx-details-btn/div/div/label/nb-icon");
    private By noData= By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div[1]/div/div/div/div/nb-layout-column/div/ngx-employee-details/nb-card/nb-card-body/div[5]/nb-tabset/nb-tab[1]/ngx-health-checkup-result/div/div/div/ngx-data-table/div/div[2]/div/div/ng2-smart-table/table/tbody/tr/td");






    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }



    public void setEmployeeList() {
        WebElement employeeElement = driver.findElement(employeeList);
        employeeElement.click();
    }

    public void setNewRegistration() {
        WebElement registrationElement = driver.findElement(newRegistration);
        registrationElement.click();
    }

    public void setLanguage(){
        WebElement languageElement = driver.findElement(language);
        languageElement.click();
        WebElement languageSelect = driver.findElement(langSelect);
        languageSelect.click();
    }

    public void clickSettings(){
        WebElement settingsElement = driver.findElement(settings);
        settingsElement.click();
        WebElement basicSettingsElement = driver.findElement(basicInfoSettings);
        basicSettingsElement.click();
    }

    public void clickAdd(){
        WebElement addElement = driver.findElement(add);
        addElement.click();
    }



    public void clickUpdateEmployee(){
        WebElement updateElement = driver.findElement(updateEmployee);
        updateElement.click();
    }

    public void clickEmployeeEditBtn(){
        WebElement editElement = wait.until(ExpectedConditions.elementToBeClickable(employeeEditBtn));
        editElement.click();
    }

    public void clickProfileIcon(){
        WebElement iconElement = wait.until(ExpectedConditions.visibilityOfElementLocated(accountInfoIcon));
        iconElement.click();
    }



    public void clickEmployeeInfoUpload(){
        WebElement uploadElement = driver.findElement(infoUploadBtn);
        uploadElement.click();
    }



    public void clickHealthCheck(){
        WebElement healthCheckElement =  wait.until(ExpectedConditions.visibilityOfElementLocated(healthCheck));
        healthCheckElement.click();
    }

    public void clickEmployeeJudgement(){
        WebElement employeeJudgementElement =  wait.until(ExpectedConditions.visibilityOfElementLocated(employeeJudgement));
        employeeJudgementElement.click();
    }

    public void EmployeeJudgementTitle(){
        WebElement employeeJudgementTitleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(employeeJudgementTitle));
        employeeJudgementTitleElement.isDisplayed();
    }
    public void healthcheckTitle(){
        WebElement healthcheckTitleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(healthcheckTitle));
        healthcheckTitleElement.click();
    }
    public void healthCheckEmployee(){
        WebElement healthCheckEmployeeElement = driver.findElement(healthCheckEmployee);
        healthCheckEmployeeElement.click();

    }
    public void healthResults(){
        WebElement healthResultsElement = driver.findElement(healthResults);
        healthResultsElement.click();

    }
//    public void healthData(){
//        WebElement healthDataElement = driver.findElement(healthData);
//        healthDataElement.isDisplayed();
//    }
//
//    public void noData(){
//        WebElement noDataElement = driver.findElement(noData);
//        noDataElement.isDisplayed();
//    }

    public boolean healthData() {
//        try {
//            WebElement healthDataElement = wait.until(ExpectedConditions.visibilityOfElementLocated(healthData1));
//            return healthDataElement.isDisplayed();
//        } catch (NoSuchElementException e) {
//            return false;
//        }
        if (driver.findElement(healthData1).isDisplayed()){
            return true;
        } else {
            return false;
        }
    }

    public boolean noData() {
        try {
            WebElement noDataElement = driver.findElement(noData);
            return noDataElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }




}