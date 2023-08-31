package org.litesoft.annotations.support;

import java.util.Collection;
import java.util.function.Supplier;

import org.litesoft.annotations.expectations.Expectation;

/**
 * Generic Collection "validator" that uses a "checker", and if the "checker" indicates a problem, uses an "Expectation" to report (usually <code>throws</code>) the problem.
 * <p>
 * Legacy... see AssertArgument, AssertState, AssertError
 */
public class CollectionValidate_r extends UnmetCheck {
    private final CollectionCheck_r mChecker;

    public CollectionValidate_r( String pExpectationString, CollectionCheck_r pChecker ) {
        super( pExpectationString );
        mChecker = assertNotNull( pChecker );
    }

    // String based - Legacy
    public boolean value( String pName, Collection<?> pToCheck, Expectation pExpectation ) {
        return value( () -> pName, pToCheck, pExpectation );
    }

    // Supplier based
    public boolean value( Supplier<String> pContext, Collection<?> pToCheck, Expectation pExpectation ) {
        return acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
    }
}
