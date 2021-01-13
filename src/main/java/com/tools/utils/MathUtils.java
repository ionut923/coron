package com.tools.utils;

import java.lang.reflect.Field;
import java.util.List;

public class MathUtils {
    public static Double formatDoubleToTwoDecimals(Double value) {
        String strDouble = String.format("%.2f", value);
        return Double.parseDouble(strDouble.replace("NaN", "0.0"));
    }

    public static <T> double getObjectDoubleFieldValue(Object object, String fieldName) throws NoSuchFieldException, SecurityException,
            IllegalArgumentException,
            IllegalAccessException {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        Field objectField = object.getClass().getDeclaredField(fieldName);
        objectField.setAccessible(true);
        return objectField.getDouble(object);
    }

    @SuppressWarnings("null")
    public static List<Integer> getObjectsFieldValues(List<Object> objects, String fieldName) throws SecurityException,
            IllegalArgumentException {
        List<Integer> values = null;
        objects.forEach(object -> {
            try {
                Field field = object.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                Field objectField = object.getClass().getDeclaredField(fieldName);
                objectField.setAccessible(true);
                values.add(objectField.getInt(object));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return values;
    }

}