package browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class ChromeBrowser extends Browser {

    ChromeBrowser(WebDriver driver) {
        super(driver);
    }

    @Override
    public WebDriver getBrowserDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
            driver = new ChromeDriver();
            var options = new ChromeOptions();
            options.setCapability("-inprivate", true);
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
        }
        return driver;
    }
}
