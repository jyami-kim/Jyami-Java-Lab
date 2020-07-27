package chapter2.quiz2;

/**
 * Created by jyami on 2020/07/26
 */
public class MeaningOfThis {
    public final int value = 4;
    public void doit(){
        int value = 6;
        Runnable r = new Runnable() {
            public final int value = 5;

            @Override
            public void run() {
                int value = 10;
                System.out.println(this.value);
            }
        };
        r.run();
    }
}
