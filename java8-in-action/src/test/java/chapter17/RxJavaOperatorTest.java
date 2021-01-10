package chapter17;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by jyami on 2021/01/11
 */
public class RxJavaOperatorTest {

    @Test
    @DisplayName("좀더 일반화를 위해 온도를 직접 출력하지 않고 " +
            "사용자에게 팩토리 메서드를 제공해 매초마다 온도를 방출하는 Observable을 만들어보자" +
            "이렇게 할 경우 observer는 그저 받은 데이터만 출력하는 기능을 하면된다." +
            "observable로 온도를 얻는 과정에서 에러와 완료처리 로직을 다 구현했기 때문에 ")
    void observableExample() {
        Observable<TempInfo> seoul = getTemperature("Seoul");
        seoul.blockingSubscribe(new TempObserver());
    }

    @Test
    @DisplayName("Observable의 연산자를 이용해 스트림을 유동적으로 처리할 수 있다." +
            "filter 를 이용해 섭씨온도 영하만 필터링하고 " +
            "map을 이용해 화씨를 섭씨로 변환한다")
    void getMinusCelsiusTemperature() {
        Observable<TempInfo> seoul = getTemperature("Seoul")
                .filter(temp -> temp.getTemp()<0)
                .map(temp -> new TempInfo(temp.getTown(), ((temp.getTemp() - 32) * 5) / 9));
        seoul.blockingSubscribe(new TempObserver());
    }

    @Test
    @DisplayName("여러 Observable 스트림을 Merge 하는 연산도 가능하다.")
    void ObservableMergeStream() {
        Observable<TempInfo> celsiusTemperatures = getCelsiusTemperatures("Paju", "Seoul", "Pangyo");
        celsiusTemperatures.blockingSubscribe(new TempObserver());
    }

    public static Observable<TempInfo> getCelsiusTemperatures(String... towns){
        return Observable.merge(Arrays.stream(towns)
                .map(RxJavaOperatorTest::getTemperature)
                .collect(Collectors.toList())
        );
    }

    public static Observable<TempInfo> getTemperature(String town){
        return Observable.create(emitter ->
                Observable.interval(1, TimeUnit.SECONDS) // 매 초마다 무한으로 증가하는 long 반환 Observable
                        .subscribe(i -> {
                            if(!emitter.isDisposed()){ // 소비된 옵저버가 폐기되지 않았으면 어떤 작업을 수행(에러가 안났으면)
                                if(i >= 5){ // 5번 온도를 onNext 했으면 성공처리 후 종료
                                    emitter.onComplete();
                                }else {
                                    try {
                                        emitter.onNext(TempInfo.fetch(town)); // 온도를 observer로 보
                                    }catch (Exception e){
                                        emitter.onError(e);// 에러가 발생하면 Observer에게 알림
                                    }
                                }
                            }
                        })
        );
    }

    public class TempObserver implements Observer<TempInfo> {

        @Override
        public void onSubscribe(@NonNull Disposable d) {
        }

        @Override
        public void onNext(@NonNull TempInfo tempInfo) {
            System.out.println(tempInfo);
        }

        @Override
        public void onError(@NonNull Throwable e) {
            System.err.println("Got Problem " + e.getMessage());
        }

        @Override
        public void onComplete() {
            System.out.println("Done!");
        }
    }

}
