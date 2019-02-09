package org.litesoft.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.litesoft.annotations.expectations.Expectation;
import org.litesoft.annotations.expectations.IllegalArgument;
import org.litesoft.annotations.expectations.Validator;

/**
 * This class has been derived from the public domain code at: https://github.com/litesoft/LiteSoftCommonFoundation
 */
@SuppressWarnings({"WeakerAccess", "unused"})
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
public @interface NotEmpty {
  String EXPECTATION = "Not Empty";

  /**
   * @see Validator
   */
  class Validate {
    public static boolean value( String pName, String pToCheck, Expectation pExpectation ) {
      boolean zAcceptable = Check.value( pToCheck );
      if ( !zAcceptable ) {
        pExpectation.unmet( pName, pToCheck, EXPECTATION );
      }
      return zAcceptable;
    }
  }

  class Check {
    public static boolean value( String pToCheck ) {
      return (null != pToCheck) && !pToCheck.isEmpty(); // Left to Right!
    }
  }

  class Assert {
    public static String namedValueExpectation( String pName, String pToCheck, Expectation pExpectation ) {
      boolean zAcceptable = Check.value( pToCheck );
      if ( !zAcceptable ) {
        pExpectation.unmet( pName, null, EXPECTATION );
      }
      return pToCheck;
    }
  }

  class AssertArgument {
    public static String namedValue( String pName, String pToCheck )
            throws IllegalArgumentException {
      return Assert.namedValueExpectation( pName, pToCheck, IllegalArgument.INSTANCE );
    }
  }
}
