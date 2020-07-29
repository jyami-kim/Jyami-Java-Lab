package chapter3;

import chapter1.methodReference.Apple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

/**
 * Created by jyami on 2020/07/29
 */
public class TypeInferenceTest {

    @Test
    @DisplayName("형식 추론을 하지 않는다")
    void name() {
        Comparator<Apple> c = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
    }

    @Test
    @DisplayName("형식 추론을 한다.")
    void name2() {
        Comparator<Apple> c = (a1, a2) -> a1.getWeight().compareTo(a2.getWeight());
    }
}
