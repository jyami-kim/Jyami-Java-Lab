package chapter5;

import chapter4.Dish;
import chapter4.DishFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by jyami on 2020/08/01
 */
public class FilteringAndSlicingTest {
    @Test
    @DisplayName("프레디케이트 필터링")
    void name() {
        List<Dish> collect = DishFactory.menu.stream()
                .filter(Dish::isVegetarian)
                .collect(Collectors.toList());
        assertThat(collect.size()).isEqualTo(4);
    }

    @Test
    @DisplayName("고유요소 필터링")
    void name1() {
        List<Integer> list = Arrays.asList(1, 2, 1, 3, 3, 4, 2);
        list.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    @DisplayName("스트림 축소")
    void name3() {
        List<Dish> collect = DishFactory.menu.stream()
                .filter(x -> x.getCalories() > 300)
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    @DisplayName("요소 건너 뛰기")
    void name4() {
        List<Dish> collect = DishFactory.menu.stream()
                .filter(x -> x.getCalories() > 300)
                .skip(2)
                .collect(Collectors.toList());
        System.out.println(collect);
    }
}
