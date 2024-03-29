package org.litesoft.annotations.expectations;

@SuppressWarnings({"unused"})
public class IllegalArgument extends AbstractExpectationThrows<IllegalArgumentException> {
    public static final IllegalArgument INSTANCE = new IllegalArgument();

    @Override
    protected IllegalArgumentException createException( String pMessage ) {
        return new IllegalArgumentException( pMessage );
    }

    /**
     * Process (either record or throw an Exception) the <code>Expectation</code>
     * not being met with the <code>FailedValue</code> from the <code>Context</code>d
     * source.
     *
     * @param pContext     of the source (normally a parameter name or context pat) (if not significant, will be substituted with <code>DEFAULT_ON_INSIGNIFICANT</code>)
     * @param pFailedValue Nullable
     * @param pExpectation that was not met (if not significant, will be substituted with <code>DEFAULT_ON_INSIGNIFICANT</code>)
     * @return IllegalArgumentException!
     */
    public static IllegalArgumentException expectationUnmet( String pContext,
                                                             Object pFailedValue,
                                                             String pExpectation ) {
        return INSTANCE.createException( pContext, pFailedValue, pExpectation );
    }

    /**
     * Process (either record or throw an Exception) the <code>Expectation</code>
     * not being met with the <code>FailedValue</code> from the <code>Context</code>d
     * source.
     *
     * @param pContext       of the source (normally a parameter name or context pat) (if not significant, will be substituted with <code>DEFAULT_ON_INSIGNIFICANT</code>)
     * @param pMessageSuffix Failed Message Suffix (if not significant, will be substituted with <code>DEFAULT_ON_INSIGNIFICANT</code>)
     * @return IllegalArgumentException!
     */
    public static IllegalArgumentException expectationUnmet( String pContext,
                                                             String pMessageSuffix ) {
        return INSTANCE.createException( pContext, pMessageSuffix );
    }
}
