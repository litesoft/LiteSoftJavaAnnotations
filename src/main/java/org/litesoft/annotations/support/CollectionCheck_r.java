package org.litesoft.annotations.support;

import java.util.Collection;

import org.litesoft.annotations.NotChecked;
import org.litesoft.annotations.NotNull;

/**
 * Generic Collection "checker" that always checks for null, and if not null, checks for null entries and then delegates to <code>notNullCheck</code>.
 */
public class CollectionCheck_r {
    /**
     * Check of <code>pToCheck</code> is acceptable (<code>true</code>).
     *
     * @return true if <code>pToCheck</code> is not null and <code>notNullTestT</code> return true!
     */
    public boolean value( Collection<?> pToCheck ) {
        return (null != pToCheck) && anyNulls( pToCheck ) && notNullTestT( pToCheck ); // Left to Right!
    }

    static boolean anyNulls( @NotNull @NotChecked Collection<?> pToCheck ) {
        for ( Object entry : pToCheck ) {
            if ( entry == null ) {
                return false;
            }
        }
        return true;
    }

    protected boolean notNullTestT( Collection<?> pToCheck ) {
        return true;
    }
}
