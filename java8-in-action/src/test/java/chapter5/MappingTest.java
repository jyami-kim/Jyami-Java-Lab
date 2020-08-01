package chapter5;

import chapter4.Dish;
import chapter4.DishFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by jyami on 2020/08/01
 */
public class MappingTest {

    @Test
    @DisplayName("스트림의 각 요소에 함수 적용 : 요리명의 길이를 알고 싶을 때")
    void name() {
        List<Integer> collect = DishFactory.menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    @DisplayName("스트림 평면화")
    void name2() {
        String[] str = {"Hello","World"};
        List<String> collect = Arrays.stream(str)
                .map(s -> s.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(collect);
    }
}
