package org.litesoft.annotations.support;

import java.util.function.Supplier;

import org.litesoft.annotations.expectations.Expectation;

/**
 * An Assert_r with initialized <code>Expectation</code>.
 *
 * @param <T> the type to checked
 */
public class Assert_rTypedWithExpectation<T> extends UnmetCheck implements Assert_rWithExpectation<T> {
    private final Check_r<T> mChecker;
    protected final Expectation mExpectation;

    public Assert_rTypedWithExpectation( String pExpectationString, Check_r<T> pChecker, Expectation pExpectation ) {
        super( pExpectationString );
        mChecker = assertNotNull( pChecker );
        mExpectation = assertNotNull( pExpectation );
    }

    @Override
    public T namedValue( String pName, T pToCheck ) {
        return contextValue( () -> pName, pToCheck );
    }

    @Override
    public T contextValue( Supplier<String> pContext, T pToCheck ) {
        acceptable( mChecker.value( pToCheck ), pContext, pToCheck, mExpectation );
        return pToCheck;
    }
}
