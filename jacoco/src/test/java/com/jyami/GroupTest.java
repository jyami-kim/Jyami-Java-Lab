package com.jyami;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jyami on 2020/03/12
 */
public class GroupTest {

    @Test
    public void isEnrollmentFull() {
        Group group = new Group();
        group.maxNumberOfEmployee = 0;
        assertEquals(group.isEnrollmentFull(), false);
    }
}