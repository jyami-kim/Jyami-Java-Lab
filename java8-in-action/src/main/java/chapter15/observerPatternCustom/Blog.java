package chapter15.observerPatternCustom;

/**
 * Created by jyami on 2021/01/10
 */
public class Blog implements Platform {
    @Override
    public void update(String msg) {
        System.out.println("Blog 수신 : " + msg);
    }
}
