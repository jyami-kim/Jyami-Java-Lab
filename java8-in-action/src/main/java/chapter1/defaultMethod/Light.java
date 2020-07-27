package chapter1.defaultMethod;

/**
 * Created by jyami on 2020/07/26
 */
public interface Light {

    void printLightDescription();

    /**
     * default 메서드를 사용하면, 기존 인터페이스에 새로운 메서드를 추가하더라도,
     * 이 인터페이스를 상속하는 하위 클래스들이 구현을 하지 않아도 된다. (default 메서드의 구현을 사용한다)
     * 이 메서드 바디는 클래스 구현이 아닌, 인터페이스의 일부라고 생각하자.
     */
    default void switchOn(){
        System.out.println("this light switch on");
    }

}
