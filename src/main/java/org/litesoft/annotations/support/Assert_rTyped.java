package org.litesoft.annotations.support;

import java.util.function.Supplier;

import org.litesoft.annotations.expectations.Expectation;

public class Assert_rTyped<T> extends UnmetCheck {
    protected final Check_r<T> mChecker;

    public Assert_rTyped( String pExpectationString, Check_r<T> pChecker ) {
        super( pExpectationString );
        mChecker = pChecker;
    }

    public T namedValueExpectation( String pName, T pToCheck, Expectation pExpectation ) {
        return contextValueExpectation( () -> pName, pToCheck, pExpectation );
    }

    public T contextValueExpectation( Supplier<String> pContext, T pToCheck, Expectation pExpectation ) {
        acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
        return pToCheck;
    }
}
