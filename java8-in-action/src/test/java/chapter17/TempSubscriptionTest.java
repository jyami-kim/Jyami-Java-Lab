package chapter17;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Flow;

import static org.junit.Assert.*;

/**
 * Created by jyami on 2021/01/10
 */
public class TempSubscriptionTest {

    @Test
    @DisplayName("재귀호출 방식의 Subscription.request")
    void name() {
        getTemperatures("Seoul").subscribe(new TempSubscriber()); // 서울에 Publisher를 만들고 TempSubscriber를 구독
    }

    private static Flow.Publisher<TempInfo> getTemperatures(String town){ // Publisher.subscribe() 메서드 구현
        return subscriber -> subscriber.onSubscribe(new TempSubscription(subscriber, town));
        // 구독한 Subscriber에게 TempSubscription을 전송하는 Publisher를 반환
    }
}