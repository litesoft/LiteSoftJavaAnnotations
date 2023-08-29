package org.litesoft.pragmatics.wrappedcheckedexceptions;

public class FileNotFound_rtException extends RuntimeException {
    @SuppressWarnings("unused")
    public FileNotFound_rtException( String message ) {
        super( message );
    }

    public FileNotFound_rtException( Throwable cause ) {
        super( cause );
    }
}
