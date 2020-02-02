package item13;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jyami on 2020/02/02
 */
public class StackTest {

    @Test
    public void testClone() {
        Stack stack = new Stack(1);
        Stack clone = stack.clone();
        assertNotEquals(clone, stack);
    }
}