package org.litesoft.annotations.support;

import java.util.function.Supplier;

import org.litesoft.annotations.expectations.Expectation;

/**
 * Generic "validator" that uses a "checker", and if the "checker" indicates a problem, uses an "Expectation" to report (usually <code>throws</code>) the problem.
 * <p>
 * Legacy... see AssertArgument, AssertState, AssertError
 *
 * @param <T> the type to be checked
 */
public class Validate_r<T> extends UnmetCheck {
    private final Check_r<T> mChecker;

    public Validate_r( String pExpectationString, Check_r<T> pChecker ) {
        super( pExpectationString );
        mChecker = assertNotNull( pChecker );
    }

    // String based - Legacy
    public boolean value( String pName, T pToCheck, Expectation pExpectation ) {
        return value( () -> pName, pToCheck, pExpectation );
    }

    // Supplier based
    public boolean value( Supplier<String> pContext, T pToCheck, Expectation pExpectation ) {
        return acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
    }
}
