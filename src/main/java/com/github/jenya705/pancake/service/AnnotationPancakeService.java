package com.github.jenya705.pancake.service;

import com.github.jenya705.pancake.PancakeUtils;
import com.github.jenya705.pancake.annotation.NotField;
import com.github.jenya705.pancake.field.PancakeField;
import lombok.AccessLevel;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author Jenya705
 */
@Getter
class AnnotationPancakeService implements PancakeService {

    @Getter(AccessLevel.PRIVATE)
    private final Object source;

    private final String name;
    private final boolean visible;
    private final Collection<PancakeField> fields;

    public AnnotationPancakeService(@NotNull Object source) {
        this.source = source;
        this.name = PancakeUtils.getName(source.getClass());
        this.visible = PancakeUtils.isVisible(source.getClass());
        this.fields = Arrays
                .stream(source.getClass().getDeclaredFields())
                .filter(field -> field.getAnnotation(NotField.class) == null)
                .map(field -> PancakeField.byAnnotation(
                        field,
                        Modifier.isStatic(field.getModifiers()) ? null : source
                ))
                .toList();
    }

}
