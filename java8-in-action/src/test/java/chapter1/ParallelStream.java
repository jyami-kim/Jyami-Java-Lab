package chapter1;

import chapter1.methodReference.Apple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jyami on 2020/07/26
 */
@DisplayName("스트림과 람다표현식을 이용하면, 병렬로 필터링이 가능하다.")
public class ParallelStream {

    List<Apple> inventory = Collections.emptyList();

    @BeforeEach
    void setUp() {
        List<Apple> apples = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            apples.add(new Apple("red", i));
        }
        inventory = Collections.unmodifiableList(apples);
    }

    @Test
    @DisplayName("순차 처리 방식의 코드를 이용한 필터링 ")
    void streamProcess() {
        long startTime = System.currentTimeMillis();

        List<Apple> heavyApples = inventory.stream()
                .filter(a -> a.getWeight() > 150)
                .collect(Collectors.toList());

        long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println(elapsedTime); // 10

    }

    @Test
    @DisplayName("병렬 처리 방식의 코드를 이용한 필터링 ")
    void parallelStreamProcess() {
        long startTime = System.currentTimeMillis();

        List<Apple> heavyApples = inventory.parallelStream()
                .filter(a -> a.getWeight() > 150)
                .collect(Collectors.toList());

        long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println(elapsedTime); // 18
    }

    /**
     * 이펙티브 자바에 따르면 병렬 스트림을 사용할때 종단연산으로 collectors와 같이 모으는 연산을 할 때
     * 흩어져 있던, 결과물들을 합치는데 걸리는 시간이 들어, 순차 처리방식보다 오래걸릴 수 있다 했음.
     */
}
