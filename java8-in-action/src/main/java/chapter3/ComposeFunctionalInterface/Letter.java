package chapter3.ComposeFunctionalInterface;

/**
 * Created by jyami on 2020/07/29
 */
public class Letter {
    public static String addHeader(String text){
        return "From Raoul, Mario and Alan: " + text;
    }

    public static String addFooter(String text){
        return text + " Kind regards";
    }

    public static String checkSpelling(String text){
        return text.replace("labda", "lambda");
    }
}
