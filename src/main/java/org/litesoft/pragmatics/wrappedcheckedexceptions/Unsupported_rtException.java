package org.litesoft.pragmatics.wrappedcheckedexceptions;

public class Unsupported_rtException extends RuntimeException {
    @SuppressWarnings("unused")
    public Unsupported_rtException( String message ) {
        super( message );
    }

    public Unsupported_rtException( Throwable cause ) {
        super( cause );
    }
}
