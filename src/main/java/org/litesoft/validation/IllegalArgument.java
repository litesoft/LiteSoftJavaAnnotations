package org.litesoft.validation;

import org.litesoft.annotations.DefaultingOnInsignificant;
import org.litesoft.annotations.NotNull;
import org.litesoft.annotations.Nullable;
import org.litesoft.annotations.Significant;

@SuppressWarnings({"WeakerAccess", "unused"})
public class IllegalArgument {
  public static final String DEFAULT_ON_INSIGNIFICANT = ".!N/A!.";

  public static IllegalArgumentException expectationUnmet( @DefaultingOnInsignificant String pName,
                                                           @Nullable Object pFailedValue,
                                                           @DefaultingOnInsignificant String pExpectation ) {
    return new IllegalArgumentException( formatExpectationUnmet( wrapName( pName ) + " was expected",
                                                                 pFailedValue, pExpectation ) );
  }

  public static IllegalArgumentException exception( @NotNull Class<?> pHost,
                                                    @Nullable Object pHostId,
                                                    @NotNull Validation pValidation ) {
    throw new Error( "NIY" ); // TODO: XXX
  }

  public static String formatExpectationUnmet( @Nullable Object pFailedValue,
                                               @DefaultingOnInsignificant String pExpectation ) {
    return formatExpectationUnmet( "Expected", pFailedValue, pExpectation );
  }

  public static String format( Object pValue ) {
    return (pValue instanceof String) ? ("\"" + pValue + "\"") : String.valueOf( pValue );
  }

  private static String wrapName( String pName ) {
    pName = Significant.ConstrainTo.valueOrNull( pName );
    return (pName == null) ? DEFAULT_ON_INSIGNIFICANT : ("'" + pName + "'");
  }

  private static String formatExpectationUnmet( String pPrefix, Object pFailedValue, String pExpectation ) {
    return pPrefix + " to be " + Significant.ConstrainTo.valueOr( pExpectation, DEFAULT_ON_INSIGNIFICANT )
           + ", but was: " + format( pFailedValue );
  }
}
