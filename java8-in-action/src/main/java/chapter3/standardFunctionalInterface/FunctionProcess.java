package chapter3.standardFunctionalInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Created by jyami on 2020/07/29
 */
public class FunctionProcess {
    public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for(T s: list){
            result.add(f.apply(s));
        }
        return result;
    }
}
