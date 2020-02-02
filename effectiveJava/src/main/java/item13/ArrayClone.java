package item13;

import java.util.Arrays;

/**
 * Created by jyami on 2020/02/02
 */
public class ArrayClone {
    public int[] arrayCloneWithMethod(int[] arr){
        return arr.clone();
    }

    public int[] arrayCloneWithFor(int[] arr){
        int[] cloneArr  = new int[arr.length];
        for(int i=0; i<cloneArr.length; i++){
            cloneArr[i] = arr[i];
        }
        return cloneArr;
    }
}
