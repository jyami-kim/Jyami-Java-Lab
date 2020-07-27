package chapter2.quiz1;

import chapter1.methodReference.Apple;

/**
 * Created by jyami on 2020/07/26
 */
public class AppleFancyFormatter implements AppleFormatter {
    @Override
    public String accept(Apple apple) {
        String characteristic = apple.getWeight() > 150 ? "heavy" : "light";
        return "A " + characteristic + " " + apple.getColor() + " apple";
    }
}
