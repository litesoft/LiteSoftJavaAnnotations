package org.litesoft.annotations;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignificantTest extends TestSupport {
    @Override
    protected String getExpectation() {
        return Significant.EXPECTATION;
    }

    private static final List<CheckParamsWithMorphedOutput<String>> OurParams = new ListOf<CheckParamsWithMorphedOutput<String>>()
            .with( new CheckParamsWithMorphedOutput<>( true, "JustA", "A", "A" ) )
            .with( new CheckParamsWithMorphedOutput<>( true, "SpacedA", " A ", "A" ) )
            .with( new CheckParamsWithMorphedOutput<>( false, "Spaces", "  ", null ) )
            .with( new CheckParamsWithMorphedOutput<>( false, "Space", " ", null ) )
            .with( new CheckParamsWithMorphedOutput<>( false, "!Space", "", null ) )
            .with( new CheckParamsWithMorphedOutput<>( false, "null", null, null ) );

    @Test
    void _Check() {
        check_Check( Significant.Check, OurParams );
    }

    @Test
    void _Validate() {
        check_Validate( Significant.Validate, OurParams );
    }

    @Test
    void _Assert() {
        check_Assert( Significant.Assert, OurParams );

        assertEquals( "Fred", Significant.AssertArgument.namedValue( "Error1", "Fred" ) );

        try {
            Object error2 = Significant.AssertArgument.namedValue( "Error2", null );
            fail( "Unexpected response of: " + error2 );
        }
        catch ( IllegalArgumentException expected ) {
            assertTrue( expected.getMessage().contains( "Error2" ) );
        }
    }

    @Test
    void _AssertArgument() {
        new _Asserter_Typed_CheckParamsWithMorphedOutput<>( Significant.AssertArgument, IllegalArgumentException.class )
                .checkAll( OurParams );
    }

    @Test
    void _AssertState() {
        new _Asserter_Typed_CheckParamsWithMorphedOutput<>( Significant.AssertState, IllegalStateException.class )
                .checkAll( OurParams );
    }

    @Test
    void _AssertError() {
        new _Asserter_Typed_CheckParamsWithMorphedOutput<>( Significant.AssertError, Error.class )
                .checkAll( OurParams );
    }
}