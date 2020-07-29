package chapter3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.IntPredicate;
import java.util.function.Predicate;

/**
 * Created by jyami on 2020/07/29
 */
@DisplayName("오토박싱 동작을 피할 수 있도록 만든 함수형 인터페이스")
public class AutoBoxingTest {

    @Test
    @DisplayName("오토박싱이 일어나지 않는다.")
    void intPredicate() {
        IntPredicate evenNumber = (int i) -> i % 2 == 0;
        evenNumber.test(1);
    }

    @Test
    @DisplayName("오토박싱이 일어난다.")
    void predicate() {
        Predicate<Integer> evenNumber = (Integer i) -> i % 2 == 0;
        evenNumber.test(1);
    }
}
