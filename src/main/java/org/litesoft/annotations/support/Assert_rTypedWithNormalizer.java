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
public class Assert_rTypedWithNormalizer<T> extends UnmetCheck {
    protected final Normalize_r<T> mNormalize_r;

    public Assert_rTypedWithNormalizer( String pExpectationString, Normalize_r<T> pNormalize_r ) {
        super( pExpectationString );
        mNormalize_r = assertNotNull( pNormalize_r );
    }

    public T namedValueExpectation( String pName, T pToCheck, Expectation pExpectation ) {
        return contextValueExpectation( () -> pName, pToCheck, pExpectation );
    }

    public T contextValueExpectation( Supplier<String> pContext, T pToCheck, Expectation pExpectation ) {
        T result = mNormalize_r.normalizeToNull( pToCheck );
        acceptable( result != null, pContext, pToCheck, pExpectation );
        return result;
    }
}
