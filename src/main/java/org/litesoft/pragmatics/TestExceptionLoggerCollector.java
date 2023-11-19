package org.litesoft.pragmatics;

import java.util.ArrayList;
import java.util.List;

public class TestExceptionLoggerCollector implements ExceptionLogger {
    record Entry(String type, String message) {
    }

    private final List<Entry> entries = new ArrayList<>();

    public boolean hasEntries() {
        return !entries.isEmpty();
    }

    /**
     * Find all the <code>entries</code> that contain <B>ALL</B> the values as
     * <code>subString()</code>s of the <code>entry</code>'s <code>message</code>.
     * <p>
     * Note: As <code>null</code> and empty values are ignored, to be included in the results,
     * at least one of the <code>values</code> members must not be <code>null</code> or empty,
     * and obviously ALL non-<code>null</code> and non-empty members must be found in the
     * <code>entry</code>'s <code>message</code>.
     *
     * @param values substrings to check the <code>entry</code>'s <code>message</code> for contains.
     * @return non-Null list of matched entries -- often empty!
     */
    public List<String> getEntriesWithMessageContains( String... values ) {
        if ( (values == null) || (0 == values.length) || entries.isEmpty() ) {
            return List.of();
        }
        List<String> found = new ArrayList<>();
        for ( Entry entry : entries ) {
            if ( shouldSelect( entry, values ) ) {
                found.add( addEntry( entry, new StringBuilder() ).toString() );
            }
        }
        return found;
    }

    @Override
    public void log( Exception e ) {
        String type = null;
        String message = null;
        if ( e != null ) {
            type = e.getClass().getSimpleName();
            message = e.getMessage();
        }
        entries.add( new Entry( type, message ) );
    }

    @Override
    public void log( String message ) {
        entries.add( new Entry( NON_EXCEPTION_TYPE, message ) );
    }

    @Override
    public String toString() {
        int size = entries.size();
        StringBuilder sb = new StringBuilder()
                .append( size )
                .append( ' ' )
                .append( (size == 1) ? "Entry" : "Entries" );
        if ( size != 0 ) {
            sb.append( ':' );
            for ( Entry entry : entries ) {
                addEntry( entry, sb.append( "\n  " ) );
            }
        }
        return sb.toString();
    }

    private static StringBuilder addEntry( Entry entry, StringBuilder sb ) {
        String type = entry.type();
        sb.append( type );
        if ( NON_EXCEPTION_TYPE.equals( type ) ) {
            return sb.append( ": " ).append( entry.message() );
        }
        return sb.append( '(' ).append( entry.message() ).append( ')' );
    }

    private static boolean shouldSelect( Entry entry, String[] values ) {
        String message = entry.message;
        boolean result = false;
        if ( (message != null) && !message.isEmpty() ) {
            for ( String value : values ) {
                if ( (value != null) && !value.isEmpty() ) {
                    if (!(result = message.contains( value )) ) {
                        return false;
                    }
                }
            }
        }
        return result;
    }
}
