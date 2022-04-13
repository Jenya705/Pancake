package com.github.jenya705.pancake.field;

import com.github.jenya705.pancake.PancakeUtils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Jenya705
 */
@Getter
class AnnotationPancakeField implements PancakeField {

    @Getter(AccessLevel.PRIVATE)
    private final Field field;

    @Getter(AccessLevel.PRIVATE)
    private final Object source;

    private final Method get;
    private final Method set;
    private final String name;
    private final boolean visible;

    public AnnotationPancakeField(Field field, Object source) {
        this.field = field;
        this.source = source;
        this.get = PancakeUtils.getGetMethodNullable(field, source.getClass());
        this.set = PancakeUtils.getSetMethodNullable(field, source.getClass());
        if (get == null || set == null) {
            field.setAccessible(true);
        }
        this.name = PancakeUtils.getName(field);
        this.visible = PancakeUtils.isVisible(field);
    }

    @Override
    public Class<?> getValueClass() {
        return field.getDeclaringClass();
    }

    @Override
    @SneakyThrows
    public Object getValue() {
        if (get == null) {
            return field.get(source);
        }
        return get.invoke(source);
    }

    @Override
    public void setValue(Object value) {
        try {
            if (set == null) {
                field.set(source, value);
            }
            else {
                set.invoke(source, value);
            }
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
