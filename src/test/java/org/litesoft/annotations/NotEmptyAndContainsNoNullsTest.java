package org.litesoft.annotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

class NotEmptyAndContainsNoNullsTest extends TestSupport {
    @Override
    protected String getExpectation() {
        return NotEmptyAndContainsNoNulls.EXPECTATION;
    }

    private static final List<CheckParamsList<String>> OurParams = new ListOf<CheckParamsList<String>>()
            .with( new CheckParamsList<>( true, "Spaces", Collections.singletonList( " " ) ) )
            .with( new CheckParamsList<>( false, "empty", Collections.emptyList() ) )
            .with( new CheckParamsList<>( false, "with_null", Arrays.asList( " ", null ) ) )
            .with( new CheckParamsList<>( false, "null", null ) );

    @Test
    void _Check() {
        check_CheckList( NotEmptyAndContainsNoNulls.Check, OurParams );
    }

    @Test
    void _Validate() {
        check_ValidateList( NotEmptyAndContainsNoNulls.Validate, OurParams );
    }

    @Test
    void _Assert() {
        check_AssertList( NotEmptyAndContainsNoNulls.Assert, OurParams );
    }

    @Test
    void _AssertArgument() {
        new _Asserter_Collections_CheckParams( NotEmptyAndContainsNoNulls.AssertArgument, IllegalArgumentException.class )
                .checkAll( OurParams );
    }

    @Test
    void _AssertState() {
        new _Asserter_Collections_CheckParams( NotEmptyAndContainsNoNulls.AssertState, IllegalStateException.class )
                .checkAll( OurParams );
    }

    @Test
    void _AssertError() {
        new _Asserter_Collections_CheckParams( NotEmptyAndContainsNoNulls.AssertError, Error.class )
                .checkAll( OurParams );
    }
}