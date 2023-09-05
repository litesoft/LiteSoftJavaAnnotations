package org.litesoft.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.litesoft.annotations.expectations.IllegalArgument;
import org.litesoft.annotations.expectations.IllegalState;
import org.litesoft.annotations.expectations.ThrowError;
import org.litesoft.annotations.support.Assert_rNumber;
import org.litesoft.annotations.support.Assert_rNumberWithExpectation;
import org.litesoft.annotations.support.Check_rNumber;
import org.litesoft.annotations.support.Validate_rNumber;

/**
 * This class has been derived from the public domain code at: <a href="https://github.com/litesoft/LiteSoftCommonFoundation">LiteSoftCommonFoundation</a>
 */
@SuppressWarnings({"WeakerAccess", "unused"})
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
public @interface NotNegative {
    String EXPECTATION = "Not Negative";

    Check_rNumber Check = new Check_rNumber() {
        @Override
        public boolean longTestIt( long pToCheck ) {
            return Numbers.ZERO_long <= pToCheck;
        }

        @Override
        protected boolean notNanTestIt( double pToCheck ) {
            return Numbers.ZERO_double <= pToCheck;
        }

        @Override
        protected boolean notNullTestIt( BigInteger pToCheck ) {
            return 0 <= pToCheck.compareTo( Numbers.ZERO_BigInteger );
        }

        @Override
        protected boolean notNullTestIt( BigDecimal pToCheck ) {
            return 0 <= pToCheck.compareTo( Numbers.ZERO_BigDecimal );
        }
    };

    /**
     * Legacy - Similar to <code>Assert</code> in that an <code>Expectation</code> thrower must be provided
     */
    Validate_rNumber Validate = new Validate_rNumber( EXPECTATION, Check );

    /**
     * Generic Assertion that requires an <code>Expectation</code> thrower.
     *
     * @see org.litesoft.annotations.expectations.Expectation
     */
    Assert_rNumber Assert = new Assert_rNumber( EXPECTATION, Check );

    Assert_rNumberWithExpectation AssertArgument = new Assert_rNumberWithExpectation( EXPECTATION, Check, IllegalArgument.INSTANCE );
    Assert_rNumberWithExpectation AssertState = new Assert_rNumberWithExpectation( EXPECTATION, Check, IllegalState.INSTANCE );
    Assert_rNumberWithExpectation AssertError = new Assert_rNumberWithExpectation( EXPECTATION, Check, ThrowError.INSTANCE );
}
