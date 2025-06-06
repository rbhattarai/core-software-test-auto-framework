package components;

import contracts.IComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Textbox extends BaseComponent implements IComponent {
    public Textbox(WebDriver driver) {
        super(driver);
    }

    @Override
    public IComponent getComponent() {
        return this::getComponent;
    }

    @Override
    public void type(By locator, String value) {
        WebElement textbox = wait.until(ExpectedConditions.elementToBeClickable(locator));
        if (textbox.isDisplayed() && textbox.isEnabled()) {
            textbox.click();
            textbox.sendKeys(value);
        }
        wait.until(ExpectedConditions.textToBePresentInElementValue(locator, value));
    }

    @Override
    public void submit(By locator) {
        WebElement textbox = wait.until(ExpectedConditions.elementToBeClickable(locator));
        if (textbox.isDisplayed() && textbox.isEnabled()) {
            textbox.submit();
        }
    }

}
