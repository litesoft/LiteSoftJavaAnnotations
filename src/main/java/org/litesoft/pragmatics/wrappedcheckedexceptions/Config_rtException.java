package org.litesoft.pragmatics.wrappedcheckedexceptions;

public class Config_rtException extends RuntimeException {
    @SuppressWarnings("unused")
    public Config_rtException( String message ) {
        super( message );
    }

    public Config_rtException( Throwable cause ) {
        super( cause );
    }
}
