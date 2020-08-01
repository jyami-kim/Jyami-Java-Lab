package chapter5;

import chapter4.Dish;
import chapter4.DishFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jyami on 2020/08/01
 */
public class Quiz0501Test {

    @Test
    @DisplayName("스트림을 이용해서 처음 등장하는 두 고기요리 필터링")
    void name() {
        List<Dish> menu = DishFactory.menu;

        List<Dish> collect = menu.stream()
                .filter(d -> d.getType() == Dish.Type.MEAT)
                .limit(2)
                .collect(Collectors.toList());

        System.out.println(collect);
    }
}
