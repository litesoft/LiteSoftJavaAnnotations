package org.litesoft.pragmatics.wrappedcheckedexceptions;

public class XML_rtException extends RuntimeException {
    @SuppressWarnings("unused")
    public XML_rtException( String message ) {
        super( message );
    }

    public XML_rtException( Throwable cause ) {
        super( cause );
    }
}
