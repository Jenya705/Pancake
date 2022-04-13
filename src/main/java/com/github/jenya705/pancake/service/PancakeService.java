package com.github.jenya705.pancake.service;

import com.github.jenya705.pancake.Named;
import com.github.jenya705.pancake.Visible;
import com.github.jenya705.pancake.field.PancakeField;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Optional;

/**
 * @author Jenya705
 */
public interface PancakeService extends Named, Visible {

    static PancakeService byAnnotations(@NotNull Object source) {
        return new AnnotationPancakeService(source);
    }

    Collection<PancakeField> getFields();

    default Optional<PancakeField> getField(String name) {
        return getFields()
                .stream()
                .filter(field -> field.getName().equals(name))
                .findAny();
    }

}
