package contracts;

import org.openqa.selenium.By;
import org.openqa.selenium.UnsupportedCommandException;

public interface ITextbox {
    default void type(By locator, String value){
        throw new UnsupportedCommandException();
    }

    default void submit(By locator){
        throw new UnsupportedCommandException();
    }
}
