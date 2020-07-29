package chapter3.standardFunctionalInterface;

import java.util.List;
import java.util.function.Consumer;

/**
 * Created by jyami on 2020/07/29
 */
public class ConsumerProcess {
    public static <T> void forEach(List<T> list, Consumer<T> c){
        for(T l : list){
            c.accept(l);
        }
    }
}
