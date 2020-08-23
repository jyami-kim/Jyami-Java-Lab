package chapter5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by jyami on 2020/08/01
 */
public class ReducingTest {
    @Test
    @DisplayName("모든 요소의 곱 : 초기값 + BinaryOperator")
    void name() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        int production = list.stream()
                .reduce(1, (a, b) -> a * b);

        int summary = list.stream()
                .reduce(0, Integer::sum);

        assertThat(production).isEqualTo(120);
        assertThat(summary).isEqualTo(15);
    }

    @Test
    @DisplayName("초기값 없음 : reduce 는 합계를 반환할 수 없다.")
    void name1() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> reduce = list.stream()
                .reduce(Integer::sum);
        assertThat(reduce).isEqualTo(Optional.empty());
    }

    @Test
    @DisplayName("최댓값 최솟값")
    void name3() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> max = list.stream().reduce(Integer::max);
    }
}
