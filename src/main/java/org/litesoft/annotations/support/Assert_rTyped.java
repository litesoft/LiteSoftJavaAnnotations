package org.litesoft.annotations.support;

import java.util.function.Supplier;

import org.litesoft.annotations.expectations.Expectation;

/**
 * Generic "asserter" that uses a "checker", and if the "checker" indicates a problem, uses an "Expectation" to report (usually <code>throws</code>) the problem.
 * <p>
 * Legacy... see AssertArgument, AssertState, AssertError
 *
 * @param <T> the type to be checked
 */public class Assert_rTyped<T> extends UnmetCheck {
    protected final Check_r<T> mChecker;

    public Assert_rTyped( String pExpectationString, Check_r<T> pChecker ) {
        super( pExpectationString );
        mChecker = assertNotNull( pChecker );
    }

    public T namedValueExpectation( String pName, T pToCheck, Expectation pExpectation ) {
        return contextValueExpectation( () -> pName, pToCheck, pExpectation );
    }

    public T contextValueExpectation( Supplier<String> pContext, T pToCheck, Expectation pExpectation ) {
        acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
        return pToCheck;
    }
}
