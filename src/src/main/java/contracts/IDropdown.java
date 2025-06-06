package contracts;

import org.openqa.selenium.By;
import org.openqa.selenium.UnsupportedCommandException;

public interface IDropdown {
    default void select(By locator, String value){
        throw new UnsupportedCommandException();
    }
}
