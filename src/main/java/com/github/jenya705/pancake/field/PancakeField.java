package com.github.jenya705.pancake.field;

import com.github.jenya705.pancake.Named;
import com.github.jenya705.pancake.Visible;

import java.lang.reflect.Field;

/**
 * @author Jenya705
 */
public interface PancakeField extends Named, Visible {

    static PancakeField byAnnotation(Field field, Object source) {
        return new AnnotationPancakeField(field, source);
    }

    Class<?> getValueClass();

    Object getValue();

    void setValue(Object value);

}
