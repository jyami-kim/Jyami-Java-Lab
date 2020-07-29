package chapter1.methodReference;

import lombok.Getter;

/**
 * Created by jyami on 2020/07/26
 */
@Getter
public class Apple {
    private String color;
    private Integer weight;

    public Apple() {
    }

    public Apple(String color) {
        this.color = color;
    }

    public Apple(Integer weight) {
        this.weight = weight;
    }

    public Apple(String color, Integer weight) {
        this.color = color;
        this.weight = weight;
    }
}
