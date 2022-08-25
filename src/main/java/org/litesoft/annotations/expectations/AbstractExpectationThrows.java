package org.litesoft.annotations.expectations;

@SuppressWarnings({"WeakerAccess", "unused"})
public abstract class AbstractExpectationThrows<T extends RuntimeException> extends AbstractExpectation {
    @Override
    public final void unmet( String pName, String pMessageSuffix ) {
        throw createException( pName, pMessageSuffix );
    }

    protected T createException( String pName, Object pFailedValue, String pExpectation ) {
        return createException( pName, Format.expectationUnmet( pExpectation, pFailedValue ) );
    }

    protected T createException( String pName, String pMessageSuffix ) {
        return createException( Format.prependName( pName, pMessageSuffix ) );
    }

    abstract protected T createException( String pMessage );
}
