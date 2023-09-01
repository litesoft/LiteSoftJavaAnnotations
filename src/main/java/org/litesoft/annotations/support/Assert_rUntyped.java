package org.litesoft.annotations.support;

import java.util.function.Supplier;

import org.litesoft.annotations.expectations.Expectation;

/**
 * Generic "asserter" that uses a "checker", and if the "checker" indicates a problem, uses an "Expectation" to report (usually <code>throws</code>) the problem.
 */
public class Assert_rUntyped extends UnmetCheck {
    protected final Check_r<Object> mChecker;

    public Assert_rUntyped( String pExpectationString, Check_r<Object> pChecker ) {
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
}
