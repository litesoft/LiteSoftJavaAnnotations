package org.litesoft.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.litesoft.annotations.expectations.IllegalArgument;
import org.litesoft.annotations.expectations.IllegalState;
import org.litesoft.annotations.expectations.ThrowError;
import org.litesoft.annotations.support.CollectionAssert_r;
import org.litesoft.annotations.support.CollectionAssert_rWithExpectation;
import org.litesoft.annotations.support.CollectionCheck_r;
import org.litesoft.annotations.support.CollectionValidate_r;

/**
 * This class has been derived from the public domain code at: <a href="https://github.com/litesoft/LiteSoftCommonFoundation">LiteSoftCommonFoundation</a>
 */
@SuppressWarnings({"WeakerAccess", "unused"})
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
public @interface NotNullAndContainsNoNulls {
    String EXPECTATION = "Not Null And Contains No Nulls";

    CollectionCheck_r Check = new CollectionCheck_r();

    CollectionValidate_r Validate = new CollectionValidate_r( EXPECTATION, Check );

    CollectionAssert_r Assert = new CollectionAssert_r( EXPECTATION, Check );

    CollectionAssert_rWithExpectation AssertArgument = new CollectionAssert_rWithExpectation( EXPECTATION, Check, IllegalArgument.INSTANCE );
    CollectionAssert_rWithExpectation AssertState = new CollectionAssert_rWithExpectation( EXPECTATION, Check, IllegalState.INSTANCE );
    CollectionAssert_rWithExpectation AssertError = new CollectionAssert_rWithExpectation( EXPECTATION, Check, ThrowError.INSTANCE );
}
