package org.litesoft.annotations.expectations;

@SuppressWarnings({"WeakerAccess", "unused"})
public class IllegalState extends AbstractExpectationThrows<IllegalStateException> {
    public static final IllegalState INSTANCE = new IllegalState();

    @Override
    protected IllegalStateException createException( String pMessage ) {
        return new IllegalStateException( pMessage );
    }

    /**
     * Process (either record or throw an Exception) the <code>Expectation</code>
     * not being met with the <code>FailedValue</code> from the <code>Context</code>d
     * source.
     *
     * @param pContext     of the source (normally a parameter name or context path) (if not significant, will be substituted with <code>DEFAULT_ON_INSIGNIFICANT</code>)
     * @param pFailedValue Nullable
     * @param pExpectation that was not met (if not significant, will be substituted with <code>DEFAULT_ON_INSIGNIFICANT</code>)
     * @return IllegalStateException!
     */
    public static IllegalStateException expectationUnmet( String pContext,
                                                          Object pFailedValue,
                                                          String pExpectation ) {
        return INSTANCE.createException( pContext, pFailedValue, pExpectation );
    }

    /**
     * Process (either record or throw an Exception) the <code>Expectation</code>
     * not being met with the <code>FailedValue</code> from the <code>Context</code>d
     * source.
     *
     * @param pContext       of the source (normally a parameter name or context path) (if not significant, will be substituted with <code>DEFAULT_ON_INSIGNIFICANT</code>)
     * @param pMessageSuffix Failed Message Suffix (if not significant, will be substituted with <code>DEFAULT_ON_INSIGNIFICANT</code>)
     * @return IllegalStateException!
     */
    public static IllegalStateException expectationUnmet( String pContext,
                                                          String pMessageSuffix ) {
        return INSTANCE.createException( pContext, pMessageSuffix );
    }
}
