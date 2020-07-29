package chapter3;

import chapter3.ComposeFunctionalInterface.Letter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * Created by jyami on 2020/07/29
 */
public class LetterComposeTest {
    @Test
    @DisplayName("addHeader -> checkSpelling -> addFooter")
    void name() {
        Function<String, String> addHeader = Letter::addHeader;
        Function<String, String> transformationPipeline =
                addHeader.andThen(Letter::checkSpelling)
                        .andThen(Letter::addFooter);

        String hello = transformationPipeline.apply("labda");
        assertThat(hello).isEqualTo("From Raoul, Mario and Alan: lambda Kind regards");
    }

    @Test
    @DisplayName("addHeader -> addFooter")
    void name2() {
        Function<String, String> addHeader = Letter::addHeader;
        Function<String, String> transformationPipeline
                = addHeader.andThen(Letter::addFooter);

        String hello = transformationPipeline.apply("hello im jyami");
        assertThat(hello).isEqualTo("From Raoul, Mario and Alan: hello im jyami Kind regards");
    }
}
