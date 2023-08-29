package org.litesoft.pragmatics.wrappedcheckedexceptions;

public class WrappedCheckedException extends RuntimeException {
    public WrappedCheckedException( Throwable cause ) {
        super( cause );
    }
}
