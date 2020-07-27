package chapter2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Created by jyami on 2020/07/26
 */
@DisplayName("실전 예제 Runnable로 실행할 코드블럭 지정하기")
public class RunnableTest {

    @Test
    @DisplayName("익명클래스 이용")
    void name() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("helloWorld");
            }
        });
        t.run();
    }

    @Test
    @DisplayName("람다식 이용")
    void name2() {
        Thread t = new Thread(() -> System.out.println("helloWorld"));
        t.run();
    }

}
