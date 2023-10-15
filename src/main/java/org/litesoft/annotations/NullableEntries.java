package org.litesoft.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.Objects;

import org.litesoft.pragmatics.Context;

/**
 * This class has been derived from the public domain code at: <a href="https://github.com/litesoft/LiteSoftCommonFoundation">LiteSoftCommonFoundation</a>
 */
@SuppressWarnings("unused")
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
public @interface NullableEntries {
    class ConstrainTo {
        public static <T> List<T> removeNulls( String pName, List<T> pList ) {
            return removeNulls( new Context( pName ), pList );
        }

        public static <T> List<T> removeNulls( Context pContext, List<T> pList ) {
            return NotNull.AssertArgument.contextValue( pContext, pList ).stream().filter( Objects::nonNull ).toList();
        }
    }
}
