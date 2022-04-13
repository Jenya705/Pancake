package com.github.jenya705.pancake.test;

import com.github.jenya705.pancake.annotation.Name;
import com.github.jenya705.pancake.annotation.NotField;
import com.github.jenya705.pancake.annotation.Vision;

/**
 * @author Jenya705
 */
@Name("test-service")
public class TestService {

    @Vision
    private String mode = "default";

    @NotField
    private int notField;

    @Name("good-name")
    private long someNotGoodName = 1;

    public void setMode(String mode) {
        System.out.printf("My mode changed %s", mode);
        this.mode = mode;
    }

    public long getSomeNotGoodName() {
        System.out.println("Someone getting field with not good name");
        return someNotGoodName;
    }
}
