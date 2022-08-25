package org.litesoft.annotations.expectations;

@SuppressWarnings({"unused"})
public abstract class AbstractExpectation implements Expectation {
    @Override
    public final void unmet( String pName, Object pFailedValue, String pExpectation ) {
        unmet( pName, Format.expectationUnmet( pExpectation, pFailedValue ) );
    }
}
