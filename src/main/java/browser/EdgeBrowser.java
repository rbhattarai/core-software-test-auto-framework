package browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeBrowser extends Browser {
    EdgeBrowser(WebDriver driver) {
        super(driver);
    }

    @Override
    public WebDriver getBrowserDriver() {
        System.setProperty("webdriver.edge.driver", "src/test/resources/drivers/msedgedriver.exe");
        var options = new EdgeOptions();
        options.setCapability("-inprivate", true);
        driver = new EdgeDriver(options);
        return driver;
    }
}
