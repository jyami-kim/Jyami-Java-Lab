package chapter15.observerPatternCustom;

/**
 * Created by jyami on 2021/01/10
 */
public class Main {
    public static void main(String[] args){
        Jyami jyami = new Jyami();
        Platform blog = new Blog();
        Platform facebook = new Facebook();
        Platform instagram = new Instagram();

        jyami.subscribe(blog);
        jyami.subscribe(facebook);
        jyami.subscribe(instagram);

        jyami.publishText();

//        jyami.unSubscribe(facebook);
//
//        jyami.publishText();
    }
}
