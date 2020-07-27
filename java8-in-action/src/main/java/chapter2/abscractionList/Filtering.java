package chapter2.abscractionList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jyami on 2020/07/26
 */
public class Filtering {
    public static <T> List<T> filter(List<T> list, Predicate<T> p){
        List<T> result = new ArrayList<>();
        for(T e: list){
            if(p.test(e))
                result.add(e);
        }
        return result;
    }
}
