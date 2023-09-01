package org.litesoft.annotations;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

class RequiredTest extends TestSupport {
    @Override
    protected String getExpectation() {
        return Required.EXPECTATION;
    }

    private static final List<CheckParams<Object>> OurParams = new ListOf<CheckParams<Object>>()
            .with( new CheckParams<>( true, "Space", " " ) )
            .with( new CheckParams<>( true, "!Space", "" ) )
            .with( new CheckParams<>( true, "Spaces", Collections.singletonList( " " ) ) )
            .with( new CheckParams<>( true, "!Spaces", Collections.emptyList() ) )
            .with( new CheckParams<>( false, "null", null ) );

    @Test
    void _Check() {
        check_Check( Required.Check, OurParams );
    }

    @Test
    void _Validate() {
        check_Validate( Required.Validate, OurParams );
    }

    @Test
    void _Assert() {
        check_Assert( Required.Assert, OurParams );
    }

    @Test
    void _AssertArgument() {
        new _Asserter_Untyped_CheckParams( Required.AssertArgument, IllegalArgumentException.class )
                .checkAll( OurParams );
    }

    @Test
    void _AssertState() {
        new _Asserter_Untyped_CheckParams( Required.AssertState, IllegalStateException.class )
                .checkAll( OurParams );
    }

    @Test
    void _AssertError() {
        new _Asserter_Untyped_CheckParams( NotNull.AssertError, Error.class )
                .checkAll( OurParams );
    }
}