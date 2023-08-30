package org.litesoft.annotations;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.litesoft.annotations.support.Assert_rTypedWithNormalizerAndExpectation;

import static org.junit.jupiter.api.Assertions.*;

class SignificantTest extends TestSupport {
    @Override
    protected String getExpectation() {
        return Significant.EXPECTATION;
    }

    private static final List<CheckParamsWithResults<String>> OurParams = new ListOf<CheckParamsWithResults<String>>()
            .with( new CheckParamsWithResults<>( true, "JustA", "A", "A" ) )
            .with( new CheckParamsWithResults<>( true, "SpacedA", " A ", "A" ) )
            .with( new CheckParamsWithResults<>( false, "Spaces", "  ", null ) )
            .with( new CheckParamsWithResults<>( false, "Space", " ", null ) )
            .with( new CheckParamsWithResults<>( false, "!Space", "", null ) )
            .with( new CheckParamsWithResults<>( false, "null", null, null ) );

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
        new _Asserter( Significant.AssertArgument, IllegalArgumentException.class )
                .checkAll();
    }

    @Test
    void _AssertState() {
        new _Asserter( Significant.AssertState, IllegalStateException.class )
                .checkAll();
    }

    @Test
    void _AssertError() {
        new _Asserter( Significant.AssertError, Error.class )
                .checkAll();
    }

    private static class _Asserter {
        private final Assert_rTypedWithNormalizerAndExpectation<String> asserter;
        private final Class<?> expectedThrowableClass;

        public _Asserter( Assert_rTypedWithNormalizerAndExpectation<String> pAsserter, Class<?> pExpectedThrowableClass ) {
            asserter = pAsserter;
            expectedThrowableClass = pExpectedThrowableClass;
        }

        void checkAll() {
            check( true, "JustA", "A", "A" );
            check( true, "SpacedA", " A ", "A" );
            check( false, "Spaces", "  ", null );
            check( false, "Space", " ", null );
            check( false, "!Space", "", null );
            check( false, "null", null, null );
        }

        void check( boolean expected, String pContext, String pToCheck, String pResult ) {
            String actual;
            try {
                actual = asserter.namedValue( pContext, pToCheck );
            }
            catch ( Throwable e ) {
                checkT( expected, e );
                return;
            }
            assertEquals( pResult, actual );
        }

        void checkT( boolean expected, Throwable t ) {
            if ( expected ) {
                assertNull( t );
            } else {
                assertInstanceOf( expectedThrowableClass, t );
            }
        }
    }

    @Test
    void constrainTo() {
        String whatever = "whatever";
        String spacedWhatever = " whatever ";

        assertEquals( whatever, Significant.ConstrainTo.valueOrEmpty( whatever ) );
        assertEquals( whatever, Significant.ConstrainTo.valueOrEmpty( spacedWhatever ) );
        assertEquals( "", Significant.ConstrainTo.valueOrEmpty( null ) );
        assertEquals( "", Significant.ConstrainTo.valueOrEmpty( " " ) );

        assertEquals( whatever, Significant.ConstrainTo.valueOrNull( whatever ) );
        assertEquals( whatever, Significant.ConstrainTo.valueOrNull( spacedWhatever ) );
        assertNull( Significant.ConstrainTo.valueOrNull( null ) );
        assertNull( Significant.ConstrainTo.valueOrNull( " " ) );
    }
}