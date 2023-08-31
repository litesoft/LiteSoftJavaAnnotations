package org.litesoft.annotations.support;

import java.util.Collection;
import java.util.function.Supplier;

import org.litesoft.annotations.expectations.Expectation;

/**
 * Generic Collection "asserter" that uses a "checker", and if the "checker" indicates a problem, uses an "Expectation" to report (usually <code>throws</code>) the problem.
 * <p>
 * Legacy... see AssertArgument, AssertState, AssertError
 */public class CollectionAssert_r extends UnmetCheck {
    protected final CollectionCheck_r mChecker;

    public CollectionAssert_r( String pExpectationString, CollectionCheck_r pChecker ) {
        super( pExpectationString );
        mChecker = assertNotNull( pChecker );
    }

    public <T extends Collection<?>> T namedValueExpectation( String pName, T pToCheck, Expectation pExpectation ) {
        return contextValueExpectation( () -> pName, pToCheck, pExpectation );
    }

    public <T extends Collection<?>> T contextValueExpectation( Supplier<String> pContext, T pToCheck, Expectation pExpectation ) {
        acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
        return pToCheck;
    }
}
