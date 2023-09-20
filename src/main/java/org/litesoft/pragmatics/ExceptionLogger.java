package org.litesoft.pragmatics;

import java.io.PrintStream;

import org.litesoft.annotations.NotNull;

/**
 * ExceptionLogger is an interface (and <code>PrintStream</code> based implementation)
 * for logging exceptions with isolation from any particular logging framework.
 */
public interface ExceptionLogger {
    void log( Exception e );

    @SuppressWarnings("unused")
    static ExceptionLogger with( PrintStream ps ) {
        NotNull.AssertError.namedValue( "PrintStream", ps );
        return ( e ) -> {
            if ( e != null ) {
                e.printStackTrace( System.out );
            }
        };
    }
}
