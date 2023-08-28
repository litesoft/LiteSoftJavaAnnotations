package org.litesoft.annotations.support;

import java.util.function.Supplier;

import org.litesoft.annotations.expectations.Expectation;

public class Assert_rUntypedWithExpectation extends UnmetCheck {
    private final Check_r<Object> mChecker;
    protected final Expectation mExpectation;

    public Assert_rUntypedWithExpectation( String pExpectationString, Check_r<Object> pChecker, Expectation pExpectation ) {
        super( pExpectationString );
        mChecker = pChecker;
        mExpectation = pExpectation;
    }

    public <T> T namedValue( String pName, T pToCheck ) {
        return contextValue( () -> pName, pToCheck );
    }

    public <T> T contextValue( Supplier<String> pContext, T pToCheck) {
        acceptable( mChecker.value( pToCheck ), pContext, pToCheck, mExpectation );
        return pToCheck;
    }
}
