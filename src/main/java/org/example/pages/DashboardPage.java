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
    private By language = By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div/div/div/div/nb-layout-header/nav/ngx-header/div[2]/nb-actions/nb-action/div");
    private By langSelect = By.xpath("//*[contains(text(), 'English')]");
    private By settings = By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div/div/div/nb-sidebar/div/div/nb-menu/ul/li[14]/a");
    private By basicInfoSettings = By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div[1]/div/div/nb-sidebar/div/div/nb-menu/ul/li[14]/ul/li[3]/a");
    private By add = By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div/div/div/div/div/nb-layout-column/div/ngx-basic-information-setting/nb-card/nb-card-body/nb-tabset/ul/li[2]/a");

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
}

