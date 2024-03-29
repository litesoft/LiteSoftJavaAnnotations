package org.litesoft.annotations.expectations;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public interface Validator<T> {
    /**
     * Process (either record or throw an Exception) the <code>Expectation</code>
     * not being met with the <code>FailedValue</code> from the <code>Context</code>d
     * source.
     *
     * @param pContext     of the source (normally a parameter name or a contextual path) (if not significant, will be substituted with <code>DEFAULT_ON_INSIGNIFICANT</code>)
     * @param pToCheck     Nullable
     * @param pExpectation If the check fails then the Expectation is called to handle our disappointment
     * @return false if the chexk for validity failed (and the Expectation was called - which may throw in which case the return value will not be returned)
     */
    boolean check( Supplier<String> pContext, T pToCheck, Expectation pExpectation );

    /**
     * Process (either record or throw an Exception) the <code>Expectation</code>
     * not being met with the <code>FailedValue</code> from the <code>Name</code>d
     * source.
     *
     * @param pContext     of the source (normally a parameter name or a contextual path) (if not significant, will be substituted with <code>DEFAULT_ON_INSIGNIFICANT</code>)
     * @param pToCheck     Nullable
     * @param pExpectation If the check fails then the Expectation is called to handle our disappointment
     * @return false if the chexk for validity failed (and the Expectation was called - which may throw in which case the return value will not be returned)
     */
    default boolean check( String pContext, T pToCheck, Expectation pExpectation ) {
        return check( () -> pContext, pToCheck, pExpectation );
    }
}
