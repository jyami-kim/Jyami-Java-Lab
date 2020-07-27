package chapter2;

import chapter1.methodReference.Apple;
import chapter2.abscractionList.Filtering;
import chapter2.behaviorParameterize.AppleGreenColorPredicate;
import chapter2.behaviorParameterize.AppleHeavyWeightPredicate;
import chapter2.behaviorParameterize.FilteringApple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.in;


/**
 * Created by jyami on 2020/07/26
 */
@DisplayName("Predicate 설정에 따라 동작이 달라지는 동작 파라미터")
public class FilteringAppleTest {

    List<Apple> inventory = new ArrayList<>();

    @BeforeEach
    void setUp() {
        inventory = Arrays.asList(new Apple("green", 80),
                new Apple("green", 155),
                new Apple("red", 120));
    }

    @Test
    @DisplayName("predicate가 ApplePredicate 로 한정되어있다.")
    void filteringApple() {


        List<Apple> heavyApples = FilteringApple.filterApples(inventory, new AppleHeavyWeightPredicate());
        List<Apple> greenApples = FilteringApple.filterApples(inventory, new AppleGreenColorPredicate());

        assertThat(heavyApples.size()).isEqualTo(1);
        assertThat(greenApples.size()).isEqualTo(2);
    }


}