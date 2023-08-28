package org.litesoft.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.litesoft.annotations.expectations.Expectation;
import org.litesoft.annotations.expectations.Validator;

/**
 * This class has been derived from the public domain code at: <a href="https://github.com/litesoft/LiteSoftCommonFoundation">LiteSoftCommonFoundation</a>
 */
@SuppressWarnings({"unused"})
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
public @interface Required {
    /**
     * TODO: AssertArgument, AssertState, AssertError
     *
     * @see Validator
     */
    class Validate {
        public static boolean value( String pName, Object pToCheck, Expectation pExpectation ) {
            boolean zAcceptable = Check.value( pToCheck );
            if ( !zAcceptable ) {
                pExpectation.unmet( pName, "REQUIRED, but was never 'set'" );
            }
            return zAcceptable;
        }
    }

    class Check {
        public static boolean value( Object pToCheck ) {
            return (null != pToCheck);
        }
    }
}
