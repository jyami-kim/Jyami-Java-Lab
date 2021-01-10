package chapter17;

import lombok.Getter;
import lombok.ToString;

import java.util.Random;

/**
 * Created by jyami on 2021/01/10
 */
@Getter
public class TempInfo {
    public static final Random random = new Random();

    private final String town;
    private final int temp;

    public TempInfo(String town, int temp) {
        this.town = town;
        this.temp = temp;
    }

    public static TempInfo fetch(String town){
        if(random.nextInt(10) == 0)
            throw new RuntimeException("Error!");
        return new TempInfo(town, random.nextInt(100));
    }

    @Override
    public String toString() {
        return "TempInfo{" +
                "town='" + town + '\'' +
                ", temp=" + temp +
                '}';
    }
}
