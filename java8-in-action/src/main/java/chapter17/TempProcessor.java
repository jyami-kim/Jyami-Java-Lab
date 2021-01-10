package chapter17;

import java.util.concurrent.Flow;

/**
 * Created by jyami on 2021/01/10
 */
public class TempProcessor implements Flow.Processor<TempInfo, TempInfo> {

    private Flow.Subscriber<? super TempInfo> subscriber;

    @Override
    public void subscribe(Flow.Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public void onNext(TempInfo tempInfo) {
        int fTemp = (((tempInfo.getTemp() - 32) * 5) / 9); // 섭씨로 변환한 다음 TempInfo 다시 전송
        subscriber.onNext(new TempInfo(tempInfo.getTown(), fTemp));
    }

    // 다른 모든 신호는 업스트림 구독자에게 전달.
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        subscriber.onSubscribe(subscription);
    }

    @Override
    public void onError(Throwable throwable) {
        subscriber.onError(throwable);
    }

    @Override
    public void onComplete() {
        subscriber.onComplete();
    }
}
