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
@SuppressWarnings({"WeakerAccess", "unused", "ConstantConditions"})
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
public @interface NotNull {
    String EXPECTATION = "Not Null";

    /**
     * @see Validator
     */
    class Validate {
        public static boolean value( String pName, Object pToCheck, Expectation pExpectation ) {
            boolean zAcceptable = Check.value( pToCheck );
            if ( !zAcceptable ) {
                pExpectation.unmet( pName, pToCheck, EXPECTATION );
            }
            return zAcceptable;
        }
    }

    class Check {
        public static boolean value( Object pToCheck ) {
            return (null != pToCheck);
        }
    }

    class ConstrainTo {
        public static String valueOr( String pToCheck, String pValueIfNull ) {
            return (pToCheck != null) ? pToCheck : pValueIfNull;
        }

        public static <T> T valueOr( T pToCheck, T pValueIfNull ) {
            return (pToCheck != null) ? pToCheck : pValueIfNull;
        }
    }

    class Assert {
        public static <T> T namedValueExpectation( String pName, T pToCheck, Expectation pExpectation ) {
            if ( pToCheck == null ) {
                pExpectation.unmet( pName, null, EXPECTATION );
            }
            return pToCheck;
        }

        public static <T> T errorOn( String pName, T pToCheck ) {
            if ( pToCheck != null ) {
                return pToCheck;
            }
            throw new Error( "No '" + pName + "' provided -- coding error" );
        }
    }

    class AssertArgument {
        public static <T> T namedValue( String pName, T pToCheck )
                throws IllegalArgumentException {
            return Assert.namedValueExpectation( pName, pToCheck, IllegalArgument.INSTANCE );
        }
    }

    class Count {
        public static int of( Object... pObjects ) {
            int count = 0;
            if ( pObjects != null ) {
                for ( Object o : pObjects ) {
                    if ( o != null ) {
                        count++;
                    }
                }
            }
            return count;
        }
    }
}
