package chapter2;

import chapter1.methodReference.Apple;
import chapter2.behaviorParameterize.ApplePredicate;
import chapter2.behaviorParameterize.FilteringApple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by jyami on 2020/07/26
 */
@DisplayName("익명클래스를 이용한 구현 : 클래스 선언과 인스턴스화 동시에 수행")
public class AnonymouseClassTest {

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
    void name() {
        List<Apple> redApples = FilteringApple.filterApples(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return "red".equals(apple.getColor());
            }
        });
        assertThat(redApples.size()).isEqualTo(2);
    }
}
