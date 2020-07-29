package chapter3;

import chapter2.abscractionList.Predicate;
import chapter3.standardFunctionalInterface.ConsumerProcess;
import chapter3.standardFunctionalInterface.FunctionProcess;
import chapter3.standardFunctionalInterface.PredicateProcess;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by jyami on 2020/07/29
 */
public class StandardFunctionalInterfaceTest {
    @Test
    @DisplayName("predicate: T 객체를 인수로 받아 boolean을 반환한다.")
    void predicate() {
        List<String> list = Arrays.asList("a","b","c","d","aa","bb","cc","dd");
        Predicate<String> lengthOnePredicate = (String s) -> s.length() == 1;
        List<String> filter = PredicateProcess.filter(list, lengthOnePredicate);
        assertThat(filter.size()).isEqualTo(4);
    }

    @Test
    @DisplayName("consumer: T 객체를 인수로 받아 void를 반환한다.")
    void consumer() {
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        Consumer<Integer> printConsumer = i -> System.out.println(i);
        ConsumerProcess.forEach(list, printConsumer);
    }

    @Test
    @DisplayName("function: T 객체를 인수로 받아 R 객체를 반환한다.")
    void function() {
        List<String> list = Arrays.asList("1","2","3","4","5");
        Function<String, Integer> parseIntegerFunction = s -> Integer.parseInt(s);
        List<Integer> map = FunctionProcess.map(list, parseIntegerFunction);
        assertThat(map.get(0)).isEqualTo(1);
    }
}
