package contracts;

import org.openqa.selenium.By;
import org.openqa.selenium.UnsupportedCommandException;

public interface ICheckbox {
    default void check(By locator){
        throw new UnsupportedCommandException();
    }

    default void uncheck(By locator){
        throw new UnsupportedCommandException();
    }

    default boolean isChecked(By locator){
        throw new UnsupportedCommandException();
    }

}
