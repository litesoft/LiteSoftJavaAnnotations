package org.litesoft.annotations.support;

import java.util.Collection;
import java.util.function.Supplier;

import org.litesoft.annotations.expectations.Expectation;

/**
 * An Assert_r with initialized <code>Expectation</code> that adds support for Collections.
 *
 * @param <T> the type to be asserted FOR NON-COLLECTIONS!
 */
public class Assert_rTypedWithExpectationWithCollection<T> extends Assert_rTypedWithExpectation<T> {
    private final Check_rWithCollection<T> mChecker;

    public Assert_rTypedWithExpectationWithCollection( String pExpectationString, Check_rWithCollection<T> pChecker, Expectation pExpectation ) {
        super( pExpectationString, pChecker, pExpectation );
        mChecker = pChecker;
    }

    public <E extends Collection<?>> E namedValue( String pName, E pToCheck ) {
        return contextValue( () -> pName, pToCheck );
    }

    public <E extends Collection<?>> E contextValue( Supplier<String> pContext, E pToCheck ) {
        acceptable( mChecker.value( pToCheck ), pContext, pToCheck, mExpectation );
        return pToCheck;
    }
}
