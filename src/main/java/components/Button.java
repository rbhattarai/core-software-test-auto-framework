package components;

import contracts.IComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Button extends BaseComponent implements IComponent {
    public Button(WebDriver driver) {
        super(driver);
    }

    @Override
    public IComponent getComponent() {
        return this;
    }

    @Override
    public void click(By locator) {
        WebElement button = driver.findElement(locator);
        this.waitElement(locator);
        if(button.isDisplayed() && button.isEnabled()) {
            wait.until(ExpectedConditions.elementToBeClickable(button));
            try {
                button.click();
            } catch (Exception e) {
                util.clickByJavaScript(locator);
            }
        }
    }
}
