package components;

import contracts.IComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

public class Dropdown extends BaseComponent implements IComponent {

    public Dropdown(WebDriver driver) {
        super(driver);
    }

    private By getDropdown(String id) {
        return By.cssSelector("[id="+ id +"]");
    }

    private By getDropdownButtonIcon(String id) {
        return By.cssSelector("button[id="+id+"]");
    }

    private By getDropdownListWhenExpanded(String id) {
        return By.cssSelector("ul#" + id + "-list.show");
    }

    @Override
    public IComponent getComponent() {
        return this;
    }

    @Override
    public void select(By locator, String value) {
        String tag = getTagName(locator);
        String id = getID(locator);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(this.getDropdown(id))));
        switch (tag) {
            case "input":
                this.expandDropdownAndSelect(this.getDropdownButtonIcon(id), this.getDropdownListWhenExpanded(id), value);
                break;
            case "select":
            default:
                this.selectValueFromDropdown(this.getDropdown(id), value);
        }
    }

    private void selectValueFromDropdown(By locator, Object obj) {
        WebElement element = driver.findElement(locator);
        this.waitElement(locator);
        Select select = new Select(element);
        if (obj instanceof String) {
            select.selectByVisibleText((String) obj);
        } else if (obj instanceof Integer) {
            select.selectByIndex((int) obj);
        } else {
            select.selectByValue((String) obj);
        }
    }

    private void expandDropdownAndSelect(By dropdownButton, By ulListLocator, String value) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        wait.until(ExpectedConditions.elementToBeClickable(dropdownButton));
        driver.findElement(dropdownButton).click();
        wait.withTimeout(Duration.ofSeconds(10));
        List<WebElement> options = driver.findElement(ulListLocator).findElements(By.cssSelector("li"));
        for (WebElement opt : options) {
            String optText = opt.getText();
            if(optText.equals(value)) {
                try {
                    opt.click();
                    if (!opt.isSelected()) {
                        js.executeScript("arguments[0].click()", opt);
                    }
                } catch (Exception e) {
                    js.executeScript("arguments[0].scrollIntoView()", opt);
                    if (!opt.isSelected()) {
                        js.executeScript("arguments[0].click()", opt);
                    }
                }
                break;
            }
        }
    }
}
