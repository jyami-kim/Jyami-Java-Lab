package chapter2.behaviorParameterize;

import chapter1.methodReference.Apple;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jyami on 2020/07/26
 */
public class FilteringApple {
    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p){
        List<Apple> result = new ArrayList<>();
        for(Apple apple: inventory){
            if(p.test(apple))
                result.add(apple);
        }
        return result;
    }
}
