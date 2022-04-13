package com.github.jenya705.pancake.test;

import com.github.jenya705.pancake.Pancake;
import com.github.jenya705.pancake.field.PancakeField;
import com.github.jenya705.pancake.service.PancakeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * @author Jenya705
 */
public class GenericServiceTests {

    @Test
    public void fieldsTest() {
        Pancake pancake = new Pancake();
        PancakeService service = pancake.service(new TestService());
        Assertions.assertEquals("test-service", service.getName());
        Assertions.assertTrue(service.isVisible());
        Assertions.assertEquals(Optional.of("mode"), service.getField("mode").map(PancakeField::getName));
        Assertions.assertEquals(Optional.empty(), service.getField("notField"));
        Assertions.assertEquals(Optional.of("good-name"), service.getField("good-name").map(PancakeField::getName));
        Assertions.assertEquals(Optional.of("default"), service.getField("mode").map(PancakeField::getValue));
        Assertions.assertEquals(Optional.of(1L), service.getField("good-name").map(PancakeField::getValue));
    }

}
