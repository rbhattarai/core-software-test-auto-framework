package browser;

import org.openqa.selenium.WebDriver;

public abstract class Browser {
    protected WebDriver driver;

    Browser(WebDriver driver) {
        this.driver = driver;
    }

    public abstract WebDriver getBrowserDriver();
}
