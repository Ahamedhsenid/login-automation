package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By employeeList = By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div/div/div/nb-sidebar/div/div/nb-menu/ul/li[2]/a");
    private By newRegistration = By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div/div/div/div/div/nb-layout-column/div/ngx-employee-list-main/div/div[6]/button");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setEmployeeList() {
        WebElement employeeElement = driver.findElement(employeeList);
        employeeElement.click();
    }

    public void setNewRegistration() {
        WebElement registrationElement = driver.findElement(newRegistration);
        registrationElement.click();
    }
}