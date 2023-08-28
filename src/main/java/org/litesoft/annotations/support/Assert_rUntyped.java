package org.litesoft.annotations.support;

import java.util.function.Supplier;

import org.litesoft.annotations.expectations.Expectation;

public class Assert_rUntyped extends UnmetCheck {
    protected final Check_r<Object> mChecker;

    public Assert_rUntyped( String pExpectationString, Check_r<Object> pChecker ) {
        super( pExpectationString );
        mChecker = pChecker;
    }

    public <T> T namedValueExpectation( String pName, T pToCheck, Expectation pExpectation ) {
        return contextValueExpectation( () -> pName, pToCheck, pExpectation );
    }

    public <T> T contextValueExpectation( Supplier<String> pContext, T pToCheck, Expectation pExpectation ) {
        acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
        return pToCheck;
    }
}
