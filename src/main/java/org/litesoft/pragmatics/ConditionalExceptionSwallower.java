package org.litesoft.pragmatics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

public class ConditionalExceptionSwallower {
    private final List<Class<?>> types = new ArrayList<>();
    private final List<List<String>> messages = new ArrayList<>();

    public ConditionalExceptionSwallower addMessage( String... contains ) {
        if ( (contains == null) || (contains.length == 0) ) {
            throw new IllegalArgumentException( "null strings not allowed as a 'contains' option" );
        }
        for ( String str : contains ) {
            if ( str == null ) {
                throw new IllegalArgumentException( "null strings not allowed as a 'contains' option" );
            }
        }
        messages.add( Arrays.asList( contains ) );
        return this;
    }

    public <T extends Throwable> ConditionalExceptionSwallower addType( Class<T> type ) {
        if ( type == null ) {
            throw new IllegalArgumentException( "null type(s) not allowed" );
        }
        if ( !Throwable.class.isAssignableFrom( type ) ) {
            throw new IllegalArgumentException( type.getSimpleName() + " not a Throwable" );
        }
        if ( !types.contains( type ) ) {
            types.add( type );
        }
        return this;
    }

    public boolean shouldEat( Throwable e ) {
        if ( e != null ) {
            Map<Throwable, Boolean> seen = new IdentityHashMap<>(); // Handle looping "Causes"
            while ( Boolean.TRUE != seen.get( e ) ) {
                if (e == null) {
                    return false;
                }
                if ( checkMessages( e ) || checkClasses( e ) ) {
                    return true;
                }
                seen.put( e, Boolean.TRUE );
                e = e.getCause();
            }
        }
        return false;
    }

    private boolean checkClasses( Throwable e ) {
        Class<? extends Throwable> paramType = e.getClass();
        for ( Class<?> type : types ) {
            if ( checkClass( paramType, type ) ) {
                return true;
            }
        }
        return false;
    }

    private boolean checkMessages( Throwable e ) {
        String message = e.getMessage();
        for ( List<String> messageParts : messages ) {
            if ( checkMessageParts( message, messageParts ) ) {
                return true;
            }
        }
        return false;
    }

    static boolean checkMessageParts( String paramMessage, List<String> messagePartsToCheck ) {
        for ( String part : messagePartsToCheck ) {
            if ( !paramMessage.contains( part ) ) {
                return false;
            }
        }
        return true;
    }

    static boolean checkClass( Class<?> paramType, Class<?> typeToCheck ) {
        return typeToCheck.isAssignableFrom( paramType );
    }
}
