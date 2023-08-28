package org.litesoft.annotations.support;

import java.util.Collection;
import java.util.function.Predicate;

/**
 * A Check_r that adds support for Collections.
 *
 * @param <T> the type to be checked FOR NON-COLLECTIONS!
 */
public class Check_rWithCollection<T> extends Check_r<T> {
    private final Predicate<Collection<?>> mNotNullCollectionTest;

    public Check_rWithCollection( Predicate<Collection<?>> pNotNullCollectionTest ) {
        mNotNullCollectionTest = pNotNullCollectionTest;
    }

    public <E extends Collection<?>> boolean value( E pToCheck ) {
        return (null != pToCheck) && mNotNullCollectionTest.test( pToCheck ); // Left to Right!
    }
}
