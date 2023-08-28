package org.litesoft.annotations;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotEmptyTest extends TestSupport {
    @Override
    protected String getExpectation() {
        return NotEmpty.EXPECTATION;
    }

    @Test
    void _Check() {
        assertTrue( NotEmpty.Check.value( " " ) );
        assertFalse( NotEmpty.Check.value( "" ) );
        assertFalse( NotEmpty.Check.value( (String)null ) );

        assertTrue( NotEmpty.Check.value( Collections.singletonList( " " ) ) );
        assertFalse( NotEmpty.Check.value( Collections.emptyList() ) );
        assertFalse( NotEmpty.Check.value( (List<String>)null ) );
    }

    @Test
    void _Validate() {
        checkV( true, "Spaces", Collections.singletonList( " " ) );
        checkV( true, "Space", " " );
        checkV( false, "!Spaces", Collections.emptyList() );
        checkV( false, "!Space", "" );
        checkV( false, "null", (String)null );
        checkV( false, "nullList", (List<String>)null );
    }

    void checkV( boolean expected, String pContext, String pToCheck ) {
        Exp expectation = new Exp();
        assertEquals( expected, NotEmpty.Validate.value( pContext, pToCheck, expectation ) );
        checkExpectation( expected, expectation.params, pContext, pToCheck );
    }

    void checkV( boolean expected, String pContext, List<String> pToCheck ) {
        Exp expectation = new Exp();
        assertEquals( expected, NotEmpty.Validate.value( pContext, pToCheck, expectation ) );
        checkExpectation( expected, expectation.params, pContext, pToCheck );
    }

    @Test
    void _Assert() {
        checkA( true, "Spaces", Collections.singletonList( " " ) );
        checkA( true, "Space", " " );
        checkA( false, "!Spaces", Collections.emptyList() );
        checkA( false, "!Space", "" );
        checkA( false, "null", (String)null );
        checkA( false, "nullList", (List<String>)null );
    }

    void checkA( boolean expected, String pContext, String pToCheck ) {
        Exp expectation = new Exp();
        assertEquals( pToCheck, NotEmpty.Assert.namedValueExpectation( pContext, pToCheck, expectation ) );
        checkExpectation( expected, expectation.params, pContext, pToCheck );
    }

    void checkA( boolean expected, String pContext, List<String> pToCheck ) {
        Exp expectation = new Exp();
        assertEquals( pToCheck, NotEmpty.Assert.namedValueExpectation( pContext, pToCheck, expectation ) );
        checkExpectation( expected, expectation.params, pContext, pToCheck );
    }

    @Test
    void _AssertExceptionArgument() {

        checkEArgument( true, "Spaces", Collections.singletonList( " " ) );
        checkEArgument( true, "Space", " " );
        checkEArgument( false, "!Spaces", Collections.emptyList() );
        checkEArgument( false, "!Space", "" );
        checkEArgument( false, "null", (String)null );
        checkEArgument( false, "nullList", (List<String>)null );
    }

    void checkEArgument( boolean expected, String pContext, String pToCheck ) {
        RuntimeException rte = null;
        try {
            assertEquals( pToCheck, NotEmpty.AssertArgument.namedValue( pContext, pToCheck ) );
        } catch (RuntimeException e) {
            rte = e;
        }
        checkExpectationArgument( expected, rte );
    }

    void checkEArgument( boolean expected, String pContext, List<String> pToCheck ) {
        RuntimeException rte = null;
        try {
            assertEquals( pToCheck, NotEmpty.AssertArgument.namedValue( pContext, pToCheck ) );
        } catch (RuntimeException e) {
            rte = e;
        }
        checkExpectationArgument( expected, rte );
    }

    void checkExpectationArgument( boolean expected, RuntimeException rte ) {
        if ( expected ) {
            assertNull( rte );
        } else {
            assertInstanceOf( IllegalArgumentException.class, rte );
        }
    }

    @Test
    void _AssertExceptionState() {
        checkEState( true, "Spaces", Collections.singletonList( " " ) );
        checkEState( true, "Space", " " );
        checkEState( false, "!Spaces", Collections.emptyList() );
        checkEState( false, "!Space", "" );
        checkEState( false, "null", (String)null );
        checkEState( false, "nullList", (List<String>)null );
    }

    void checkEState( boolean expected, String pContext, String pToCheck ) {
        RuntimeException rte = null;
        try {
            assertEquals( pToCheck, NotEmpty.AssertState.namedValue( pContext, pToCheck ) );
        } catch (RuntimeException e) {
            rte = e;
        }
        checkExpectationState( expected, rte );
    }

    void checkEState( boolean expected, String pContext, List<String> pToCheck ) {
        RuntimeException rte = null;
        try {
            assertEquals( pToCheck, NotEmpty.AssertState.namedValue( pContext, pToCheck ) );
        } catch (RuntimeException e) {
            rte = e;
        }
        checkExpectationState( expected, rte );
    }

    void checkExpectationState( boolean expected, RuntimeException rte ) {
        if ( expected ) {
            assertNull( rte );
        } else {
            assertInstanceOf( IllegalStateException.class, rte );
        }
    }
}