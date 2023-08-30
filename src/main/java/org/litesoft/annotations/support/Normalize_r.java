package org.litesoft.annotations.support;

/**
 * Generic "normalizer" that always checks for null, and if not null delegates to <code>notNullNormalize</code>.
 *
 * @param <T> the type to be checked and returned
 */
public abstract class Normalize_r<T> {
    public T normalizeToNull( T pToCheck ) {
        return (null == pToCheck) ? null : notNullNormalize( pToCheck );
    }

    abstract protected T notNullNormalize( T pToCheck );
}
