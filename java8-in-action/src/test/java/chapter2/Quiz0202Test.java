package chapter2;

import chapter2.quiz2.MeaningOfThis;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Created by jyami on 2020/07/26
 */
@DisplayName("익명 클래스로 헷갈림을 유도하는 코드 정답 맞추기 quiz 2-2")
public class Quiz0202Test {
    @Test
    void name() {
        MeaningOfThis meaningOfThis = new MeaningOfThis();
        meaningOfThis.doit();
    }
}
