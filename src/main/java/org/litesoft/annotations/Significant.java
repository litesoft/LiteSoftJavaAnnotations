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
public @interface Significant {

  @SuppressWarnings({"WeakerAccess", "unused"})
  class Check {
    public static boolean value( String pToCheck ) {
      return (null != ConstrainTo.valueOrNull( pToCheck ));
    }
  }

  @SuppressWarnings({"WeakerAccess", "unused"})
  class ConstrainTo {
    public static String valueOrNull( String pToCheck ) {
      return valueOr( pToCheck, null );
    }

    public static String valueOrEmpty( String pToCheck ) {
      return valueOr( pToCheck, "" );
    }

    public static String valueOr( String pToCheck, String pValueIfNotSignificant ) {
      if ( (pToCheck != null) && !pToCheck.isEmpty() ) { // Left to Right
        pToCheck = pToCheck.trim();
        if ( !pToCheck.isEmpty() ) {
          return pToCheck;
        }
      }
      return pValueIfNotSignificant;
    }
  }

  @SuppressWarnings({"WeakerAccess", "unused"})
  class Assert {
    public static String namedValue( String pName, String pToCheck )
            throws IllegalArgumentException {
      String zResult = ConstrainTo.valueOrNull( pToCheck );
      if ( zResult != null ) {
        return zResult;
      }
      throw IllegalArgument.expectationUnmet( pName, pToCheck, "Significant" );
    }
  }

  class Validate {
    public static String currently( String pToCheck ) {
      return null;
    }

    public static String confirm( String pName, String pToCheck ) {
      return pToCheck;
    }
  }
}
