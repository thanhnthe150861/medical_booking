package selenium.base;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Setup {
    public WebDriver webDriver;

    public WebDriverWait wait;

    public Setup(WebDriver driver) {

        this.webDriver = driver;
        Duration timeout = Duration.ofSeconds(5);
        wait = new WebDriverWait(webDriver,timeout);
    }
    // sendKeys 1 gia tri cho em
    public void setText(By element, String value){
        wait.until(ExpectedConditions.elementToBeClickable(element));
        webDriver.findElement(element).clear();
        webDriver.findElement(element).sendKeys(value);
    }
    public void clickElement(By element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
        webDriver.findElement(element).click();

    }
    public void selecttOptionByText(By element, String text){
        // Chuyen tu doi tuong By sang doi tuong Webelement trên webDriver.findElement
        Select select = new Select(webDriver.findElement(element));
        select.selectByVisibleText(text);
    }

    public void waitForPageloaded(){
        ExpectedCondition<Boolean> expectedCondition = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
            }
        };
        try {
            Duration timeout = Duration.ofSeconds(5);
            WebDriverWait webDriverWait = new WebDriverWait(webDriver, timeout);
            wait.until(expectedCondition);
        }catch (Throwable error){
            Assert.fail("Timeout waiting for Page Load request");

        }
    }

}
