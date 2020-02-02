package item13;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by jyami on 2020/02/02
 */
public class ArrayCloneTest {

    @Test
    public void arrayClone() {

        //기본 타입의 복사는 clone 이 좋은듯~!?
        int[] arr = {1,2,3,4};
        ArrayClone arrayClone = new ArrayClone();
        int[] arrMethod = arrayClone.arrayCloneWithMethod(arr);
        assertNotEquals(arrMethod, arr);
    }

    @Test
    public void TimeCheck() {
        //기본 타입의 복사는 clone 이 좋은듯~!?
        int[] arr = new int[1000000];
        Arrays.fill(arr, 1);
        ArrayClone arrayClone = new ArrayClone();

        System.out.println(LocalDateTime.now());
        int[] arrMethod = arrayClone.arrayCloneWithMethod(arr);
        System.out.println(LocalDateTime.now());

        System.out.println(LocalDateTime.now());
        int[] arrFor = arrayClone.arrayCloneWithFor(arr);
        System.out.println(LocalDateTime.now());

    }
}