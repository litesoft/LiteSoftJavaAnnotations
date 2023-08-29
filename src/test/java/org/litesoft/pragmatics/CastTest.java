package org.litesoft.pragmatics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class CastTest {

    @Test
    void happyCases() {
        happyString( 1, null );
        happyString( 2, "" );
        happyString( 3, " " );
        happyInteger( 1, null );
        happyInteger( 2, 0 );
        happyInteger( 3, 1 );
    }

    void happyString( int id, String expected ) {
        String actual = "WT...";
        try {
            actual = Cast.it( expected );
        }
        catch ( RuntimeException e ) {
            fail( "happyString " + id + ": " + e.getMessage() );
        }
        assertEquals( expected, actual, "happyString " + id );
    }

    void happyInteger( int id, Integer expected ) {
        Integer actual = -1;
        try {
            actual = Cast.it( expected );
        }
        catch ( RuntimeException e ) {
            fail( "happyInteger " + id + ": " + e.getMessage() );
        }
        assertEquals( expected, actual, "happyInteger " + id );
    }

    @Test
    void sadCases() {
        sadString( 1 );
        sadString( 2 );
        sadString( 3 );
        sadInteger( "1" );
        sadInteger( "2" );
        sadInteger( "3" );
    }

    void sadString( int id ) {
        try {
            String result = Cast.it( id );
            fail( "sadString " + id + ": " + result );
        }
        catch ( ClassCastException e ) {
            Exceptions.swallowExpected( e );
        }
    }

    void sadInteger( String id ) {
        try {
            Integer result = Cast.it( id );
            fail( "sadInteger " + id + ": " + result );
        }
        catch ( ClassCastException e ) {
            Exceptions.swallowExpected( e );
        }
    }
}