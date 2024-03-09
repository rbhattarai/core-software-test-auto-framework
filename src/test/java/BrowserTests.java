import browser.BrowserFactory;
import browser.BrowserName;
import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BrowserTests {
    static WebDriver browser = null;

    @BeforeAll
    public static void before() {
        browser = new BrowserFactory().getBrowser(BrowserName.EDGE);
        browser.manage().window().maximize();
    }

    @AfterAll
    public static void after() {
        browser.quit();
    }

    @Test
    public void testGoogleSearch() {
        String searchText = "Java Design Patterns";

        browser.get("http://www.google.com");
        WebElement searchBox = browser.findElement(By.name("q"));
        searchBox.click();
        searchBox.sendKeys(searchText);
        searchBox.submit();
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.titleContains(searchText));
        List<WebElement> allHeaders = browser.findElements(By.cssSelector("* h3"));
        List<String> headings = this.getValues(allHeaders);
        Assert.isTrue(headings.contains(searchText), "Heading didn't match");
    }

    private List<String> getValues(List<WebElement> elements) {
        List<String> values = new ArrayList<>();
        for (WebElement e : elements) {
            values.add(e.getText());
        }
        return values;
    }


}
