package chapter15.observerPatternCustom;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jyami on 2021/01/10
 */
public class Jyami implements Account {
    List<Platform> platforms = new ArrayList<>();
    public void publishText(){
        System.out.println("쟈미가 글을 발행했다");
        notifyCrew("글 발행 전송");
    }
    @Override
    public void subscribe(Platform platform) {
        platforms.add(platform);
    }

    @Override
    public void unSubscribe(Platform platform) {
        platforms.remove(platform);
    }

    @Override
    public void notifyCrew(String msg) {
        platforms.forEach(crew -> crew.update(msg));
    }
}
