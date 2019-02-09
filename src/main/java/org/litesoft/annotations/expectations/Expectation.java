package org.litesoft.annotations.expectations;

public interface Expectation {
  String DEFAULT_ON_INSIGNIFICANT = ".!N/A!.";

  /**
   * Process (either record or throw an Exception) the <code>Expectation</code>
   * not being met with the <code>FailedValue</code> from the <code>Name</code>d
   * source.
   *
   * @param pName        of the source (normally a parameter name) (if not significant, will be substituted with <code>DEFAULT_ON_INSIGNIFICANT</code>)
   * @param pFailedValue Nullable
   * @param pExpectation that was not met (if not significant, will be substituted with <code>DEFAULT_ON_INSIGNIFICANT</code>)
   */
  void unmet( String pName, Object pFailedValue, String pExpectation );

  /**
   * Process (either record or throw an Exception) the <code>Expectation</code>
   * not being met with the <code>FailedValue</code> from the <code>Name</code>d
   * source.
   *
   * @param pName    of the source (normally a parameter name) (if not significant, will be substituted with <code>DEFAULT_ON_INSIGNIFICANT</code>)
   * @param pMessageSuffix Failed Message Suffix (if not significant, will be substituted with <code>DEFAULT_ON_INSIGNIFICANT</code>)
   */
  void unmet( String pName, String pMessageSuffix );

  @SuppressWarnings("WeakerAccess")
  class Format {
    /**
     * Format an arbitrary value (Strings get wrapped w/ double quotes).
     *
     * @param pValue Nullable to be formatted
     *
     * @return String form of the formatted value
     */
    public static String value( Object pValue ) {
      return (pValue instanceof String) ? ("\"" + pValue + "\"") : String.valueOf( pValue );
    }

    /**
     * Format a string that is assumed to be a name (probably an attribute or parameter) such that is is wrapped w/ simgle quotes (if not significant, will be substituted with <code>DEFAULT_ON_INSIGNIFICANT</code> AND not wrapped).
     *
     * @param pName to be formatted (if not significant, will be substituted with <code>DEFAULT_ON_INSIGNIFICANT</code>)
     *
     * @return String form of the name
     */
    public static String name( String pName ) {
      pName = significantOr( pName, null );
      return (pName == null) ? DEFAULT_ON_INSIGNIFICANT : ("'" + pName + "'");
    }

    /**
     * Format the Unmet Expectation (without the fieldname).
     *
     * @param pExpectation that was not met (if not significant, will be substituted with <code>DEFAULT_ON_INSIGNIFICANT</code>)
     * @param pFailedValue Nullable
     *
     * @return formatted the Unmet Expectation
     */
    public static String expectationUnmet( String pExpectation, Object pFailedValue ) {
      return "expected to be " + significantOr( pExpectation, DEFAULT_ON_INSIGNIFICANT )
             + ", but was: " + value( pFailedValue );
    }

    /**
     * Format the Unmet Expectation (without the fieldname).
     *
     * @param pName          of the source (normally a parameter name) (if not significant, will be substituted with <code>DEFAULT_ON_INSIGNIFICANT</code>)
     * @param pMessageSuffix Significant message suffix (if not significant, will be substituted with <code>DEFAULT_ON_INSIGNIFICANT</code>)
     *
     * @return formatted the Unmet Expectation
     */
    public static String prependName( String pName, String pMessageSuffix ) {
      return name( pName ) + " is " + significantOr( pMessageSuffix, DEFAULT_ON_INSIGNIFICANT );
    }

    private static String significantOr( String pToCheck, String pOrValue ) {
      if ( pToCheck != null ) {
        pToCheck = pToCheck.trim();
        if ( !pToCheck.isEmpty() ) {
          return pToCheck;
        }
      }
      return pOrValue;
    }
  }
}
