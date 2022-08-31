package browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeBrowser extends Browser {

    ChromeBrowser(WebDriver driver) {
        super(driver);
    }

    @Override
    public WebDriver getBrowserDriver() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        return driver;
    }
}
