package item13;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jyami on 2020/02/02
 */
public class StringCloneTest {
    @Test
    public void name() {
        String b= "abc";
        String a = b;


        System.out.println(a.hashCode());

    }

    @Test
    public void stringClone() throws CloneNotSupportedException {
        StringClone stringClone = new StringClone(new String("javabom"));
        StringClone clone = stringClone.clone();

        clone.setClone(new String("java"));

        assertEquals(clone.getClone(),stringClone.getClone());
    }


    @Test
    public void string() throws CloneNotSupportedException {
        String s1 = new String("javabom");

        String s2 = "javabom";
        String s3 = "javabom";

        assertTrue(s1 != s2);
        assertTrue(s2 == s3);
    }
}