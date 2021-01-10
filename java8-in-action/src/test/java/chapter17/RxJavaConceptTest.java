package chapter17;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by jyami on 2021/01/11
 */
public class RxJavaConceptTest {
    @Test
    @DisplayName("Observable just 메서드 : Observable의 Subscriber는" +
            " onNext(first) onNext(second) onComplete() 순서로 메서드를 받는다.")
    void observableJust() {
        Observable<String> just = Observable.just("first", "second");
    }

    @Test
    @DisplayName("Observable interval 메서드 : 지정된 속도로 이벤트를 방출하는 상황에서 사용한다." +
            "0에서 시작해 1초 간격으로 long 형식의 값을 무한으로 증가시키며 값을 방출한다.")
    void observableInterval() {
        Observable<Long> interval = Observable.interval(1, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("RxJava Observer: 많은 오버로드로 인해 유연하게 구현이 가능하다.")
    void observableIntervalWithObserver() {
        Observable<Long> onePerSec = Observable.interval(1, TimeUnit.SECONDS);
        onePerSec.subscribe(i -> System.out.println(TempInfo.fetch("Seoul")));
    }
}
