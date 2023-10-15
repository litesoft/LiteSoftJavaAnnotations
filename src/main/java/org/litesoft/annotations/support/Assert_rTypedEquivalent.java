package org.litesoft.annotations.support;

import java.util.function.BiPredicate;
import java.util.function.Supplier;

import org.litesoft.annotations.NotNull;
import org.litesoft.annotations.expectations.Expectation;
import org.litesoft.pragmatics.Context;

/**
 * Equivalent "asserter" that uses a <code>BiPredicate</code> "checker" to determine Equivalence, and if
 * the "checker" indicates a problem, uses an "Expectation" to report (usually <code>throws</code>) the problem.
 */
public class Assert_rTypedEquivalent { // extends UnmetCheck {
    public static final String NOT_SAME = "not the same instance as";
    public static final String NOT_EQUAL = "not equal to the";

    private final Expectation mExpectation;

    public Assert_rTypedEquivalent( Expectation pExpectation ) {
        mExpectation = pExpectation;
    }

    public <T> T sameNotNull( String pExpectedName, T pExpected, String pActualName, T pActual ) {
        return sameNotNull( new Context( pExpectedName ), pExpected, () -> pActualName, pActual );
    }

    public <T> T equalNotNull( String pExpectedName, T pExpected, String pActualName, T pActual ) {
        return equalNotNull( new Context( pExpectedName ), pExpected, () -> pActualName, pActual );
    }

    public <T> T sameNotNull( Context pExpectedContext, T pExpected, Supplier<String> pActualContext, T pActual ) {
        return check( pExpectedContext, pExpected, pActualContext, pActual, NOT_SAME, this::checkSameNotNull );
    }

    public <T> T equalNotNull( Context pExpectedContext, T pExpected, Supplier<String> pActualContext, T pActual ) {
        return check( pExpectedContext, pExpected, pActualContext, pActual, NOT_EQUAL, this::checkEqualNotNull );
    }

    private <T> boolean checkSameNotNull( T pExpected, T pActual ) {
        return pExpected == pActual;
    }

    private <T> boolean checkEqualNotNull( T pExpected, T pActual ) {
        return pExpected.equals( pActual );
    }

    private <T> T check( Supplier<String> pExpectedContext, T pExpected,
                         Supplier<String> pActualContext, T pActual,
                         String errorText, BiPredicate<T, T> checker ) {
        NotNull.AssertArgument.contextValue( pExpectedContext, pExpected );
        NotNull.AssertArgument.contextValue( pActualContext, pActual );
        if ( !checker.test( pExpected, pActual ) ) {
            mExpectation.unmet( "Actual '" + pActualContext.get() + "'", // + ' is ' +
                                errorText + " (expected) '" + pExpectedContext.get() + "':"
                                + "\n  expected: " + pExpected
                                + "\n    actual: " + pActual );
        }
        return pActual;
    }
}
