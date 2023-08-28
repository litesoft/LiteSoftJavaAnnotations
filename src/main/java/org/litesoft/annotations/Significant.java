package org.litesoft.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.litesoft.annotations.expectations.Expectation;
import org.litesoft.annotations.expectations.IllegalArgument;
import org.litesoft.annotations.expectations.Validator;

/**
 * This class has been derived from the public domain code at: <a href="https://github.com/litesoft/LiteSoftCommonFoundation">LiteSoftCommonFoundation</a>
 */
@SuppressWarnings({"WeakerAccess", "unused"})
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
public @interface Significant {
    String EXPECTATION = "Significant";

    /**
     * TODO: AssertArgument, AssertState, AssertError
     *
     * @see Validator
     */
    class Validate {
        public static boolean value( String pName, String pToCheck, Expectation pExpectation ) {
            boolean zAcceptable = Check.value( pToCheck );
            if ( !zAcceptable ) {
                pExpectation.unmet( pName, pToCheck, EXPECTATION );
            }
            return zAcceptable;
        }
    }

    class Check {
        public static boolean value( String pToCheck ) {
            return (null != ConstrainTo.valueOrNull( pToCheck ));
        }
    }

    class ConstrainTo {
        public static String valueOrNull( String pToCheck ) {
            return valueOr( pToCheck, null );
        }

        public static String valueOrEmpty( String pToCheck ) {
            return valueOr( pToCheck, "" );
        }

        public static String valueOr( String pToCheck, String pValueIfNotSignificant ) {
            return ((pToCheck != null) && !pToCheck.isBlank()) // Left to Right
                   ? pToCheck.trim() : pValueIfNotSignificant;
        }
    }

    class Assert {
        public static String namedValueExpectation( String pName, String pToCheck, Expectation pExpectation )
                throws IllegalArgumentException {
            String zResult = ConstrainTo.valueOrNull( pToCheck );
            if ( zResult == null ) {
                pExpectation.unmet( pName, pToCheck, EXPECTATION );
            }
            return zResult;
        }
    }

    class AssertArgument {
        public static String namedValue( String pName, String pToCheck )
                throws IllegalArgumentException {
            return Assert.namedValueExpectation( pName, pToCheck, IllegalArgument.INSTANCE );
        }
    }
}
