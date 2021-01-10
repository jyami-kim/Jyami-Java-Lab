package chapter15;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

/**
 * Created by jyami on 2021/01/10
 */
public class SimpleCellTest {
    @Test
    @DisplayName("구독관계 확인 : C1은 Publisher / C3은 Subscriber > publisher 값이 변하면 subscriber에 이벤트가 간다")
    void simpleCell() {
        SimpleCell c3 = new SimpleCell("C3");
        SimpleCell c2 = new SimpleCell("C2");
        SimpleCell c1 = new SimpleCell("C1");

        c1.subscribe(c3);

        c1.onNext(10);
        c2.onNext(20);

//        C1:10{
//        C3:10
//        C2:20
    }
}