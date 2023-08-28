package org.litesoft.annotations.support;

import java.util.function.Supplier;

import org.litesoft.annotations.expectations.Expectation;

public class Validate_r<T> extends UnmetCheck {
    private final Check_r<T> mChecker;

    public Validate_r( String pExpectationString, Check_r<T> pChecker ) {
        super( pExpectationString );
        mChecker = pChecker;
    }

    public boolean value( String pName, T pToCheck, Expectation pExpectation ) {
        return value( () -> pName, pToCheck, pExpectation );
    }

    public boolean value( Supplier<String> pContext, T pToCheck, Expectation pExpectation ) {
        return acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
    }
}
