package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class StressAssessmentPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By stressCheckName = By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div/div/div/div/div/nb-layout-column/div/ngx-stress-check-create/nb-card/div/form/div[1]/div[2]/div/input");
    private By startDate = By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div[1]/div/div/div/div/nb-layout-column/div/ngx-stress-check-create/nb-card/div/form/div[2]/div[2]/div/input[1]");
    private By endDate = By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div[1]/div/div/div/div/nb-layout-column/div/ngx-stress-check-create/nb-card/div/form/div[2]/div[2]/div/input[2]");
    private By question = By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div[1]/div/div/div/div/nb-layout-column/div/ngx-stress-check-create/nb-card/div/form/div[3]/div[2]/div/nb-select/button");
    private By submitBtn = By.xpath("/html/body/ngx-app/ngx-pages/ngx-one-column-layout/nb-layout/div[1]/div/div/div/div/nb-layout-column/div/ngx-stress-check-create/nb-card/div/form/button/span");
    private By confirmationBtn = By.xpath("//*[@id=\"cdk-overlay-3\"]/nb-dialog-container/nb-card/nb-card-body/div[4]/button[2]");
    private By errorMessage = By.xpath("//*[@id=\"cdk-overlay-6\"]/nb-toastr-container");

    public StressAssessmentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void stressName(String name){
        driver.findElement(stressCheckName).sendKeys(name);
    }

    public void stressStartDate(String date){
        driver.findElement(startDate).sendKeys(date);
    }

    public void stressEndDate(String date){
        driver.findElement(endDate).sendKeys(date);
    }

    public void stressQuestion(String questions){
        driver.findElement(question).click();
        WebElement questionsElement = driver.findElement(By.xpath("//*[contains(text(), '" + questions + "')]"));
        questionsElement.click();
    }

    public void clickSubmitBtn(){
        driver.findElement(submitBtn).click();
    }


    public boolean submitBtnEnabled(){
        driver.findElement(submitBtn).isEnabled();
        return false;
    }

    public void clickConfirmationBtn(){
        driver.findElement(confirmationBtn).click();
    }

    public String stressErrorMessage(){
        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        String msg = message.getText();
        return msg;
    }

    public List stressQuestionQty(){
        driver.findElement(question).click();
        List<WebElement> options = driver.findElements(By.xpath("//*[@id=\"cdk-overlay-2\"]/nb-option-list"));
        return options;
    }
}