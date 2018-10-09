package org.litesoft.annotations.expectations;

@SuppressWarnings({"WeakerAccess", "unused"})
public abstract class AbstractExpectation implements Expectation {
  /**
   * Format the Unmet Expectation (without the fieldname).
   *
   * @param pFailedValue Nullable
   * @param pExpectation that was not met (if not significant, will be substituted with <code>DEFAULT_ON_INSIGNIFICANT</code>)
   *
   * @return formatted the Unmet Expectation
   */
  public static String formatExpectationUnmet( Object pFailedValue, String pExpectation ) {
    return prefixedformatExpectationUnmet( "Expected", pFailedValue, pExpectation );
  }

  protected static String format( Object pValue ) {
    return (pValue instanceof String) ? ("\"" + pValue + "\"") : String.valueOf( pValue );
  }

  protected static String wrapName( String pName ) {
    pName = significantOr( pName, null );
    return (pName == null) ? DEFAULT_ON_INSIGNIFICANT : ("'" + pName + "'");
  }

  protected static String significantOr( String pToCheck, String pOrValue ) {
    if ( pToCheck != null ) {
      pToCheck = pToCheck.trim();
      if ( !pToCheck.isEmpty() ) {
        return pToCheck;
      }
    }
    return pOrValue;
  }

  protected static String prefixedformatExpectationUnmet( String pPrefix, Object pFailedValue, String pExpectation ) {
    return pPrefix + " to be " + significantOr( pExpectation, DEFAULT_ON_INSIGNIFICANT )
           + ", but was: " + format( pFailedValue );
  }
}
