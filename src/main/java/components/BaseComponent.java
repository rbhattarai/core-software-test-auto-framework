package components;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utilities;

import java.time.Duration;
import java.util.Locale;

public class BaseComponent {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Utilities util;

    public BaseComponent(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(45));
        util = new Utilities(driver);
    }

    public boolean waitForPageLoad() {
        return wait.until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("compete"));
    }

    public String getTagName(By locator) {
        return driver.findElement(locator).getTagName().toLowerCase(Locale.ROOT);
    }

    public String getID(By locator) {
        return (!driver.findElement(locator).getAttribute("id").isEmpty()) ? driver.findElement(locator).getAttribute("id") : driver.findElement(locator).getAttribute("name");
    }

    public void waitElement(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitDisplayed(final WebElement element) {
        try {
            wait.until((ExpectedCondition<Boolean>) driver -> element.isDisplayed());
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void waitEnabled(final WebElement element) {
        try {
            wait.until((ExpectedCondition<Boolean>) driver -> element.isEnabled());
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void waitSelected(final WebElement element) {
        try {
            wait.until((ExpectedCondition<Boolean>) driver -> element.isSelected());
        } catch (Exception e) {
            e.getMessage();
        }
    }

}
