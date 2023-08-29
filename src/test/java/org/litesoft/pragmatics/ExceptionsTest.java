package org.litesoft.pragmatics;

import java.util.function.Function;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.litesoft.pragmatics.Exceptions.renderExitWith;

class ExceptionsTest {

    @Test
    void _renderExitWith() {
        checkWithStackTrace( Exception::new, "T1" );
        checkWithStackTrace( RuntimeException::new, "T2" );
        checkWithoutStackTrace( Exception::new, "T3" );
        checkWithoutStackTrace( RuntimeException::new, "T4" );
    }

    void checkWithStackTrace( Function<String, Exception> constructor, String param ) {
        String postPrefix = checkCommon( constructor.apply( param ), param, true, Class::getName );
        if ( !postPrefix.contains( "ExceptionsTest._renderExitWith" ) ) {
            assertEquals( "...ExceptionsTest._renderExitWith...", postPrefix );
        }
    }

    void checkWithoutStackTrace( Function<String, Exception> constructor, String param ) {
        assertEquals( "", checkCommon( constructor.apply( param ), param, false, Class::getSimpleName ) );
    }

    String checkCommon( Exception e, String param, boolean withStackTrace, Function<Class<?>, String> extractName ) {
        String output = renderExitWith( e, withStackTrace );
        String prefix = "\n" + "*".repeat( 64 ) + "\n" + extractName.apply( e.getClass() ) + ": " + param + "\n";
        if ( !output.startsWith( prefix ) ) {
            assertEquals( prefix, output );
        }
        return output.substring( prefix.length() );
    }
}