package org.litesoft.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.litesoft.annotations.expectations.IllegalArgument;
import org.litesoft.annotations.expectations.IllegalState;
import org.litesoft.annotations.expectations.ThrowError;
import org.litesoft.annotations.support.Assert_rTypedWithNormalizer;
import org.litesoft.annotations.support.Assert_rTypedWithNormalizerAndExpectation;
import org.litesoft.annotations.support.Check_r;
import org.litesoft.annotations.support.SignificantNormalizer;
import org.litesoft.annotations.support.Validate_r;

/**
 * This class has been derived from the public domain code at: <a href="https://github.com/litesoft/LiteSoftCommonFoundation">LiteSoftCommonFoundation</a>
 */
@SuppressWarnings({"WeakerAccess", "unused"})
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
public @interface Significant {
    String EXPECTATION = "Significant";

    Check_r<String> Check = new Check_r<>() {
        @Override
        protected boolean notNullTestT( String pToCheck ) {
            return null != ConstrainTo.valueOrNull( pToCheck );
        }
    };

    /**
     * Legacy - Similar to <code>Assert</code> in that an <code>Expectation</code> thrower must be provided
     */
    Validate_r<String> Validate = new Validate_r<>( EXPECTATION, Check );

    /**
     * Generic Assertion that requires an <code>Expectation</code> thrower.
     *
     * @see org.litesoft.annotations.expectations.Expectation
     */
    Assert_rTypedWithNormalizer<String> Assert = new Assert_rTypedWithNormalizer<>( EXPECTATION, SignificantNormalizer.INSTANCE );

    Assert_rTypedWithNormalizerAndExpectation<String> AssertArgument = new Assert_rTypedWithNormalizerAndExpectation<>( EXPECTATION, SignificantNormalizer.INSTANCE, IllegalArgument.INSTANCE );
    Assert_rTypedWithNormalizerAndExpectation<String> AssertState = new Assert_rTypedWithNormalizerAndExpectation<>( EXPECTATION, SignificantNormalizer.INSTANCE, IllegalState.INSTANCE );
    Assert_rTypedWithNormalizerAndExpectation<String> AssertError = new Assert_rTypedWithNormalizerAndExpectation<>( EXPECTATION, SignificantNormalizer.INSTANCE, ThrowError.INSTANCE );

    class ConstrainTo {
        public static String valueOrNull( String pToCheck ) {
            return valueOr( pToCheck, null );
        }

        public static String valueOrEmpty( String pToCheck ) {
            return valueOr( pToCheck, "" );
        }

        public static String valueOr( String pToCheck, String pValueIfNotSignificant ) {
            // IMO, slightly more efficient than previous version checking isBlank()!
            return SignificantNormalizer.valueOr( pToCheck, pValueIfNotSignificant );
        }
    }
}
