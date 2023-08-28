package org.litesoft.annotations.support;

import java.util.function.Supplier;

import org.litesoft.annotations.expectations.Expectation;

public class UnmetCheck {
    private final String mExpectationString;

    public UnmetCheck( String pExpectationString ) {
        mExpectationString = pExpectationString;
    }

    protected boolean acceptable(boolean zAcceptable, Supplier<String> pContext, Object pToCheck, Expectation pExpectation) {
        if ( !zAcceptable ) {
            pExpectation.unmet( pContext, pToCheck, mExpectationString );
        }
        return zAcceptable;
    }
}
