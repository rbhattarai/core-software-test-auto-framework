package browser;

import org.openqa.selenium.WebDriver;

public class BrowserFactory {
    private WebDriver driver;

    public WebDriver getBrowser(BrowserName browserName) {
        switch(browserName) {
            case CHROME:
                return new ChromeBrowser(driver).getBrowserDriver();
            case EDGE:
                return new EdgeBrowser(driver).getBrowserDriver();
            case FIREFOX:
                return new FirefoxBrowser(driver).getBrowserDriver();
            default:
                return new EdgeBrowser(driver).getBrowserDriver();
        }
    }
}
