package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EmployeeCreationPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions action;

    private By lName = By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div[1]/div/div/div/div/nb-layout-column/div/ngx-employee-reg/nb-card/nb-card-body/form/div/div[1]/div[1]/div[1]/div[2]/input");
    private By fName = By.xpath("/html[1]/body[1]/ngx-app[1]/ngx-pages[1]/ngx-one-column-layout[1]/nb-layout[1]/div[1]/div[1]/div[1]/div[1]/div[1]/nb-layout-column[1]/div[1]/ngx-employee-reg[1]/nb-card[1]/nb-card-body[1]/form[1]/div[1]/div[1]/div[1]/div[2]/div[2]/input[1]");
    private By DOB = By.xpath("/html[1]/body[1]/ngx-app[1]/ngx-pages[1]/ngx-one-column-layout[1]/nb-layout[1]/div[1]/div[1]/div[1]/div[1]/div[1]/nb-layout-column[1]/div[1]/ngx-employee-reg[1]/nb-card[1]/nb-card-body[1]/form[1]/div[1]/div[1]/div[4]/div[2]/div[1]/input[1]");
    private By radioBtn = By.xpath("/html[1]/body[1]/ngx-app[1]/ngx-pages[1]/ngx-one-column-layout[1]/nb-layout[1]/div[1]/div[1]/div[1]/div[1]/div[1]/nb-layout-column[1]/div[1]/ngx-employee-reg[1]/nb-card[1]/nb-card-body[1]/form[1]/div[1]/div[1]/div[3]/div[2]/div[1]/nb-radio-group[1]/nb-radio[1]/label[1]/span[2]");
    private By office = By.xpath("/html[1]/body[1]/ngx-app[1]/ngx-pages[1]/ngx-one-column-layout[1]/nb-layout[1]/div[1]/div[1]/div[1]/div[1]/div[1]/nb-layout-column[1]/div[1]/ngx-employee-reg[1]/nb-card[1]/nb-card-body[1]/form[1]/div[1]/div[2]/div[1]/div[2]/nb-select[1]/button[1]");
    private By officeSelect = By.xpath("//*[@id=\"nb-option-137\"]");
    private By department = By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div[1]/div/div/div/div/nb-layout-column/div/ngx-employee-reg/nb-card/nb-card-body/form/div/div[2]/div[2]/div[2]/nb-select/button");
    private By depSelect = By.xpath("//*[@id=\"nb-option-157\"]");
    private By empNo = By.xpath("/html[1]/body[1]/ngx-app[1]/ngx-pages[1]/ngx-one-column-layout[1]/nb-layout[1]/div[1]/div[1]/div[1]/div[1]/div[1]/nb-layout-column[1]/div[1]/ngx-employee-reg[1]/nb-card[1]/nb-card-body[1]/form[1]/div[1]/div[2]/div[7]/div[2]/input[1]");
    private By status = By.xpath("/html[1]/body[1]/ngx-app[1]/ngx-pages[1]/ngx-one-column-layout[1]/nb-layout[1]/div[1]/div[1]/div[1]/div[1]/div[1]/nb-layout-column[1]/div[1]/ngx-employee-reg[1]/nb-card[1]/nb-card-body[1]/form[1]/div[1]/div[2]/div[15]/nb-radio-group[1]/nb-radio[1]/label[1]/span[2]");
    private By saveBtn = By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div[1]/div/div/div/div/nb-layout-column/div/ngx-employee-reg/nb-card/nb-card-body/div[2]/div[1]/button");
    private By registration = By.xpath("//*[@id=\"cdk-overlay-4\"]/nb-dialog-container/nb-card/nb-card-body");
    private By regBtn = By.xpath("//*[@id=\"cdk-overlay-4\"]/nb-dialog-container/nb-card/nb-card-body/div[3]/div/button");
    private By regError = By.xpath("//*[@id=\"cdk-overlay-5\"]/nb-dialog-container/ngx-error-popup/nb-card/nb-card-body");
    private By employeeTitle = By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div[1]/div/div/div/nb-layout-header/nav/ngx-header/div[1]/label");

    public EmployeeCreationPage(WebDriver driver, Actions action) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.action = action;
    }

    public void setlName(String name){
        WebElement lastNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(lName));
        lastNameElement.sendKeys(name);
    }

    public void setfName(String firstname){
        WebElement firstNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(fName));
        firstNameElement.sendKeys(firstname);
    }

    public void setDOB(String dob){
        WebElement DobElement = wait.until(ExpectedConditions.visibilityOfElementLocated(DOB));
        DobElement.sendKeys(dob);
    }

    public void setRadioBtn(){
        WebElement radioBtnElement = wait.until(ExpectedConditions.visibilityOfElementLocated(radioBtn));
        radioBtnElement.click();
    }

    public void setOffice(){
        WebElement officeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(office));
        officeElement.click();
    }

    public void setOfficeSelect() {
        action.scrollToElement(driver.findElement(officeSelect));
        WebElement selectElement = driver.findElement(officeSelect);
        selectElement.click();
    }

    public void setDepartment() {
        WebElement departmentElement = driver.findElement(department);
        departmentElement.click();
    }

    public void setDepSelect() {
        action.scrollToElement(driver.findElement(depSelect));
        WebElement depSelectElement = driver.findElement(depSelect);
        depSelectElement.click();
    }

    public void setEmpNo(String empNum){
        WebElement empNoElement = wait.until(ExpectedConditions.visibilityOfElementLocated(empNo));
        empNoElement.sendKeys(empNum);
    }

    public void setStatus() {
        WebElement statusElement = wait.until(ExpectedConditions.visibilityOfElementLocated(status));
        statusElement.click();
    }

    public void setSaveBtn() {
        WebElement saveElement = wait.until(ExpectedConditions.visibilityOfElementLocated(saveBtn));
        saveElement.click();
    }

    public void setRegBtn() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(registration));
        action.scrollToElement(driver.findElement(regBtn));
        WebElement regElement = driver.findElement(regBtn);
        regElement.click();
    }

    public void setRegError(){
        WebElement regErrorElement = driver.findElement(regError);
    }

    public void setEmployeeTitle(){
        WebElement employeeTitleElement = driver.findElement(employeeTitle);
    }
}
