package org.litesoft.annotations.support;

import java.util.Collection;
import java.util.function.Supplier;

import org.litesoft.annotations.expectations.Expectation;

public class Assert_rTypedWithCollection<T> extends Assert_rTyped<T> {
    private final Check_rWithCollection<T> mChecker;

    public Assert_rTypedWithCollection( String pExpectationString, Check_rWithCollection<T> pChecker ) {
        super( pExpectationString, pChecker );
        mChecker = pChecker;
    }

    public <E extends Collection<?>> E namedValueExpectation( String pName, E pToCheck, Expectation pExpectation ) {
        return contextValueExpectation( () -> pName, pToCheck, pExpectation );
    }

    public <E extends Collection<?>> E contextValueExpectation( Supplier<String> pContext, E pToCheck, Expectation pExpectation ) {
        acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
        return pToCheck;
    }
}
