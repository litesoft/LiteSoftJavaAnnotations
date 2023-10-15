package org.litesoft.pragmatics;

/**
 * A Simple approach to generating a newline terminated String -- single quotes (') are replaced with double quotes (").
 * <p>
 * Not quite as clean as the new multi-line text support, but allows for easily adding calculated values or constants!
 */
@SuppressWarnings("unused")
public class PoorMans {
    public static String textBlock( String... lines ) {
        StringBuilder sb = new StringBuilder( lines[0] );
        for ( int i = 1; i < lines.length; i++ ) {
            sb.append( '\n' ).append( lines[i] );
        }
        return sb.toString().replace( '\'', '"' );
    }
}
