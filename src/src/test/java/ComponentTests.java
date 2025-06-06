import browser.BrowserFactory;
import browser.BrowserName;
import components.ComponentFactory;
import components.ComponentType;
import components.Textbox;
import contracts.IComponent;
import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ComponentTests {
    static  WebDriver driver;
    static  IComponent textbox;
    static  IComponent button;

    @BeforeAll
    public static void before() {
        driver = new BrowserFactory().getBrowser(BrowserName.EDGE);
        textbox  = new ComponentFactory(driver).getComponent(ComponentType.TEXTBOX);
        button = new ComponentFactory(driver).getComponent(ComponentType.BUTTON);
        driver.manage().window().maximize();
    }

    @AfterAll
    public static void after() {
        driver.quit();
    }

    @Test
    public void testGoogleSearch() {
        String searchText = "Java Design Patterns";
        By bySearchTextBox = By.name("q");

        driver.get("http://www.google.com");

        Textbox tb = new Textbox(driver);
        tb.type(bySearchTextBox, searchText);
        tb.submit(bySearchTextBox);

//        textbox.type(bySearchTextBox, searchText);
//        textbox.submit(bySearchTextBox);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.titleContains(searchText));
        List<WebElement> allHeaders = driver.findElements(By.cssSelector("* h3"));
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
