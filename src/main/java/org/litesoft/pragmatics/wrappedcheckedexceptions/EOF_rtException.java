package org.litesoft.pragmatics.wrappedcheckedexceptions;

public class EOF_rtException extends RuntimeException {
    @SuppressWarnings("unused")
    public EOF_rtException( String message ) {
        super( message );
    }

    public EOF_rtException( Throwable cause ) {
        super( cause );
    }
}
