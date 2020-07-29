package chapter3;

import chapter1.methodReference.Apple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by jyami on 2020/07/29
 */
public class FunctionalInterfaceChaining {

    List<Apple> inventory = Collections.emptyList();

    @BeforeEach
    void setUp() {
        inventory = Collections.unmodifiableList(Arrays.asList(
                new Apple("green", 10),
                new Apple("red", 160),
                new Apple("green", 200),
                new Apple("red", 300)));
    }

    @Test
    @DisplayName("Comparator 연결")
    void name() {
        inventory.sort(
                Comparator.comparing(Apple::getWeight)
                        .reversed()
                        .thenComparing(Apple::getColor));
    }

    @Test
    @DisplayName("Predicate 연결")
    void name2() {
        Predicate<Apple> redApple = a -> "red".equals(a.getWeight());

        Predicate<Apple> notRedApple = redApple.negate();

        Predicate<Apple> redAndHeavyApple = redApple.and(a -> a.getWeight() > 150);

        Predicate<Apple> redAndHeavyAppleOrGreen = redApple
                .and(a -> a.getWeight() > 150)
                .or(a -> "green".equals(a.getColor()));
    }

    @Test
    @DisplayName("Function 연결")
    void name3() {
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g); // g(f(x))
        int result = h.apply(1);
        assertThat(result).isEqualTo(4);
    }

    @Test
    @DisplayName("Function 연결")
    void name4() {
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.compose(g); // f(g(x))
        int result = h.apply(1);
        assertThat(result).isEqualTo(3);
    }
}
