package chapter3;

import chapter1.methodReference.Apple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by jyami on 2020/07/29
 */
@DisplayName("생성자 레퍼런스")
public class ConstructorReference {

    @Test
    @DisplayName("기본생성자 레퍼런스")
    void name4() {
        Supplier<Apple> appleSupplierLambda = () -> new Apple();
        Supplier<Apple> appleSupplier = Apple::new;
    }

    @Test
    @DisplayName("인수 1개 생성자 레퍼런스 : 무게")
    void name2() {
        Function<Integer, Apple> appleFunctionLambda = (Integer weight) -> new Apple(weight);
        Function<Integer, Apple> appleFunction = Apple::new;
    }
    @Test
    @DisplayName("인수 1개 생성자 레퍼런스 : 색깔")
    void name3() {
        Function<String, Apple> appleFunctionLambda = (String color) -> new Apple(color);
        Function<String, Apple> appleFunction = Apple::new;
    }

    @Test
    @DisplayName("인수 2개 생성자 레퍼런스")
    void name() {

        BiFunction<String, Integer, Apple> appleBiFunctionLambda = (color, weight) -> new Apple(color, weight);
        BiFunction<String, Integer, Apple> appleBiFunction = Apple::new;
    }
}
