package chapter4;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by jyami on 2020/07/31
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Dish {
    private String name;
    private boolean vegetarian;
    private int calories;
    private Type type;

    public enum Type {
        MEAT, FISH, OTHER
    }
}
