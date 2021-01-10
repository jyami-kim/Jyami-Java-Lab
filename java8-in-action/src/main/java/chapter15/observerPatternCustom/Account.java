package chapter15.observerPatternCustom;

/**
 * Created by jyami on 2021/01/10
 */
public interface Account { // observable
    void subscribe(Platform platform);
    void unSubscribe(Platform platform);
    void notifyCrew(String msg);
}

