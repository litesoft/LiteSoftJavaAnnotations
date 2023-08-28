package org.litesoft.annotations.support;

import java.util.Collection;
import java.util.function.Supplier;

import org.litesoft.annotations.expectations.Expectation;

/**
 * A Validate_r that adds support for Collections.
 * <p>
 * Legacy... see AssertArgument, AssertState, AssertError
 *
 * @param <T> the type to be validated FOR NON-COLLECTIONS!
 */
public class Validate_rWithCollection<T> extends Validate_r<T> {
    private final Check_rWithCollection<T> mChecker;

    public Validate_rWithCollection( String pExpectationString, Check_rWithCollection<T> pChecker ) {
        super( pExpectationString, pChecker );
        mChecker = pChecker;
    }

    // String based - Legacy
    public <E extends Collection<?>> boolean value( String pName, E pToCheck, Expectation pExpectation ) {
        return value( () -> pName, pToCheck, pExpectation );
    }

    // Supplier based
    public <E extends Collection<?>> boolean value( Supplier<String> pContext, E pToCheck, Expectation pExpectation ) {
        return acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
    }
}
