package org.litesoft.pragmatics.wrappedcheckedexceptions;

public class Timeout_rtException extends RuntimeException {
    @SuppressWarnings("unused")
    public Timeout_rtException( String message ) {
        super( message );
    }

    public Timeout_rtException( Throwable cause ) {
        super( cause );
    }
}
