package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PermissionGroupPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By unregisteredName = By.xpath("//*[@id=\"cdk-overlay-2\"]/nb-dialog-container/ngx-admin-create/div/nb-card/nb-card-body/form/div[1]/div[3]/div[1]/input");
    private By registeredName = By.xpath("/html[1]/body[1]/ngx-app[1]/ngx-pages[1]/ngx-one-column-layout[1]/nb-layout[1]/div[2]/div[2]/div[1]/nb-dialog-container[1]/ngx-admin-create[1]/div[1]/nb-card[1]/nb-card-body[1]/form[1]/div[1]/div[3]/div[2]/nb-form-field[1]/div[2]/input[1]");
    private By email = By.xpath("/html[1]/body[1]/ngx-app[1]/ngx-pages[1]/ngx-one-column-layout[1]/nb-layout[1]/div[2]/div[2]/div[1]/nb-dialog-container[1]/ngx-admin-create[1]/div[1]/nb-card[1]/nb-card-body[1]/form[1]/div[2]/div[3]/input[1]");
    private By role = By.xpath("//*[@id=\"cdk-overlay-2\"]/nb-dialog-container/ngx-admin-edit/div/nb-card/nb-card-body/form/div[4]/div[3]/nb-select/button");


    private By office = By.xpath("//*[@id=\"cdk-overlay-2\"]/nb-dialog-container/ngx-admin-edit/div/nb-card/nb-card-body/form/div[5]/div[3]/nb-select/button");







    public PermissionGroupPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void setName(String name){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement nameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(unregisteredName));
        nameElement.sendKeys(name);
    }

    public void setEmail(String emailId){
        driver.findElement(email).sendKeys(emailId);
    }

    public void setRole(String roleName){
        driver.findElement(role).click();
        WebElement roleElement = driver.findElement(By.xpath("//*[contains(text(), '" + roleName + "')]"));
        roleElement.click();
    }



    public void setOffice(List<String> labels) {
        for (String label : labels) {
            try {
                // Locate the label element containing the given text
                driver.findElement(office).click();
                WebElement checkboxLabel = driver.findElement(By.xpath(
                        "//nb-option-list//span[contains(text(), '" + label + "')]/ancestor::nb-checkbox/label"
                ));

                // Check if the checkbox is already selected (optional, to avoid unnecessary clicks)
                WebElement checkbox = checkboxLabel.findElement(By.xpath("ancestor::nb-checkbox"));
                boolean isChecked = checkbox.getAttribute("class").contains("checked");

                // If it's not selected, click the checkbox
                if (!isChecked) {
                    checkboxLabel.click();
                    System.out.println("Checkbox with label '" + label + "' selected.");
                } else {
                    System.out.println("Checkbox with label '" + label + "' is already selected.");
                }

            } catch (Exception e) {
                System.err.println("Checkbox with label '" + label + "' not found or could not be selected.");
            }
        }
    }


}