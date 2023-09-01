package org.litesoft.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.litesoft.annotations.expectations.IllegalArgument;
import org.litesoft.annotations.expectations.IllegalState;
import org.litesoft.annotations.expectations.ThrowError;
import org.litesoft.annotations.support.Assert_rTypedWithCollection;
import org.litesoft.annotations.support.Assert_rTypedWithExpectationWithCollection;
import org.litesoft.annotations.support.Check_rWithCollection;
import org.litesoft.annotations.support.Validate_rWithCollection;

/**
 * This class has been derived from the public domain code at: <a href="https://github.com/litesoft/LiteSoftCommonFoundation">LiteSoftCommonFoundation</a>
 */
@SuppressWarnings({"WeakerAccess", "unused"})
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
public @interface NotEmpty {
    String EXPECTATION = "Not Empty";

    Check_rWithCollection<String> Check = new Check_rWithCollection<>( c -> !c.isEmpty() ) {
        @Override
        protected boolean notNullTestT( String pToCheck ) {
            return !pToCheck.isEmpty();
        }
    };

    /**
     * Legacy - Similar to <code>Assert</code> in that an <code>Expectation</code> thrower must be provided
     */
    Validate_rWithCollection<String> Validate = new Validate_rWithCollection<>( EXPECTATION, Check );

    /**
     * Generic Assertion that requires an <code>Expectation</code> thrower.
     *
     * @see org.litesoft.annotations.expectations.Expectation
     */
    Assert_rTypedWithCollection<String> Assert = new Assert_rTypedWithCollection<>( EXPECTATION, Check );

    Assert_rTypedWithExpectationWithCollection<String> AssertArgument = new Assert_rTypedWithExpectationWithCollection<>( EXPECTATION, Check, IllegalArgument.INSTANCE );
    Assert_rTypedWithExpectationWithCollection<String> AssertState = new Assert_rTypedWithExpectationWithCollection<>( EXPECTATION, Check, IllegalState.INSTANCE );
    Assert_rTypedWithExpectationWithCollection<String> AssertError = new Assert_rTypedWithExpectationWithCollection<>( EXPECTATION, Check, ThrowError.INSTANCE );
}
