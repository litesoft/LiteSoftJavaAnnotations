package org.litesoft.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.litesoft.validation.IllegalArgument;

/**
 * This class has been derived from the public domain code at: https://github.com/litesoft/LiteSoftCommonFoundation
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
public @interface NotNull {

  @SuppressWarnings({"WeakerAccess", "unused"})
  class Assert {
    public static <T> T namedValue( String pName, T pToCheck )
            throws IllegalArgumentException {
      if ( pToCheck != null ) {
        return pToCheck;
      }
      throw IllegalArgument.expectationUnmet( pName, null, "Not Null" );
    }
  }
}
