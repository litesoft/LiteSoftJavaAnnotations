package org.litesoft.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.litesoft.annotations.expectations.IllegalArgument;
import org.litesoft.annotations.expectations.IllegalState;
import org.litesoft.annotations.expectations.ThrowError;
import org.litesoft.annotations.support.Assert_rUntypedWithExpectation;
import org.litesoft.annotations.support.Assert_rUntypedWithLegacyErrorOn;
import org.litesoft.annotations.support.Check_r;
import org.litesoft.annotations.support.Validate_r;

/**
 * This class has been derived from the public domain code at: <a href="https://github.com/litesoft/LiteSoftCommonFoundation">LiteSoftCommonFoundation</a>
 */
@SuppressWarnings({"WeakerAccess", "unused"})
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
public @interface NotNull {
    String EXPECTATION = "Not Null";

    Check_r<Object> Check = new Check_r<>();

    Validate_r<Object> Validate = new Validate_r<>( EXPECTATION, Check );

    Assert_rUntypedWithLegacyErrorOn Assert = new Assert_rUntypedWithLegacyErrorOn( EXPECTATION, Check );

    Assert_rUntypedWithExpectation AssertArgument2 = new Assert_rUntypedWithExpectation( EXPECTATION, Check, IllegalArgument.INSTANCE );
    Assert_rUntypedWithExpectation AssertState = new Assert_rUntypedWithExpectation( EXPECTATION, Check, IllegalState.INSTANCE );
    Assert_rUntypedWithExpectation AssertError = new Assert_rUntypedWithExpectation( EXPECTATION, Check, ThrowError.INSTANCE );

    class ConstrainTo {
        public static String valueOr( String pToCheck, String pValueIfNull ) {
            return (pToCheck != null) ? pToCheck : pValueIfNull;
        }

        public static <T> T valueOr( T pToCheck, T pValueIfNull ) {
            return (pToCheck != null) ? pToCheck : pValueIfNull;
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
