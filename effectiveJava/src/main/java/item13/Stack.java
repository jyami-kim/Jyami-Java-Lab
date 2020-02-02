package item13;

/**
 * Created by jyami on 2020/02/02
 */
public class Stack {

    private int number;

    public Stack(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public Stack clone(){
        try {
            return (Stack) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
