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

        List<String> headings = this.getValues(driver.findElements(By.tagName("h3")));
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
