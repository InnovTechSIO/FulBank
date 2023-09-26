package fr.innovtech.fulbank.annotations;

import java.lang.annotation.*;
import java.lang.reflect.Field;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.FIELD})
public @interface NotNull {
}

class NullFieldException extends Exception {
    public NullFieldException(String message) {
        super(message);
    }
}



