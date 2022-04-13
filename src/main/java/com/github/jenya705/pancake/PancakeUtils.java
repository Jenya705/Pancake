package com.github.jenya705.pancake;

import com.github.jenya705.pancake.annotation.Name;
import com.github.jenya705.pancake.annotation.Vision;
import lombok.experimental.UtilityClass;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author Jenya705
 */
@UtilityClass
public class PancakeUtils {

    public String getName(Class<?> clazz) {
        Name annotation = clazz.getAnnotation(Name.class);
        if (annotation != null) {
            return annotation.value();
        }
        String className = clazz.getName();
        return className.substring(className.lastIndexOf('.') + 1);
    }

    public String getName(Field field) {
        Name annotation = field.getDeclaredAnnotation(Name.class);
        if (annotation != null) {
            return annotation.value();
        }
        return field.getName();
    }

    public boolean isVisible(Class<?> clazz) {
        Vision vision = clazz.getAnnotation(Vision.class);
        if (vision != null) {
            return vision.value();
        }
        return true;
    }

    public boolean isVisible(Field field) {
        Vision vision = field.getDeclaredAnnotation(Vision.class);
        if (vision != null) {
            return vision.value();
        }
        return false;
    }

    public Method getGetMethod(Field field, Class<?> clazz) throws NoSuchMethodException {
        return clazz
                .getMethod("get" +
                        Character.toUpperCase(field.getName().charAt(0)) +
                        field.getName().substring(1)
                );
    }

    public Method getSetMethod(Field field, Class<?> clazz) throws NoSuchMethodException {
        return clazz
                .getMethod("set" +
                        Character.toUpperCase(field.getName().charAt(0)) +
                        field.getName().substring(1), field.getDeclaringClass()
                );
    }

    public Method getGetMethodNullable(Field field, Class<?> clazz) {
        try {
            return getGetMethod(field, clazz);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public Method getSetMethodNullable(Field field, Class<?> clazz) {
        try {
            return getSetMethod(field, clazz);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

}
