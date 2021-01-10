package chapter15;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;

/**
 * Created by jyami on 2021/01/10
 */
public class SimpleCell implements Flow.Publisher<Integer>, Flow.Subscriber<Integer> {
    private int value =0;
    private String name;
    private List<Flow.Subscriber> subscribers = new ArrayList<>();

    public SimpleCell(String name) {
        this.name = name;
    }

    // Publisher 메서드
    @Override
    public void subscribe(Flow.Subscriber<? super Integer> subscriber) {
        subscribers.add(subscriber);
    }

    //Subscriber 메서드
    @Override
    public void onNext(Integer newValue) {
        this.value = newValue; // 구독한 셀에서 새로운 값이 생겻을 때 값을 갱신한다.
        System.out.println(this.name + ":" + this.value);
        notifyAllSubscribers(); // 값이 갱신됌을 모든 구독자에게 알림
    }

    private void notifyAllSubscribers(){ // 새로운 값이 있음을 모든 구독자에게 알리는 메서드
        subscribers.forEach(subscriber -> subscriber.onNext(this.value));
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
}
