package org.litesoft.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Collection;

import org.litesoft.annotations.expectations.Expectation;
import org.litesoft.annotations.expectations.IllegalArgument;
import org.litesoft.annotations.expectations.Validator;

/**
 * This class has been derived from the public domain code at: <a href="https://github.com/litesoft/LiteSoftCommonFoundation">LiteSoftCommonFoundation</a>
 */
@SuppressWarnings({"WeakerAccess", "unused"})
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
public @interface NotEmpty {
    String EXPECTATION = "Not Empty";

    /**
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

        public static <Entry, T extends Collection<Entry>> boolean value( String pName, T pToCheck, Expectation pExpectation ) {
            boolean zAcceptable = Check.value( pToCheck );
            if ( !zAcceptable ) {
                pExpectation.unmet( pName, pToCheck, EXPECTATION );
            }
            return zAcceptable;
        }
    }

    class Check {
        public static boolean value( String pToCheck ) {
            return (null != pToCheck) && !pToCheck.isEmpty(); // Left to Right!
        }

        public static <Entry, T extends Collection<Entry>> boolean value( T pToCheck ) {
            return (null != pToCheck) && !pToCheck.isEmpty(); // Left to Right!
        }
    }

    class Assert {
        public static String namedValueExpectation( String pName, String pToCheck, Expectation pExpectation ) {
            boolean zAcceptable = Check.value( pToCheck );
            if ( !zAcceptable ) {
                pExpectation.unmet( pName, null, EXPECTATION );
            }
            return pToCheck;
        }

        public static <Entry, T extends Collection<Entry>> T namedValueExpectation( String pName, T pToCheck, Expectation pExpectation ) {
            boolean zAcceptable = Check.value( pToCheck );
            if ( !zAcceptable ) {
                pExpectation.unmet( pName, null, EXPECTATION );
            }
            return pToCheck;
        }
    }

    class AssertArgument {
        public static String namedValue( String pName, String pToCheck )
                throws IllegalArgumentException {
            return Assert.namedValueExpectation( pName, pToCheck, IllegalArgument.INSTANCE );
        }

        public static <Entry, T extends Collection<Entry>> T namedValue( String pName, T pToCheck )
                throws IllegalArgumentException {
            return Assert.namedValueExpectation( pName, pToCheck, IllegalArgument.INSTANCE );
        }
    }
}
