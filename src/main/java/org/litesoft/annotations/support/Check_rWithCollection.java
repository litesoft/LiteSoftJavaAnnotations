package org.litesoft.annotations.support;

import java.util.Collection;
import java.util.function.Predicate;

public class Check_rWithCollection<T> extends Check_r<T> {
    private final Predicate<Collection<?>> notNullCollectionTest;

    public Check_rWithCollection( Predicate<Collection<?>> pNotNullCollectionTest ) {
        notNullCollectionTest = pNotNullCollectionTest;
    }

    public <E extends Collection<?>> boolean value( E pToCheck ) {
        return (null != pToCheck) && notNullCollectionTest.test(pToCheck); // Left to Right!
    }
}
