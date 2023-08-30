package org.litesoft.annotations.support;

import java.util.function.Supplier;

import org.litesoft.annotations.expectations.Expectation;

/**
 * Generic "asserter" that uses a "checker", and if the "checker" indicates a problem, uses an "Expectation" to report (usually <code>throws</code>) the problem.
 * <p>
 * Legacy... see AssertArgument, AssertState, AssertError
 *
 * @param <T> the type to be checked
 */
public class Assert_rTypedWithNormalizerAndExpectation<T> extends UnmetCheck {
    private final Normalize_r<T> mNormalize_r;

    protected final Expectation mExpectation;

    public Assert_rTypedWithNormalizerAndExpectation( String pExpectationString, Normalize_r<T> pNormalize_r, Expectation pExpectation ) {
        super( pExpectationString );
        mNormalize_r = assertNotNull( pNormalize_r );
        mExpectation = assertNotNull( pExpectation );
    }

    public T namedValue( String pName, T pToCheck ) {
        return contextValue( () -> pName, pToCheck );
    }

    public T contextValue( Supplier<String> pContext, T pToCheck ) {
        T result = mNormalize_r.normalizeToNull( pToCheck );
        acceptable( result != null, pContext, pToCheck, mExpectation );
        return result;
    }
}
