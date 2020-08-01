package chapter5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by jyami on 2020/08/01
 */
public class Quiz0502Test {
    @Test
    @DisplayName("숫자 리스트가 주어졌을 때 각 숫자의 제곱근으로 이루어진 리스트 반환")
    void name() {
        List<Integer> collect = Stream.of(1, 2, 3, 4, 5)
                .map(n -> n * n)
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    @DisplayName("두개의 맵을 이용해서 두 리스트의 숫자쌍을 만들어라")
    void name2() {
        List<Integer> source1 = Arrays.asList(1, 2, 3);
        List<Integer> source2 = Arrays.asList(4, 5);

        List<int[]> collect = source1.stream()
                .flatMap(x -> source2.stream()
                        .map(y -> new int[]{x, y})
                )
                .collect(Collectors.toList());

        collect.forEach(c -> System.out.println(Arrays.toString(c)));
    }

    @Test
    @DisplayName("이전 것에서 합이 3으로 나누어지는 쌍만 반환")
    void name3() {
        List<Integer> source1 = Arrays.asList(1, 2, 3);
        List<Integer> source2 = Arrays.asList(4, 5);

        List<int[]> collect = source1.stream()
                .flatMap(x -> source2.stream()
                        .filter(y -> (x + y) % 3 == 0)
                        .map(y -> new int[]{x, y}))
                .collect(Collectors.toList());

        collect.forEach(c -> System.out.println(Arrays.toString(c)));
    }
}
