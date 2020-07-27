package chapter2;

import chapter1.methodReference.Apple;
import chapter2.abscractionList.Filtering;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by jyami on 2020/07/26
 */
@DisplayName("predicate 에 형식 파라미터 T를 추가하여 추상화")
public class PredicateAbstractionTest {

    List<Apple> inventory = new ArrayList<>();

    @BeforeEach
    void setUp() {
        inventory = Arrays.asList(new Apple("green", 80),
                new Apple("green", 155),
                new Apple("red", 120));
    }

    @Test
    @DisplayName("red 컬러인 Apple로 필터링")
    void name() {
        List<Apple> redApples = Filtering.filter(inventory, apple -> "red".equals(apple.getColor()));
        assertThat(redApples.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("predicate의 타입이 추상화 되어있어, String 필터도 사용가능")
    void name2() {
        List<Integer> list = Arrays.asList(0, 1, 2, 3, 4, 5, 6);
        List<Integer> evenNumbers = Filtering.filter(list, i -> i % 2 == 0);
        assertThat(evenNumbers.size()).isEqualTo(4);
    }
}
