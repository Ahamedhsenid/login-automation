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
    private By radioBtnMale = By.xpath("/html[1]/body[1]/ngx-app[1]/ngx-pages[1]/ngx-one-column-layout[1]/nb-layout[1]/div[1]/div[1]/div[1]/div[1]/div[1]/nb-layout-column[1]/div[1]/ngx-employee-reg[1]/nb-card[1]/nb-card-body[1]/form[1]/div[1]/div[1]/div[3]/div[2]/div[1]/nb-radio-group[1]/nb-radio[1]/label[1]/span[2]");
    private By radioBtnFemale = By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div[1]/div/div/div/div/nb-layout-column/div/ngx-employee-reg/nb-card/nb-card-body/form/div/div[1]/div[3]/div[2]/div/nb-radio-group/nb-radio[2]/label/span[2]");
    private By office = By.xpath("/html[1]/body[1]/ngx-app[1]/ngx-pages[1]/ngx-one-column-layout[1]/nb-layout[1]/div[1]/div[1]/div[1]/div[1]/div[1]/nb-layout-column[1]/div[1]/ngx-employee-reg[1]/nb-card[1]/nb-card-body[1]/form[1]/div[1]/div[2]/div[1]/div[2]/nb-select[1]/button[1]");
    private By department = By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div[1]/div/div/div/div/nb-layout-column/div/ngx-employee-reg/nb-card/nb-card-body/form/div/div[2]/div[2]/div[2]/nb-select/button");
    private By empNo = By.xpath("/html[1]/body[1]/ngx-app[1]/ngx-pages[1]/ngx-one-column-layout[1]/nb-layout[1]/div[1]/div[1]/div[1]/div[1]/div[1]/nb-layout-column[1]/div[1]/ngx-employee-reg[1]/nb-card[1]/nb-card-body[1]/form[1]/div[1]/div[2]/div[7]/div[2]/input[1]");
    private By operationStatus = By.xpath("/html[1]/body[1]/ngx-app[1]/ngx-pages[1]/ngx-one-column-layout[1]/nb-layout[1]/div[1]/div[1]/div[1]/div[1]/div[1]/nb-layout-column[1]/div[1]/ngx-employee-reg[1]/nb-card[1]/nb-card-body[1]/form[1]/div[1]/div[2]/div[15]/nb-radio-group[1]/nb-radio[1]/label[1]/span[2]");
    private By absenceStatus = By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div[1]/div/div/div/div/nb-layout-column/div/ngx-employee-reg/nb-card/nb-card-body/form/div/div[2]/div[15]/nb-radio-group/nb-radio[2]/label/span[2]");
    private By statusDropdown = By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div[1]/div/div/div/div/nb-layout-column/div/ngx-employee-reg/nb-card/nb-card-body/form/div/div[2]/div[15]/div/nb-select/button");
    private By saveBtn = By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div[1]/div/div/div/div/nb-layout-column/div/ngx-employee-reg/nb-card/nb-card-body/div[2]/div[1]/button");
    private By registration = By.xpath("//*[@id=\"cdk-overlay-4\"]/nb-dialog-container/nb-card/nb-card-body");
    private By regBtn = By.xpath("//*[@id=\"cdk-overlay-5\"]/nb-dialog-container/nb-card/nb-card-body/div[3]/div/button");
    private By regError = By.xpath("//*[@id=\"cdk-overlay-6\"]/nb-dialog-container/ngx-error-popup/nb-card/nb-card-body");
    private By employeeTitle = By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div[1]/div/div/div/nb-layout-header/nav/ngx-header/div[1]/label");
    private By successMessage = By.xpath("//*[@id=\"cdk-overlay-4\"]/nb-toastr-container");

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

    public void selectGender(String gender) {
        if (gender.equalsIgnoreCase("Male")) {
            driver.findElement(radioBtnMale).click();
        } else if (gender.equalsIgnoreCase("Female")) {
            driver.findElement(radioBtnFemale).click();
        }
    }

    public void setOfficeSelect(String officeSelect) {
        WebElement officeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(office));
        officeElement.click();
        action.scrollToElement(driver.findElement(By.xpath("//*[contains(text(), '" + officeSelect + "')]")));
        WebElement selectElement = driver.findElement(By.xpath("//*[contains(text(), '" + officeSelect + "')]"));
        selectElement.click();
    }

    public void setDepSelect(String departmentSelect) {
        WebElement departmentElement = driver.findElement(department);
        departmentElement.click();
        action.scrollToElement(driver.findElement(By.xpath("//*[contains(text(), ' " + departmentSelect + " ')]")));
        WebElement depSelectElement = driver.findElement(By.xpath("//*[contains(text(), '" + departmentSelect + "')]"));
        depSelectElement.click();
    }

    public void setEmpNo(String empNum){
        WebElement empNoElement = wait.until(ExpectedConditions.visibilityOfElementLocated(empNo));
        empNoElement.sendKeys(empNum);
    }

    public void selectStatus(String status) {
        if (status.equalsIgnoreCase("Operation")) {
            driver.findElement(operationStatus).click();
        } else if (status.equalsIgnoreCase("Leave")) {
            driver.findElement(absenceStatus).click();
//            driver.findElement(statusDropdown).click();
//            WebElement depSelectElement = driver.findElement(By.xpath("//*[contains(text(), '"+ leaveValue +"')]"));
//            depSelectElement.click();
        }
    }

    public void setSaveBtn() {
        WebElement saveElement = wait.until(ExpectedConditions.visibilityOfElementLocated(saveBtn));
        if (saveElement.isEnabled()){
            saveElement.click();
        } else {

        }
    }

    public boolean saveBtnEnabled(){
        WebElement saveBtnElement = driver.findElement(saveBtn);
        return saveBtnElement.isEnabled();
    }

    public void setRegBtn() {
//        WebElement regElement = driver.findElement(By.xpath("//*[@id=\"cdk-overlay-4\"]/nb-dialog-container/nb-card/nb-card-body/div[3]/div/button"));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(regBtn));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"cdk-overlay-6\"]/nb-dialog-container/nb-card/nb-card-body")));

        WebElement regElement = wait.until(ExpectedConditions.visibilityOfElementLocated(regBtn));
        action.scrollToElement(regElement);
        regElement.click();
    }

    public void setRegError(){
        WebElement regErrorElement = driver.findElement(regError);
    }

    public void setEmployeeTitle(){
        WebElement employeeTitleElement = driver.findElement(employeeTitle);
    }

    public boolean success(){
        String url = driver.getCurrentUrl();
        if (url.equals("https://qa.pht.hsenidjapan.com/employee-list")){
            return true;
        }else {
            return false;
        }
    }

}

