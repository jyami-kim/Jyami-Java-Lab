package chapter5;

import chapter4.Dish;
import chapter4.DishFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by jyami on 2020/08/01
 */
public class SearchAndMatchingTest {
    @Test
    @DisplayName("적어도 한 요소와 일치")
    void name() {
        boolean b = DishFactory.menu.stream()
                .anyMatch(Dish::isVegetarian);
        assertThat(b).isTrue();
    }

    @Test
    @DisplayName("모든 요소와 일치하는지 검사")
    void name2() {
        boolean b = DishFactory.menu.stream()
                .allMatch(Dish::isVegetarian);
        assertThat(b).isFalse();
    }

    @Test
    @DisplayName("현재 스트림에서 임의의 요소 반환")
    void name3() {
        Optional<Dish> any = DishFactory.menu.stream()
                .filter(Dish::isVegetarian)
                .findAny();
        System.out.println(any.get().getName());
    }

    @Test
    @DisplayName("현재 스트림에서 임의의 요소 반환")
    void name4() {
        Optional<Dish> any = DishFactory.menu.stream()
                .filter(Dish::isVegetarian)
                .findFirst();
        System.out.println(any.get().getName());
    }
}
