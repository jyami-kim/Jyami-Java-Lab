package chapter5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by jyami on 2020/08/12
 */
public class PythagoreanTest {
    @Test
    @DisplayName("피타고라스 수 찾기")
    void name() {

        Stream<int[]> stream = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(
                        a -> IntStream.rangeClosed(1, 100)
                                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                );

        stream.limit(5)
                .forEach(t -> System.out.println(Arrays.toString(t)));


    }
}
