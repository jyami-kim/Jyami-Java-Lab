package com.jyami;

/**
 * Created by jyami on 2020/03/12
 */
public class Group {
    int maxNumberOfEmployee;
    int numberOfEnrollment;

    public boolean isEnrollmentFull() {
        if (maxNumberOfEmployee == 0) {
            return false;
        }
        if(numberOfEnrollment > maxNumberOfEmployee){
            return false;
        }
        return true;
    }
}
