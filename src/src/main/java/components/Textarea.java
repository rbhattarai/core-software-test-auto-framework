package components;

import contracts.IComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Textarea extends BaseComponent implements IComponent {
    public Textarea(WebDriver driver) {
        super(driver);
    }

    @Override
    public IComponent getComponent() {
        return this::getComponent;
    }

    @Override
    public void type(By locator, String value) {
        WebElement textarea = wait.until(ExpectedConditions.elementToBeClickable(locator));
        if (textarea.isDisplayed() && textarea.isEnabled()) {
            textarea.sendKeys(Keys.TAB);
            textarea.clear();
            textarea.sendKeys(value);
        }
        wait.until(ExpectedConditions.textToBePresentInElementValue(locator, value));
    }

}
