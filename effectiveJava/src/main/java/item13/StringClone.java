package item13;

/**
 * Created by jyami on 2020/02/02
 */
public class StringClone implements Cloneable{

    private String clone;

    public void setClone(String clone) {
        this.clone = clone;
    }

    public StringClone(String clone) {
        this.clone = clone;
    }

    public String getClone() {
        return clone;
    }

    @Override
    public StringClone clone() throws CloneNotSupportedException {
        return (StringClone) super.clone();
    }
}
