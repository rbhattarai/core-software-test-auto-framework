package components;

import contracts.IComponent;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class Checkbox extends BaseComponent implements IComponent {

    public Checkbox(WebDriver driver) {
        super(driver);
    }

    @Override
    public IComponent getComponent() {
        return this;
    }

    @Override
    public void check(By locator) {
        WebElement checkbox = driver.findElement(locator);
        if (!checkbox.isSelected()) {
            wait.withTimeout(Duration.ofSeconds(5));
            try {
                checkbox.click();
            } catch (Exception e) {
                wait.ignoring(StaleElementReferenceException.class)
                        .ignoring(NoSuchElementException.class)
                        .ignoring(TimeoutException.class)
                        .withTimeout(Duration.ofSeconds(45))
                        .until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(locator)));
                checkbox.click();
            }
            if(getTagName(locator).equals("input")) {
                wait.until(ExpectedConditions.elementToBeSelected(checkbox));
            } else {
                waitSelected(checkbox);
            }
        }
    }

    @Override
    public void uncheck(By locator) {
        WebElement checkbox = driver.findElement(locator);
        if (checkbox.isSelected()) {
            wait.withTimeout(Duration.ofSeconds(5));
            try {
                checkbox.click();
            } catch (Exception e) {
                wait.ignoring(StaleElementReferenceException.class)
                        .ignoring(NoSuchElementException.class)
                        .ignoring(TimeoutException.class)
                        .withTimeout(Duration.ofSeconds(45))
                        .until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(locator)));
                checkbox.click();
            }
        }
    }

    @Override
    public boolean isChecked(By locator) {
        String checkboxAttribute = driver.findElement(locator).getAttribute("ischecked");
        return checkboxAttribute.equals("checked");
    }


}
