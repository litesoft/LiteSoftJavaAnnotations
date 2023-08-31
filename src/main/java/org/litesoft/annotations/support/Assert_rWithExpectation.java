package org.litesoft.annotations.support;

import java.util.function.Supplier;

public interface Assert_rWithExpectation<T> {
    T namedValue( String pName, T pToCheck );

    T contextValue( Supplier<String> pContext, T pToCheck );
}
