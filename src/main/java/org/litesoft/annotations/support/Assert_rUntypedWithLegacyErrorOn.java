package org.litesoft.annotations.support;

import java.util.function.Supplier;

import org.litesoft.annotations.expectations.Expectation;

public class Assert_rUntypedWithLegacyErrorOn extends UnmetCheck {
    private final Check_r<Object> mChecker;

    public Assert_rUntypedWithLegacyErrorOn( String pExpectationString, Check_r<Object> pChecker ) {
        super( pExpectationString );
        mChecker = assertNotNull( pChecker );
    }

    public <T> T namedValueExpectation( String pName, T pToCheck, Expectation pExpectation ) {
        return contextValueExpectation( () -> pName, pToCheck, pExpectation );
    }

    public <T> T contextValueExpectation( Supplier<String> pContext, T pToCheck, Expectation pExpectation ) {
        acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
        return pToCheck;
    }

    public <T> T errorOn( String pName, T pToCheck ) {
        if ( mChecker.value( pToCheck ) ) {
            return pToCheck;
        }
        throw new Error( "No '" + pName + "' provided -- coding error" );
    }
}
