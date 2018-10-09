package org.litesoft.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.litesoft.annotations.expectations.Expectation;
import org.litesoft.annotations.expectations.IllegalArgument;

/**
 * This class has been derived from the public domain code at: https://github.com/litesoft/LiteSoftCommonFoundation
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
public @interface NotNull {

  @SuppressWarnings({"WeakerAccess", "unused"})
  class Assert {
    public static <T> T namedValueExpectation( String pName, T pToCheck, Expectation pExpectation ) {
      if ( pToCheck == null ) {
        pExpectation.unmet( pName, null, "Not Null" );
      }
      return pToCheck;
    }
  }

  @SuppressWarnings({"WeakerAccess", "unused"})
  class AssertArgument {
    public static <T> T namedValue( String pName, T pToCheck )
            throws IllegalArgumentException {
      return Assert.namedValueExpectation( pName, pToCheck, IllegalArgument.INSTANCE );
    }
  }
}
