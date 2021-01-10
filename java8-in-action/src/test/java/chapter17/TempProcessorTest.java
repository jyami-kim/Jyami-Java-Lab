package chapter17;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Flow;

import static org.junit.Assert.*;

/**
 * Created by jyami on 2021/01/10
 */
public class TempProcessorTest {

    @Test
    @DisplayName("processor를 이용한 C=>F 온도 변환")
    void name() {
        getCelsiusTemperatures("Seoul").subscribe(new TempSubscriber());
    }

    public static Flow.Publisher<TempInfo> getCelsiusTemperatures(String town){
        return subscriber -> {
            TempProcessor processor = new TempProcessor();
            processor.subscribe(subscriber);
            processor.onSubscribe(new TempSubscription(processor,town));
        };
    }
}