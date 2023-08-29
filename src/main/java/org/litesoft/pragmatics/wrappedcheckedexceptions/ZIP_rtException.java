package org.litesoft.pragmatics.wrappedcheckedexceptions;

public class ZIP_rtException extends RuntimeException {
    @SuppressWarnings("unused")
    public ZIP_rtException( String message ) {
        super( message );
    }

    public ZIP_rtException( Throwable cause ) {
        super( cause );
    }
}
