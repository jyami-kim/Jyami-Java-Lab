package chapter1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by jyami on 2020/07/26
 */
public class AppleMethodRefProcess {
    public static boolean isGreenApple(Apple apple){
        return "green".equals(apple.getColor());
    }

    public static boolean isHeavyApple(Apple apple){
        return apple.getWeight() > 150;
    }

    public static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p){
        List<Apple> result = new ArrayList<>();
        for(Apple apple: inventory){
            if(p.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }
}
