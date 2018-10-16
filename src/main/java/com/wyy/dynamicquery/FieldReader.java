package com.wyy.dynamicquery;

import java.lang.reflect.Field;

/**
 * @author wyy
 * @date 18-10-16
 * @time 下午4:17
 */
public class FieldReader<T> {

    private Object object;

    public FieldReader(T object) {
        assert object != null;
        this.object = object;
    }

    public Object readField(String fieldName) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(object);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e.getCause());
        }

    }
}
