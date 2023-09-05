package org.litesoft.annotations;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

class NotEmptyTest extends TestTypedSupport {
    @Override
    protected String getExpectation() {
        return NotEmpty.EXPECTATION;
    }

    private static final List<CheckParams<String>> OurStringParams = new ListOf<CheckParams<String>>()
            .with( new CheckParams<>( true, "Space", " " ) )
            .with( new CheckParams<>( false, "!Space", "" ) )
            .with( new CheckParams<>( false, "null", null ) );

    private static final List<CheckParamsList<String>> OurListParams = new ListOf<CheckParamsList<String>>()
            .with( new CheckParamsList<>( true, "Spaces", Collections.singletonList( " " ) ) )
            .with( new CheckParamsList<>( false, "!Spaces", Collections.emptyList() ) )
            .with( new CheckParamsList<>( false, "nullList", null ) );

    @Test
    void _Check() {
        check_Check( NotEmpty.Check, OurStringParams );
        check_CheckList( NotEmpty.Check, OurListParams );
    }

    @Test
    void _Validate() {
        check_Validate( NotEmpty.Validate, OurStringParams );
        check_ValidateList( NotEmpty.Validate, OurListParams );
    }

    @Test
    void _Assert() {
        check_Assert( NotEmpty.Assert, OurStringParams );
        check_AssertList( NotEmpty.Assert, OurListParams );
    }

    @Test
    void _AssertArgument() {
        new _Asserter_Typed_CheckParams<>( NotEmpty.AssertArgument, IllegalArgumentException.class )
                .checkAll( OurStringParams );
        new _Asserter_Typed_CheckParamsList<>( NotEmpty.AssertArgument, IllegalArgumentException.class )
                .checkAll( OurListParams );
    }

    @Test
    void _AssertState() {
        new _Asserter_Typed_CheckParams<>( NotEmpty.AssertState, IllegalStateException.class )
                .checkAll( OurStringParams );
        new _Asserter_Typed_CheckParamsList<>( NotEmpty.AssertState, IllegalStateException.class )
                .checkAll( OurListParams );
    }

    @Test
    void _AssertError() {
        new _Asserter_Typed_CheckParams<>( NotEmpty.AssertError, Error.class )
                .checkAll( OurStringParams );
        new _Asserter_Typed_CheckParamsList<>( NotEmpty.AssertError, Error.class )
                .checkAll( OurListParams );
    }
}