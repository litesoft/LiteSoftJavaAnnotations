package org.litesoft.annotations.expectations;

@SuppressWarnings({"WeakerAccess", "unused"})
public class IllegalState extends AbstractExpectation {
  public static final Expectation INSTANCE = new IllegalState();

  @Override
  public void unmet( String pName, Object pFailedValue, String pExpectation ) {
    throw expectationUnmet( pName, pFailedValue, pExpectation );
  }

  /**
   * Process (either record or throw an Exception) the <code>Expectation</code>
   * not being met with the <code>FailedValue</code> from the <code>Name</code>d
   * source.
   *
   * @param pName        of the source (normally a parameter name) (if not significant, will be substituted with <code>DEFAULT_ON_INSIGNIFICANT</code>)
   * @param pFailedValue Nullable
   * @param pExpectation that was not met (if not significant, will be substituted with <code>DEFAULT_ON_INSIGNIFICANT</code>)
   *
   * @return IllegalStateException!
   */
  public static IllegalStateException expectationUnmet( String pName,
                                                        Object pFailedValue,
                                                        String pExpectation ) {
    return new IllegalStateException(
            prefixedformatExpectationUnmet( wrapName( pName ) + " was expected",
                                            pFailedValue, pExpectation ) );
  }
}
