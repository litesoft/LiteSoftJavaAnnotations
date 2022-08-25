package org.litesoft.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This Annotation is used to flag (primarily) parameters, that are not checked.
 * <p>
 * This class has been derived from the public domain code at: <a href="https://github.com/litesoft/LiteSoftCommonFoundation">LiteSoftCommonFoundation</a>
 */
@SuppressWarnings({"unused", "WeakerAccess"})
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
public @interface NotChecked {
}
