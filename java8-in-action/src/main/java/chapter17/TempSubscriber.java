package chapter17;

import java.util.concurrent.Flow;

/**
 * Created by jyami on 2021/01/10
 */
public class TempSubscriber implements Flow.Subscriber<TempInfo> {

    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1); // 구독 저장하고 첫번째 요청 전달 (1개의 요청을 받을 준비가 되어있다.)
    }

    @Override
    public void onNext(TempInfo tempInfo) {
        System.out.println(tempInfo); // 수신한 온도 출력하고 다음 정보 요청
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        System.err.println(throwable.getMessage()); // 에러 메세지 출력
    }

    @Override
    public void onComplete() {
        System.out.println("Done!");
    }
}
