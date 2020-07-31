package chapter4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * Created by jyami on 2020/07/31
 */
public class StreamPipelineTest {

    @Test
    @DisplayName("내부반복을 이용해 이루어지는 스트림 연산")
    void name() {
        List<String> collect = DishFactory.menu.stream()
                .filter(d -> d.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    @DisplayName("스트림 특징 :lazy - 최종연산을 스트림 파이프라인에 실행하기 전까지 아무 연산 수행 X")
    void name2() {
        List<String> collect = DishFactory.menu.stream()
                .filter(d -> {
                    System.out.println("filtering" + d.getName());
                    return d.getCalories() > 300;
                })
                .map(d -> {
                    System.out.println("mapping" + d.getName());
                    return d.getName();
                })
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(collect);
    }
}