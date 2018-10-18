package org.litesoft.validation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class ValidationTest {
  @Test
  public void test_OK() {
    Assert.assertSame( Validation.OK,
                       assertState( Validation.OK,
                                    false,
                                    hostErrors(),
                                    new FieldErrors(),
                                    null, null,
                                    (String[])null ) );
  }

  @Test
  public void test_HostError() {
    String[] zErrorLines = {":",
                            "    Host Error: BadHost",
                            };
    Validation zValidation = Validation.OK.addHostError( "BadHost" );
    Assert.assertNotSame( Validation.OK,
                          assertState( zValidation,
                                       true,
                                       hostErrors( "BadHost" ),
                                       new FieldErrors(),
                                       String.class, null,
                                       zErrorLines ) );
    assertState( zValidation,
                 true,
                 hostErrors( "BadHost" ),
                 new FieldErrors(),
                 String.class, 42,
                 zErrorLines );
  }

  @Test
  public void test_HostErrors() {
    assertState( Validation.OK
                         .addHostError( "1st Host" )
                         .addHostError( "Host Last" ),
                 true,
                 hostErrors( "1st Host", "Host Last" ),
                 new FieldErrors(),
                 String.class, null,
                 ":",
                 "    Host Errors:",
                 "        1st Host",
                 "        Host Last" );
  }

  @Test
  public void test_1FieldError() {
    assertState( Validation.OK
                         .addFieldError( "Name", "AnError" ),
                 true,
                 hostErrors(),
                 new FieldErrors().add( "Name", "AnError" ),
                 String.class, null,
                 ":",
                 "    Field 'Name' Error: AnError" );
  }

  @Test
  public void test_1FieldErrors() {
    assertState( Validation.OK
                         .addFieldError( "Name", "AnError" )
                         .addFieldError( "Name", "Error2" ),
                 true,
                 hostErrors(),
                 new FieldErrors().add( "Name", "AnError", "Error2" ),
                 String.class, null,
                 ":",
                 "    Field 'Name' Errors:",
                 "        AnError",
                 "        Error2" );
  }

  @Test
  public void test_2FieldsErrors() {
    assertState( Validation.OK
                         .addFieldError( "Name", "AnError" )
                         .addFieldError( "Value", "Error1" )
                         .addFieldError( "Value", "Error2" ),
                 true,
                 hostErrors(),
                 new FieldErrors()
                         .add( "Name", "AnError" )
                         .add( "Value", "Error1", "Error2" ),
                 String.class, null,
                 ":",
                 "    Field Errors:",
                 "        Name: AnError",
                 "        Value:",
                 "            Error1",
                 "            Error2" );
  }

  private List<String> hostErrors( String... pErrors ) {
    return Arrays.asList( pErrors );
  }

  private static class FieldErrors {
    private Map<String, List<String>> mExpected = new HashMap<String, List<String>>();

    FieldErrors add( String pFieldRef, String... pErrors ) {
      mExpected.put( pFieldRef, Arrays.asList( pErrors ) );
      return this;
    }

    void validate( Map<String, List<String>> pActual ) {
      Assert.assertEquals( "FieldErrors (Keys)", mExpected.keySet(), pActual.keySet() );
      for ( String zKey : pActual.keySet() ) {
        Assert.assertEquals( "FieldErrors (Key:" + zKey + ")",
                             mExpected.get( zKey ), pActual.get( zKey ) );
      }
    }
  }

  private Validation assertState( Validation pActual,
                                  boolean pHasErrorsExpected,
                                  List<String> pHostErrorsExpected,
                                  FieldErrors pFieldErrorsExpected,
                                  Class<?> pHostExpected, Object pHostIdExpected,
                                  String... pFormatAndToStringSuffixLinesExpected ) {

    Assert.assertEquals( "HasErrors", pHasErrorsExpected, pActual.hasErrors() );
    Assert.assertEquals( "HostErrors", pHostErrorsExpected, pActual.getHostErrors() );
    pFieldErrorsExpected.validate( pActual.getFieldErrors() );

    assertFormatAndToString( pActual, pHostExpected, pHostIdExpected, pFormatAndToStringSuffixLinesExpected );
    return pActual;
  }

  private void assertFormatAndToString( Validation pActual, Class<?> pHostExpected, Object pHostIdExpected,
                                        String[] pFormatAndToStringSuffixLinesExpected ) {
    String zFormatActual = pActual.format( pHostExpected, pHostIdExpected );
    String zToStringActual = pActual.toString();

    String zToStringExpected = Validation.NO_ERRORS;
    String zFormatExpected = Validation.NO_ERRORS;
    if ( pFormatAndToStringSuffixLinesExpected != null ) {
      String zErrorLines = format( pFormatAndToStringSuffixLinesExpected );
      zToStringExpected = "Errors found" + zErrorLines;
      zFormatExpected = "Errors with " + pHostExpected.getSimpleName();
      if ( pHostIdExpected != null ) {
        zFormatExpected += "(" + pHostIdExpected + ")";
      }
      zFormatExpected += zErrorLines;
    }
    Assert.assertEquals( "ToString", zToStringExpected, zToStringActual );
    Assert.assertEquals( "Format", zFormatExpected, zFormatActual );
  }

  private String format( String[] pErrorLines ) {
    StringBuilder sb = new StringBuilder( pErrorLines[0] );
    for ( int i = 1; i < pErrorLines.length; i++ ) {
      sb.append( '\n' ).append( pErrorLines[i] );
    }
    return sb.toString();
  }
}
