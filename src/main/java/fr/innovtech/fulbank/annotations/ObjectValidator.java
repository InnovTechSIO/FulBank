package fr.innovtech.fulbank.annotations;

import java.lang.reflect.Field;

public class ObjectValidator {
    public static void validateNotNullFields(Object obj) throws IllegalAccessException, NullFieldException {
        Class<?> clazz = obj.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(NotNull.class)) {
                field.setAccessible(true);
                if (field.get(obj) == null) {
                    throw new NullFieldException("Field '" + field.getName() + "' cannot be null.");
                }
            }
        }
    }
}
