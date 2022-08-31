import browser.BrowserFactory;
import browser.BrowserName;
import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
        List<String> headings = this.getValues(browser.findElements(By.tagName("h3")));
        Assert.isTrue(headings.contains(searchText.toString()), "Heading didn't match");
    }

    private List<String> getValues(List<WebElement> elements) {
        List<String> values = new ArrayList<>();
        for (WebElement e : elements) {
            values.add(e.getText());
        }
        return values;
    }


}
