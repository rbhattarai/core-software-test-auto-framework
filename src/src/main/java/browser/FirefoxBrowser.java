package browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxBrowser extends Browser {

    FirefoxBrowser(WebDriver driver) {
        super(driver);
    }

    @Override
    public WebDriver getBrowserDriver() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        if (driver == null) {
            System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
            driver = new FirefoxDriver();
            var options = new FirefoxOptions();
            options.setCapability("-inprivate", true);
            options.addArguments("--remote-allow-origins=*");
            driver = new FirefoxDriver(options);
        }
        return driver;
    }
}
