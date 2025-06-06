package contracts;

import org.openqa.selenium.By;
import org.openqa.selenium.UnsupportedCommandException;

public interface IButton {
    default void click(By locator) {
        throw new UnsupportedCommandException();
    }
}
