package org.litesoft.pragmatics;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.litesoft.pragmatics.wrappedcheckedexceptions.CheckedExceptionWrapper;

@SuppressWarnings("unused")
public class Exceptions {
    /**
     * Swallows expected exceptions
     *
     * @param ignored expected exception to ignore or swallow
     */
    public static void swallowExpected( Exception ignored ) {
    }

    public static void exitWithMessage( Exception e ) {
        exitWith( e, false );
    }

    public static void exitWithStacktrace( Exception e ) {
        exitWith( e, true );
    }

    public static void propagate( Exception e ) {
        if ( e instanceof RuntimeException ) {
            throw (RuntimeException)e;
        }
        CheckedExceptionWrapper.wrap( e ); // catch-all w/ grouping/categories!
    }

    private static void exitWith(Exception e, boolean withStackTrace) {
        System.err.println( renderExitWith( e, withStackTrace ) );
        System.exit( 1 );
    }

    static String renderExitWith(Exception e, boolean withStackTrace) {
        StringWriter writer = new StringWriter();
        PrintWriter pw = new PrintWriter( writer );
        pw.println();
        pw.println("*".repeat( 64 ));
        if (withStackTrace) {
            e.printStackTrace( pw );
        } else {
            pw.println(e.getClass().getSimpleName() + ": " + e.getMessage());
        }
        pw.close();
        return writer.toString();
    }
}
