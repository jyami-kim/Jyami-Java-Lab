package chapter2.quiz1;

import chapter1.methodReference.Apple;

/**
 * Created by jyami on 2020/07/26
 */
public class AppleSimpleFormatter implements AppleFormatter {
    @Override
    public String accept(Apple apple) {
        return "An apple of " + apple.getWeight() + "g";
    }
}
