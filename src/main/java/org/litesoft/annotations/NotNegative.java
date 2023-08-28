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
public @interface NotNegative {
    String EXPECTATION = "Not Negative";
    int ZERO_int = 0;
    long ZERO_long = 0;
    float ZERO_float = 0;
    double ZERO_double = 0;

    /**
     * TODO: AssertArgument, AssertState, AssertError
     *
     * @see Validator
     */
    class Validate {
        public static boolean value( String pName, int pToCheck, Expectation pExpectation ) {
            return validate( Check.value( pToCheck ), pName, pToCheck, pExpectation );
        }

        public static boolean value( String pName, long pToCheck, Expectation pExpectation ) {
            return validate( Check.value( pToCheck ), pName, pToCheck, pExpectation );
        }

        public static boolean value( String pName, float pToCheck, Expectation pExpectation ) {
            return validate( Check.value( pToCheck ), pName, pToCheck, pExpectation );
        }

        public static boolean value( String pName, double pToCheck, Expectation pExpectation ) {
            return validate( Check.value( pToCheck ), pName, pToCheck, pExpectation );
        }

        private static boolean validate( boolean pAcceptable, String pName, Object pToCheck, Expectation pExpectation ) {
            if ( !pAcceptable ) {
                pExpectation.unmet( pName, pToCheck, EXPECTATION );
            }
            return pAcceptable;
        }
    }

    class Check {
        public static boolean value( int pToCheck ) {
            return ZERO_int <= pToCheck;
        }

        public static boolean value( long pToCheck ) {
            return ZERO_long <= pToCheck;
        }

        public static boolean value( float pToCheck ) {
            return !Float.isNaN( pToCheck ) && (ZERO_float <= pToCheck);
        }

        public static boolean value( double pToCheck ) {
            return !Double.isNaN( pToCheck ) && (ZERO_double <= pToCheck);
        }
    }

    class ConstrainTo {
        public static int valueOrZero( int pToCheck ) {
            return Math.max( ZERO_int, pToCheck );
        }

        public static long valueOrZero( long pToCheck ) {
            return Math.max( ZERO_long, pToCheck );
        }

        public static float valueOrZero( float pToCheck ) {
            return Check.value( pToCheck ) ? pToCheck : ZERO_float; // Handles NAN problem
        }

        public static double valueOrZero( double pToCheck ) {
            return Check.value( pToCheck ) ? pToCheck : ZERO_double; // Handles NAN problem
        }
    }

    class Assert {
        public static int namedValueExpectation( String pName, int pToCheck, Expectation pExpectation )
                throws IllegalArgumentException {
            Validate.value( pName, pToCheck, pExpectation );
            return pToCheck;
        }

        public static long namedValueExpectation( String pName, long pToCheck, Expectation pExpectation )
                throws IllegalArgumentException {
            Validate.value( pName, pToCheck, pExpectation );
            return pToCheck;
        }

        public static float namedValueExpectation( String pName, float pToCheck, Expectation pExpectation )
                throws IllegalArgumentException {
            Validate.value( pName, pToCheck, pExpectation );
            return pToCheck;
        }

        public static double namedValueExpectation( String pName, double pToCheck, Expectation pExpectation )
                throws IllegalArgumentException {
            Validate.value( pName, pToCheck, pExpectation );
            return pToCheck;
        }
    }

    class AssertArgument {
        public static int namedValue( String pName, int pToCheck )
                throws IllegalArgumentException {
            return Assert.namedValueExpectation( pName, pToCheck, IllegalArgument.INSTANCE );
        }

        public static long namedValue( String pName, long pToCheck )
                throws IllegalArgumentException {
            return Assert.namedValueExpectation( pName, pToCheck, IllegalArgument.INSTANCE );
        }

        public static float namedValue( String pName, float pToCheck )
                throws IllegalArgumentException {
            return Assert.namedValueExpectation( pName, pToCheck, IllegalArgument.INSTANCE );
        }

        public static double namedValue( String pName, double pToCheck )
                throws IllegalArgumentException {
            return Assert.namedValueExpectation( pName, pToCheck, IllegalArgument.INSTANCE );
        }
    }
}
