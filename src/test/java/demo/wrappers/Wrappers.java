package demo.wrappers;

import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class Wrappers {

    WebDriver driver;

    public Wrappers(WebDriver driver) {
        this.driver = driver;
    }

    // Wrapper for clicking elements
    public void clickElement(By locator) {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    // Wrapper for scrolling
    public void scrollToEndOfPage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    // Method to scroll to the extreme right using Left of a WebElement
    public void scrollToExtremeRight(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollLeft = arguments[0].scrollWidth;", element);
    }    

    // Wrapper for waiting until visibility of an element
    public WebElement waitForVisibility(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Wrapper for waiting until element is clickable
    public WebElement waitForClickability(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Wrapper for sending keys
    public void enterText(By locator, String text) {
        WebElement element = waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }

    // Wrapper for getting text
    public String getText(By locator) {
        WebElement element = waitForVisibility(locator);
        String text = element.getText();
        return text;
    }

    public WebElement findElement(By locator){

        WebElement locatedElemented = waitForVisibility(locator);
         
        return locatedElemented;
    }


}
