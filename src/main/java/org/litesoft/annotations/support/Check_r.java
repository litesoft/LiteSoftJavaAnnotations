package org.litesoft.annotations.support;

/**
 * Generic "checker" that always checks for null, and if not null delegates to <code>notNullCheck</code>.
 *
 * @param <T> the type to be checked
 */
public class Check_r<T> {
    public boolean value( T pToCheck ) {
        return (null != pToCheck) && notNullTestT( pToCheck ); // Left to Right!
    }

    protected boolean notNullTestT( T pToCheck ) {
        return true;
    }
}
