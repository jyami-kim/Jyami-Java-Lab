package chapter2.behaviorParameterize;

import chapter1.methodReference.Apple;

/**
 * Created by jyami on 2020/07/26
 */
public class AppleGreenColorPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return "green".equals(apple.getColor());
    }
}
