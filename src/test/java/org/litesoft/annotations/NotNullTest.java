package org.litesoft.annotations;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

class NotNullTest extends TestSupport {
    private static final Object[] EMPTY = {};
    private static final Object[] JUST_NULLS = {null};
    private static final Object[] VALUES = {5, 2, null, 7, "Martin Fowler"};

    @Override
    protected String getExpectation() {
        return NotNull.EXPECTATION;
    }

    @Test
    void _Check() {
        assertTrue( NotNull.Check.value( " " ) );
        assertTrue( NotNull.Check.value( "" ) );
        assertFalse( NotNull.Check.value( (String)null ) );

        assertTrue( NotNull.Check.value( Collections.singletonList( " " ) ) );
        assertTrue( NotNull.Check.value( Collections.emptyList() ) );
        assertFalse( NotNull.Check.value( (List<String>)null ) );
    }

    @Test
    void _Validate() {
        checkV( true, "Spaces", Collections.singletonList( " " ) );
        checkV( true, "Space", " " );
        checkV( true, "!Spaces", Collections.emptyList() );
        checkV( true, "!Space", "" );
        checkV( false, "null", null );
    }

    void checkV( boolean expected, String pContext, Object pToCheck ) {
        Exp expectation = new Exp();
        assertEquals( expected, NotNull.Validate.value( pContext, pToCheck, expectation ) );
        checkExpectation( expected, expectation.params, pContext, pToCheck );
    }

    @Test
    void _Assert() {
        checkA( true, "Spaces", Collections.singletonList( " " ) );
        checkA( true, "Space", " " );
        checkA( true, "!Spaces", Collections.emptyList() );
        checkA( true, "!Space", "" );
        checkA( false, "null", null );

        assertEquals( "Fred", NotNull.Assert.errorOn( "Error1", "Fred" ) );

        try {
            Object error2 = NotNull.Assert.errorOn( "Error2", null );
            fail("Unexpected response of: " + error2);
        }
        catch ( Error expected ) {
            assertTrue( expected.getMessage().contains( "Error2" ) );
        }
    }

    void checkA( boolean expected, String pContext, Object pToCheck ) {
        Exp expectation = new Exp();
        assertEquals( pToCheck, NotNull.Assert.namedValueExpectation( pContext, pToCheck, expectation ) );
        checkExpectation( expected, expectation.params, pContext, pToCheck );
    }

    @Test
    void countNotNull() {
        assertEquals( 0, NotNull.Count.of( EMPTY ), "EMPTY" );
        assertEquals( 0, NotNull.Count.of( JUST_NULLS ), "JUST_NULLS" );
        assertEquals( 4, NotNull.Count.of( VALUES ), "VALUES" );
    }

    @Test
    void constrainTo() {
        assertEquals( "whatever", NotNull.ConstrainTo.valueOr( "whatever", "!whatever" ) );
        assertEquals( "!whatever", NotNull.ConstrainTo.valueOr( null, "!whatever" ) );
    }
}