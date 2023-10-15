package org.litesoft.validation;

import org.litesoft.annotations.NotNull;
import org.litesoft.pragmatics.Context;

@SuppressWarnings("unused")
public abstract class ValidatableDTO {
    public final void validate( Context context ) {
        levelValidate( (context != null) ? context : new Context( this.getClass().getSimpleName() ) );
    }

    protected void levelValidate( @NotNull Context context ) {
    }

    protected boolean equalsNotSameNotNull( ValidatableDTO them ) {
        return true;
    }

    protected StringBuilder addFields() {
        return new StringBuilder()
                .append( this.getClass().getSimpleName() )
                .append( '{' );
    }

    protected static String noMoreFields( StringBuilder sb ) {
        return sb.append( '}' ).toString();
    }

    protected static StringBuilder appendField( StringBuilder sb, String name, Object value ) {
        if ( sb.charAt( sb.length() - 1 ) != '{' ) {
            sb.append( ", " );
        }
        sb.append( name ).append( "=" );
        if ( value == null ) {
            sb.append( "null" );
        } else if ( value instanceof String ) {
            sb.append( '\'' ).append( value ).append( '\'' );
        } else {
            sb.append( value );
        }
        return sb;
    }
}
