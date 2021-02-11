import java.util.Arrays;
import java.util.List;

import static chapter3.KotlinCollectionKt.joinToString;

/**
 * Created by jyami on 2021/01/03
 */
public class JavaMain {
    public static void main(String args[]) {
        List<Integer> collection = Arrays.asList(1,2,3);
        joinToString(collection ,  /* separator */" ", /* prefix */ " ", /* postfix */ ".");
    }
}
