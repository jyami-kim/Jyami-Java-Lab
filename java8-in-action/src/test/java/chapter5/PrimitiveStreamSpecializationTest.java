package chapter5;

import chapter4.Dish;
import chapter4.DishFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.in;

/**
 * Created by jyami on 2020/08/12
 */
public class PrimitiveStreamSpecializationTest {
    @Test
    @DisplayName("숫자 스트림으로 매핑")
    void name() {
        List<Dish> menu = DishFactory.menu;

        int sum = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();

        assertThat(sum).isEqualTo(4200);
    }

    @Test
    @DisplayName("객체 스트림으로 복원하기")
    void name2() {
        List<Dish> menu = DishFactory.menu;

        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
        Stream<Integer> boxed = intStream.boxed();
    }

    @Test
    @DisplayName("OptionalInt")
    void name3() {
        List<Dish> menu = DishFactory.menu;
        OptionalInt max = menu.stream()
                .mapToInt(Dish::getCalories)
                .max();
    }

    @Test
    @DisplayName("숫자 범위")
    void name4() {
        IntStream intStream = IntStream.rangeClosed(1, 100)
                .filter(i -> i % 2 == 0);
    }
}
