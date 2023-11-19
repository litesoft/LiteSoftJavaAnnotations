package org.litesoft.pragmatics;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.Instant;

import org.litesoft.annotations.NotNull;

/**
 * ExceptionLogger is an interface (and <code>PrintStream</code> based implementation)
 * for logging exceptions with isolation from any particular logging framework.
 * <p>
 * Starting with version 3.0, Logging of non-Exceptions has been added (defaulting to <code>System.out</code>).
 * In addition, version 3.0 brings optional time stamp prefixes to the logging.
 */
@SuppressWarnings("unused")
public interface ExceptionLogger {
    String NON_EXCEPTION_TYPE = "N/A"; // used in collecting and managing logged messages (non-Exceptions)!

    void log( Exception e );

    default void log( String message ) {
        System.out.println( message );
    }

    @Deprecated
    static ExceptionLogger with( PrintStream ps ) {
        return with(ps, ps, false);
    }

    static ExceptionLogger deNullWithTimestamp(ExceptionLogger logger) {
        if (logger != null) {
            return logger;
        }
        return withTimestamp();
    }

    static ExceptionLogger withTimestamp() {
        return with( null, null, true );
    }

    static ExceptionLogger with( PrintStream exceptionPS, PrintStream messagePS, boolean addTimestamp ) {
        PrintStream ePS = NotNull.ConstrainTo.valueOr( exceptionPS, System.err );
        PrintStream mPS = NotNull.ConstrainTo.valueOr( messagePS, System.out );
        return new ExceptionLogger() {
            @Override
            public void log( String message ) {
                log( mPS, true, message, false, null );
            }

            @Override
            public void log( Exception e ) {
                log( ePS, false, null, true, e );
            }

            private void log( PrintStream actualPS, boolean addMessage, String message, boolean addException, Exception e ) {
                Instant now = addTimestamp ? Instant.now() : null;
                String output;
                try ( ByteArrayOutputStream os = new ByteArrayOutputStream(); PrintStream ps = new PrintStream( os ) ) {
                    if ( now != null ) {
                        ps.print( now );
                        ps.print( " -- " );
                    }
                    if ( addMessage ) {
                        ps.print( message );
                    }
                    if ( addException ) {
                        if ( e == null ) {
                            ps.print( "null" );
                        } else {
                            e.printStackTrace( ps );
                        }
                    }
                    output = os.toString();
                }
                catch ( Exception unexpected ) {
                    new Error( "Unexpected Exception", unexpected ).printStackTrace( actualPS );
                    return;
                }
                actualPS.println( output );
            }
        };
    }
}
