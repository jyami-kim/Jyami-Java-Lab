package chapter15;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

/**
 * Created by jyami on 2021/01/10
 */
public class ArithmeticCellTest {
    @Test
    @DisplayName("pub-sub 상호작용")
    void cellAddExample() {
        ArithmeticCell c3 = new ArithmeticCell("c3"); //subscriber
        SimpleCell c2 = new SimpleCell("c2"); // publisher
        SimpleCell c1 = new SimpleCell("c1");

//        // 이거 동작안하는데 왜 이런 예제를...
//        c2.subscribe(c3::setLeft);
//        c1.subscribe(c3::setRight);

        c1.onNext(10);
        c2.onNext(20);
        c1.onNext(15);
    }
}