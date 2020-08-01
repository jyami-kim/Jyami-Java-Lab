package chapter5;

import chapter4.DishFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Created by jyami on 2020/08/01
 */
public class Quiz0503Test {
    @Test
    @DisplayName("map과 reduce를 사용하여 스트림의 요리 개수를 계산하시오")
    void name() {
        Integer reduce = DishFactory.menu.stream()
                .map(x -> 1)
                .reduce(0, Integer::sum);
        System.out.println(reduce);
    }
}
