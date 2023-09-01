package org.litesoft.annotations.expectations;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public interface Expectation {
    String DEFAULT_ON_INSIGNIFICANT = ".!N/A!.";

    /**
     * Process (either record or throw an Exception) the <code>Expectation</code>
     * not being met with the <code>FailedValue</code> from the <code>Name</code>d
     * source.
     *
     * @param pContext     of the source (normally a contextual path) (if not significant, will be substituted with <code>DEFAULT_ON_INSIGNIFICANT</code>)
     * @param pFailedValue Nullable
     * @param pExpectation that was not met (if not significant, will be substituted with <code>DEFAULT_ON_INSIGNIFICANT</code>)
     */
    default void unmet( Supplier<String> pContext, Object pFailedValue, String pExpectation ) {
        unmet( Format.resolve( pContext ), pFailedValue, pExpectation );
    }

    /**
     * Process (either record or throw an Exception) the <code>Expectation</code>
     * not being met with the <code>FailedValue</code> from the <code>Name</code>d
     * source.
     *
     * @param pContext       of the source (normally a parameter contextual path) (if not significant, will be substituted with <code>DEFAULT_ON_INSIGNIFICANT</code>)
     * @param pMessageSuffix Failed Message Suffix (if not significant, will be substituted with <code>DEFAULT_ON_INSIGNIFICANT</code>)
     */
    default void unmet( Supplier<String> pContext, String pMessageSuffix ) {
        unmet( Format.resolve( pContext ), pMessageSuffix );
    }

    /**
     * Process (either record or throw an Exception) the <code>Expectation</code>
     * not being met with the <code>FailedValue</code> from the <code>Name</code>d
     * source.
     *
     * @param pContext     of the source (normally a parameter name) (if not significant, will be substituted with <code>DEFAULT_ON_INSIGNIFICANT</code>)
     * @param pFailedValue Nullable
     * @param pExpectation that was not met (if not significant, will be substituted with <code>DEFAULT_ON_INSIGNIFICANT</code>)
     */
    default void unmet( String pContext, Object pFailedValue, String pExpectation ) {
        unmet( pContext, Format.expectationUnmet( pExpectation, pFailedValue ) );
    }

    /**
     * Process (either record or throw an Exception) the <code>Expectation</code>
     * not being met with the <code>FailedValue</code> from the <code>Name</code>d
     * source.
     *
     * @param pContext       of the source (normally a parameter name) (if not significant, will be substituted with <code>DEFAULT_ON_INSIGNIFICANT</code>)
     * @param pMessageSuffix Failed Message Suffix (if not significant, will be substituted with <code>DEFAULT_ON_INSIGNIFICANT</code>)
     */
    void unmet( String pContext, String pMessageSuffix );

    @SuppressWarnings("WeakerAccess")
    class Format {
        /**
         * Convert a possible nullable String Supplier into a string (possibly null).
         *
         * @param pContext String Supplier, nullable
         * @return null or Supplier result
         */
        public static String resolve( Supplier<String> pContext ) {
            return (pContext == null) ? null : pContext.get();
        }

        /**
         * Format an arbitrary value (Strings get wrapped w/ double quotes).
         *
         * @param pValue Nullable to be formatted
         * @return String form of the formatted value
         */
        public static String value( Object pValue ) {
            return (pValue instanceof String) ? ("\"" + pValue + "\"") : String.valueOf( pValue );
        }

        /**
         * Format a string that is assumed to be a name (probably an attribute or parameter) such that it is wrapped w/ single quotes (if not significant, will be substituted with <code>DEFAULT_ON_INSIGNIFICANT</code> AND not wrapped).
         *
         * @param pName to be formatted (if not significant, will be substituted with <code>DEFAULT_ON_INSIGNIFICANT</code>)
         * @return String form of the name
         */
        public static String name( String pName ) {
            return context( pName );
        }

        /**
         * Format a string that is assumed to be a name (probably an attribute or parameter name or context path) such that it is wrapped w/ single quotes (if not significant, will be substituted with <code>DEFAULT_ON_INSIGNIFICANT</code> AND not wrapped).
         *
         * @param pContext to be formatted (if not significant, will be substituted with <code>DEFAULT_ON_INSIGNIFICANT</code>)
         * @return String form of the name
         */
        public static String context( String pContext ) {
            pContext = significantOr( pContext, null );
            return (pContext == null) ? DEFAULT_ON_INSIGNIFICANT : ("'" + pContext + "'");
        }

        /**
         * Format the Unmet Expectation (without the field name).
         *
         * @param pExpectation that was not met (if not significant, will be substituted with <code>DEFAULT_ON_INSIGNIFICANT</code>)
         * @param pFailedValue Nullable
         * @return formatted the Unmet Expectation
         */
        public static String expectationUnmet( String pExpectation, Object pFailedValue ) {
            return "expected to be " + significantOr( pExpectation, DEFAULT_ON_INSIGNIFICANT )
                   + ", but was: " + value( pFailedValue );
        }

        /**
         * Combine the <code>Name</code> and the <code>MessageSuffix</code> for the Unmet Expectation.
         *
         * @param pName          of the source (normally a parameter name) (if not significant, will be substituted with <code>DEFAULT_ON_INSIGNIFICANT</code>)
         * @param pMessageSuffix Significant message suffix (if not significant, will be substituted with <code>DEFAULT_ON_INSIGNIFICANT</code>)
         * @return formatted the Unmet Expectation
         */
        public static String prependName( String pName, String pMessageSuffix ) {
            return prependContext( pName, pMessageSuffix );
        }

        /**
         * Combine the <code>Name</code> and the <code>MessageSuffix</code> for the Unmet Expectation.
         *
         * @param pContext       of the source (normally a parameter name or context path) (if not significant, will be substituted with <code>DEFAULT_ON_INSIGNIFICANT</code>)
         * @param pMessageSuffix Significant message suffix (if not significant, will be substituted with <code>DEFAULT_ON_INSIGNIFICANT</code>)
         * @return formatted the Unmet Expectation
         */
        public static String prependContext( String pContext, String pMessageSuffix ) {
            return name( pContext ) + " is " + significantOr( pMessageSuffix, DEFAULT_ON_INSIGNIFICANT );
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
