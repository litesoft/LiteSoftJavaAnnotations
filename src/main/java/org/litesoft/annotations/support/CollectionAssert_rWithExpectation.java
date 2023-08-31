package org.litesoft.annotations.support;

import java.util.Collection;
import java.util.function.Supplier;

import org.litesoft.annotations.expectations.Expectation;

/**
 * Generic Collection "asserter" that uses a "checker", and if the "checker" indicates a problem, uses an "Expectation" to report (usually <code>throws</code>) the problem.
 * <p>
 * Legacy... see AssertArgument, AssertState, AssertError
 */
public class CollectionAssert_rWithExpectation extends UnmetCheck {
    protected final CollectionCheck_r mChecker;
    protected final Expectation mExpectation;

    public CollectionAssert_rWithExpectation( String pExpectationString, CollectionCheck_r pChecker, Expectation pExpectation ) {
        super( pExpectationString );
        mChecker = assertNotNull( pChecker );
        mExpectation = assertNotNull( pExpectation );
    }

    public <T extends Collection<?>> T namedValue( String pName, T pToCheck ) {
        return contextValue( () -> pName, pToCheck );
    }

    public <T extends Collection<?>> T contextValue( Supplier<String> pContext, T pToCheck ) {
        acceptable( mChecker.value( pToCheck ), pContext, pToCheck, mExpectation );
        return pToCheck;
    }
}
