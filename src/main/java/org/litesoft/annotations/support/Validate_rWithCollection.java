package org.litesoft.annotations.support;

import java.util.Collection;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.litesoft.annotations.expectations.Expectation;

public class Validate_rWithCollection<T> extends Validate_r<T> {
    private final Check_rWithCollection<T> mChecker;

    public Validate_rWithCollection( String pExpectationString, Check_rWithCollection<T> pChecker ) {
        super(pExpectationString, pChecker);
        mChecker = pChecker;
    }

    public <E extends Collection<?>> boolean value( String pName, E pToCheck, Expectation pExpectation ) {
        return value( () -> pName, pToCheck, pExpectation );
    }

    public <E extends Collection<?>> boolean value( Supplier<String> pContext, E pToCheck, Expectation pExpectation ) {
        return acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
    }
}
