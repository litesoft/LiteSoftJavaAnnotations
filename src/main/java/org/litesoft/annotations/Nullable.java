package org.litesoft.annotations;

import java.lang.annotation.*;

/**
 * This class has been derived from the public domain code at: https://github.com/litesoft/LiteSoftCommonFoundation
 */
@SuppressWarnings("unused")
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
public @interface Nullable {
}
