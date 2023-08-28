package org.litesoft.annotations.expectations;

@SuppressWarnings("WeakerAccess")
public abstract class AbstractExpectationThrows<T extends RuntimeException> extends AbstractExpectation {
    @Override
    public final void unmet( String pContext, String pMessageSuffix ) {
        throw createException( pContext, pMessageSuffix );
    }

    protected T createException( String pContext, Object pFailedValue, String pExpectation ) {
        return createException( pContext, Format.expectationUnmet( pExpectation, pFailedValue ) );
    }

    protected T createException( String pContext, String pMessageSuffix ) {
        return createException( Format.prependContext( pContext, pMessageSuffix ) );
    }

    abstract protected T createException( String pMessage );
}
