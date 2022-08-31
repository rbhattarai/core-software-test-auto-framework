package browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxBrowser extends Browser {

    FirefoxBrowser(WebDriver driver) {
        super(driver);
    }

    @Override
    public WebDriver getBrowserDriver() {
        System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
        driver = new FirefoxDriver();
        return driver;
    }
}
