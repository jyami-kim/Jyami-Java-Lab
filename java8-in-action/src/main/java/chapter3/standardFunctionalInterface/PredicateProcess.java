package chapter3.standardFunctionalInterface;

import chapter2.abscractionList.Predicate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jyami on 2020/07/29
 */
public class PredicateProcess {
    public static <T> List<T> filter(List<T> list, Predicate<T> p){
        List<T> result = new ArrayList<>();
        for(T s: list){
            if(p.test(s)){
                result.add(s);
            }
        }
        return result;
    }
}
