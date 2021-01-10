package chapter15.observerPatternCustom;

/**
 * Created by jyami on 2021/01/10
 */
public class Facebook implements Platform {
    @Override
    public void update(String msg) {
        System.out.println("FaceBook 수신 : " + msg);
    }
}
