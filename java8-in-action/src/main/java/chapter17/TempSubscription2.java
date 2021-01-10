package chapter17;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;

/**
 * Created by jyami on 2021/01/10
 */
public class TempSubscription2 implements Flow.Subscription {

    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    private final Flow.Subscriber<? super TempInfo> subscriber;
    private final String town;

    public TempSubscription2(Flow.Subscriber<? super TempInfo> subscriber, String town) {
        this.subscriber = subscriber;
        this.town = town;
    }

    @Override
    public void request(long n) {
        executor.submit(() -> {
            for (long i = 0L; i < n; i++) {
                try {
                    subscriber.onNext(TempInfo.fetch(town)); // 현재 온도를 Subscriber로 전달
                }catch (Exception e){
                    subscriber.onError(e); // 온도 가져오기를 실패하면 Subscriber로 에러 전달
                    break;
                }
            }
        });
    }

    @Override
    public void cancel() {

    }
}
