package chapter2.quiz1;

import chapter1.methodReference.Apple;

import java.util.List;

/**
 * Created by jyami on 2020/07/26
 */
public class Quiz0201 {
    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter appleFormatter){
        for(Apple apple: inventory){
            String output = appleFormatter.accept(apple);
            System.out.println(output);
        }
    }

}
