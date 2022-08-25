package org.litesoft.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This class has been derived from the public domain code at: <a href="https://github.com/litesoft/LiteSoftCommonFoundation">LiteSoftCommonFoundation</a>
 */
@SuppressWarnings("unused")
@Retention(RetentionPolicy.CLASS)
@Target({
        ElementType.FIELD,
        ElementType.METHOD,
        ElementType.PARAMETER,
        ElementType.LOCAL_VARIABLE
})
public @interface AllSignificant {
}
