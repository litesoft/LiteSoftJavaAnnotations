package org.litesoft.pragmatics.wrappedcheckedexceptions;

public class IO_rtException extends RuntimeException {
    @SuppressWarnings("unused")
    public IO_rtException( String message ) {
        super( message );
    }

    public IO_rtException( Throwable cause ) {
        super( cause );
    }
}
