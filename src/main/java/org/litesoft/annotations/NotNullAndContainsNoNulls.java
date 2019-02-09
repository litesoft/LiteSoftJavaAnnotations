package org.litesoft.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Collection;

import org.litesoft.annotations.expectations.Expectation;
import org.litesoft.annotations.expectations.IllegalArgument;
import org.litesoft.annotations.expectations.Validator;

/**
 * This class has been derived from the public domain code at: https://github.com/litesoft/LiteSoftCommonFoundation
 */
@SuppressWarnings({"WeakerAccess", "unused"})
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
public @interface NotNullAndContainsNoNulls {
  String EXPECTATION = "Not Null And Contains No Nulls";

  /**
   * @see Validator
   */
  class Validate {
    public static <Entry, T extends Collection<Entry>> boolean value( String pName, T pToCheck, Expectation pExpectation ) {
      boolean zAcceptable = Check.value( pToCheck );
      if ( !zAcceptable ) {
        pExpectation.unmet( pName, pToCheck, EXPECTATION );
      }
      return zAcceptable;
    }
  }

  class Check {
    public static <Entry, T extends Collection<Entry>> boolean value( T pToCheck ) {
      if (null == pToCheck) {
        return false;
      }
      return anyNulls( pToCheck );
    }

    static <Entry, T extends Collection<Entry>> boolean anyNulls( @NotNull @NotChecked T pToCheck ) {
      for ( Entry zEntry : pToCheck ) {
        if (zEntry == null) {
          return false;
        }
      }
      return true;
    }
  }

  class Assert {
    public static <Entry, T extends Collection<Entry>> T namedValueExpectation( String pName, T pToCheck, Expectation pExpectation ) {
      boolean zAcceptable = Check.value( pToCheck );
      if ( !zAcceptable ) {
        pExpectation.unmet( pName, null, EXPECTATION );
      }
      return pToCheck;
    }
  }

  class AssertArgument {
    public static <Entry, T extends Collection<Entry>> T namedValue( String pName, T pToCheck )
            throws IllegalArgumentException {
      return Assert.namedValueExpectation( pName, pToCheck, IllegalArgument.INSTANCE );
    }
  }
}
