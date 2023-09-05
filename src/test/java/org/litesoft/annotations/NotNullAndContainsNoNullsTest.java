package org.litesoft.annotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

class NotNullAndContainsNoNullsTest extends TestCollectionSupport {
    @Override
    protected String getExpectation() {
        return NotNullAndContainsNoNulls.EXPECTATION;
    }

    private static final List<CheckParamsList<String>> OurParams = new ListOf<CheckParamsList<String>>()
            .with( new CheckParamsList<>( true, "Spaces", Collections.singletonList( " " ) ) )
            .with( new CheckParamsList<>( true, "empty", Collections.emptyList() ) )
            .with( new CheckParamsList<>( false, "with_null", Arrays.asList( " ", null ) ) )
            .with( new CheckParamsList<>( false, "null", null ) );

    @Test
    void _Check() {
        check_CheckList( NotNullAndContainsNoNulls.Check, OurParams );
    }

    @Test
    void _Validate() {
        check_ValidateList( NotNullAndContainsNoNulls.Validate, OurParams );
    }

    @Test
    void _Assert() {
        check_AssertList( NotNullAndContainsNoNulls.Assert, OurParams );
    }

    @Test
    void _AssertArgument() {
        new _Asserter_Collections_CheckParams( NotNullAndContainsNoNulls.AssertArgument, IllegalArgumentException.class )
                .checkAll( OurParams );
    }

    @Test
    void _AssertState() {
        new _Asserter_Collections_CheckParams( NotNullAndContainsNoNulls.AssertState, IllegalStateException.class )
                .checkAll( OurParams );
    }

    @Test
    void _AssertError() {
        new _Asserter_Collections_CheckParams( NotNullAndContainsNoNulls.AssertError, Error.class )
                .checkAll( OurParams );
    }
}