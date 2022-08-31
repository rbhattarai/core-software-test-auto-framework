package components;

import contracts.IComponent;
import org.openqa.selenium.WebDriver;

public class ComponentFactory {
    protected WebDriver driver;

    public ComponentFactory(WebDriver driver) {
        this.driver = driver;
    }

    public IComponent getComponent(ComponentType componentType) {
        switch (componentType) {
            case BUTTON:
                return new Button(driver).getComponent();
            case CHECKBOX:
                return new Checkbox(driver).getComponent();
            case DROPDOWN:
                return new Dropdown(driver).getComponent();
            case TEXTBOX:
                return new Textbox(driver).getComponent();
            case TEXTAREA:
                return new Textarea(driver).getComponent();
            default:
                return new Button(driver).getComponent();
        }
    }
}
