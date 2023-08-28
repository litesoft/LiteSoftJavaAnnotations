package org.litesoft.annotations.expectations;

public class ThrowError implements Expectation {
    public static final ThrowError INSTANCE = new ThrowError();

    @Override
    public void unmet( String pContext, String pMessageSuffix ) {
        throw new Error( Format.prependContext( pContext, pMessageSuffix ) );
    }
}
