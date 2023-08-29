package org.litesoft.pragmatics.wrappedcheckedexceptions;

public class SQL_rtException extends RuntimeException {
    @SuppressWarnings("unused")
    public SQL_rtException( String message ) {
        super( message );
    }

    public SQL_rtException( Throwable cause ) {
        super( cause );
    }
}
