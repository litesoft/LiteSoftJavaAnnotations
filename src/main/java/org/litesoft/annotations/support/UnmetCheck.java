package org.litesoft.annotations.support;

import java.util.function.Supplier;

import org.litesoft.annotations.expectations.Expectation;

/**
 * Common code to report (usually <code>throws</code>) a problem.
 */
public class UnmetCheck {
    private final String mExpectationString;

    public UnmetCheck( String pExpectationString ) {
        mExpectationString = assertNotNull( pExpectationString );
    }

    protected boolean acceptable( boolean zAcceptable, Supplier<String> pContext, Object pToCheck, Expectation pExpectation ) {
        if ( !zAcceptable ) {
            assertNotNull( pExpectation ).unmet( pContext, pToCheck, mExpectationString );
        }
        return zAcceptable;
    }

    protected <T> T assertNotNull( T value ) {
        if ( value != null ) {
            return value;
        }
        throw new Error( "Required value was null!" );
    }
}
