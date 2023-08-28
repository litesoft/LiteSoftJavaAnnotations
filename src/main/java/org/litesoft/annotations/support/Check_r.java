package org.litesoft.annotations.support;

public class Check_r<T> {
    public boolean value( T pToCheck ) {
        return (null != pToCheck) && notNullText(pToCheck); // Left to Right!
    }

    protected boolean notNullText(T pToCheck) {
        return true;
    };
}
