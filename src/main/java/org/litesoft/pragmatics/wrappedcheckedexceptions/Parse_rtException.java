package org.litesoft.pragmatics.wrappedcheckedexceptions;

public class Parse_rtException extends RuntimeException {
    @SuppressWarnings("unused")
    public Parse_rtException( String message ) {
        super( message );
    }

    public Parse_rtException( Throwable cause ) {
        super( cause );
    }
}
