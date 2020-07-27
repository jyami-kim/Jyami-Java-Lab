package chapter2;

import chapter1.methodReference.Apple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by jyami on 2020/07/26
 */
@DisplayName("실전 예제 Comparator로 정렬하기")
public class ComparatorTest {

    List<Apple> inventory = Collections.emptyList();

    @BeforeEach
    void setUp() {
        inventory = Arrays.asList(
                new Apple("green", 10),
                new Apple("red", 160),
                new Apple("green", 200),
                new Apple("red", 300));
    }

    @Test
    @DisplayName("익명클래스를 이용한 무게 작은순 사과 정렬")
    void name() {
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });
    }

    @Test
    @DisplayName("람다식을 사용한 무게 작은 순 사과 정렬")
    void name2() {
        inventory.sort((a1, a2)->a1.getWeight().compareTo(a2.getWeight()));
    }
}
