package chatper1;

import chapter1.methodReference.Apple;
import chapter1.lambda.AppleMethodRefProcess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by jyami on 2020/07/26
 */
@DisplayName("사과에 대한 (조건은 미정) 필터링을 거쳐 리스트를 반환하는 프로그래")
public class LambdaTest {

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
    @DisplayName("isGreenApple 메서드를 전달하여 filter 메서드를 동적으로 사용하는 방법")
    void isGreenAppleCall() {
        List<Apple> apples = AppleMethodRefProcess.filterApples(inventory, AppleMethodRefProcess::isGreenApple);
        assertThat(apples.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("isHeavyApple 메서드를 전달하여 filter 메서드를 동적으로 사용하는 방법")
    void isHeavyAppleCall() {
        List<Apple> apples = AppleMethodRefProcess.filterApples(inventory, AppleMethodRefProcess::isHeavyApple);
        assertThat(apples.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("녹색 사과인지 확인하기 위해 람다를 사용하여 filter 메서드에 동작을 전달하는 방법")
    void lambdaGreenApple() {
        List<Apple> apples = AppleMethodRefProcess.filterApples(inventory, apple -> "green".equals(apple.getColor()));
        assertThat(apples.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("무거운 사과인지 확인하기 위해 람다를 사용하여 filter 메서드에 동작을 전달하는 방법")
    void lambdaHeavyApple() {
        List<Apple> apples = AppleMethodRefProcess.filterApples(inventory, apple -> apple.getWeight() > 150);
        assertThat(apples.size()).isEqualTo(3);
    }

}
