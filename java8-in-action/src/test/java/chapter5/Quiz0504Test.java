package chapter5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by jyami on 2020/08/01
 */
public class Quiz0504Test {
    @Test
    @DisplayName("스트림을 이용한 피보나치 수열 만들기")
    void name() {
        Stream.iterate(new int[]{0, 1}, x -> new int[]{x[1], x[0] + x[1]})
                .limit(20)
                .forEach(t -> System.out.println(Arrays.toString(t)));
    }

    @Test
    @DisplayName("일반적인 형식으로 피보나치 수열 만들기")
    void name2() {
        List<Integer> collect = Stream.iterate(new int[]{0, 1}, x -> new int[]{x[1], x[0] + x[1]})
                .limit(20)
                .map(x -> x[0])
                .collect(Collectors.toList());
        System.out.println(collect);
    }

}
