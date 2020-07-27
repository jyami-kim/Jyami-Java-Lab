package chapter2;

import chapter1.methodReference.Apple;
import chapter2.quiz1.AppleFancyFormatter;
import chapter2.quiz1.AppleSimpleFormatter;
import chapter2.quiz1.Quiz0201;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by jyami on 2020/07/26
 */
@DisplayName("formatter 를 만들어보는 quiz 2-1")
public class Quiz0201Test {

    List<Apple> inventory = Collections.emptyList();

    @BeforeEach
    void setUp() {
        inventory = Collections.unmodifiableList(Arrays.asList(
                new Apple("green", 10),
                new Apple("red", 160),
                new Apple("green", 200),
                new Apple("red", 300)));
    }

    @Test
    void prettyPrintAppleWithSimpleFormatter(){
        Quiz0201.prettyPrintApple(inventory, new AppleSimpleFormatter());
    }

    @Test
    void prettyPrintAppleWithFancyFormatter(){
        Quiz0201.prettyPrintApple(inventory, new AppleFancyFormatter());
    }
}