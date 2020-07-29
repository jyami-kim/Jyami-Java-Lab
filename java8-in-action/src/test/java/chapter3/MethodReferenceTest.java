package chapter3;

import chapter1.methodReference.Apple;
import chapter3.functionalInterface.ExampleFunctionalInterface;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by jyami on 2020/07/29
 */
@DisplayName("람다 포현식보단 메서드 레퍼런스를 사용하는 것이 가독성이 좋다")
public class MethodReferenceTest {
    @Test
    @DisplayName("1. 정적 메서드 레퍼런스")
    void name() {
        Function<String, Integer> stringIntegerFunctionLambda = (String str) -> Integer.parseInt(str);
        Function<String, Integer> stringIntegerFunction = Integer::parseInt;
    }

    @Test
    @DisplayName("2. 인스턴스 메서드 레퍼런스")
    void name2() {
        Function<String, Integer> stringIntegerFunctionLambda = (String arg0) -> arg0.length();
        Function<String, Integer> stringIntegerFunction = String::length;
    }

    @Test
    @DisplayName("3. 기존 객체의 인스턴스 메서드 레퍼런스")
    void name3() {
        Apple apple = new Apple("red", 100);
        Supplier<Integer> supplierLambda = () -> apple.getWeight();
        Supplier<Integer> supplier = apple::getWeight;
    }

}
