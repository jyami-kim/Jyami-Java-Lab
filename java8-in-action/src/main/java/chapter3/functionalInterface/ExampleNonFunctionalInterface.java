package chapter3.functionalInterface;

/**
 * Created by jyami on 2020/07/28
 */
@FunctionalInterface
public interface ExampleNonFunctionalInterface {
    void run();
//    void run2(); //이 주석을 풀게되면 위 어노테이션으로 인해 컴파일에러가 나게 된다.
}
