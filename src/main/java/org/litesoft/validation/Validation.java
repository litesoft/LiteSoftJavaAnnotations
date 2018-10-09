package org.litesoft.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.litesoft.annotations.DefaultingOnInsignificant;
import org.litesoft.annotations.NotNull;
import org.litesoft.annotations.Nullable;
import org.litesoft.annotations.Significant;
import org.litesoft.annotations.SignificantOrNull;
import org.litesoft.annotations.Verified;
import org.litesoft.annotations.expectations.Expectation;

@SuppressWarnings("unused")
public interface Validation {
  String NO_ERRORS = "No Errors";
  String INDENT_PADDING = "    ";

  boolean hasErrors();

  @NotNull
  List<String> getHostErrors();

  @NotNull
  Map<String, List<String>> getFieldErrors();

  /**
   * Add a 'Host' Error (if the error is Significant).
   *
   * @param pError optional Error
   *
   * @return possibly new instance
   */
  @NotNull
  Validation addHostError( @SignificantOrNull String pError );

  /**
   * Add a 'Field' Error (if the error is Significant).
   *
   * @param pFieldRef Expected Significant, but will NOT error (defaulting) if insignificant
   * @param pError    optional Error
   *
   * @return possibly new instance
   */
  @NotNull
  Validation addFieldError( @DefaultingOnInsignificant String pFieldRef, @SignificantOrNull String pError );

  @Significant
  String format( @NotNull Class<?> pHost, @Nullable Object pHostId );

  Validation OK = new Validation() {
    @Override
    public boolean hasErrors() {
      return false;
    }

    @Override
    public List<String> getHostErrors() {
      return Collections.emptyList();
    }

    @Override
    public Map<String, List<String>> getFieldErrors() {
      return Collections.emptyMap();
    }

    @Override
    public Validation addHostError( String pError ) {
      pError = Significant.ConstrainTo.valueOrNull( pError );
      return (pError == null) ? this :
             addHostErrorTo( getHostErrors(), getFieldErrors(), pError );
    }

    @Override
    public Validation addFieldError( String pFieldRef, String pError ) {
      pError = Significant.ConstrainTo.valueOrNull( pError );
      return (pError == null) ? this :
             addFieldErrorTo( getHostErrors(), getFieldErrors(), pFieldRef, pError );
    }

    @Override
    public String format( Class<?> pHost, Object pHostId ) {
      return toString();
    }

    @Override
    public String toString() {
      return NO_ERRORS;
    }

    class WithErrors implements Validation {
      private final List<String> mHostErrors;
      private final Map<String, List<String>> mErrorsByFieldRef;

      private WithErrors( List<String> pHostErrors, Map<String, List<String>> pErrorsByFieldRef ) {
        mHostErrors = pHostErrors;
        mErrorsByFieldRef = pErrorsByFieldRef;
      }

      @Override
      public Validation addHostError( String pError ) {
        pError = Significant.ConstrainTo.valueOrNull( pError );
        return (pError == null) ? this :
               addHostErrorTo( mHostErrors, mErrorsByFieldRef, pError );
      }

      @Override
      public Validation addFieldError( String pFieldRef, String pError ) {
        pError = Significant.ConstrainTo.valueOrNull( pError );
        return (pError == null) ? this :
               addFieldErrorTo( mHostErrors, mErrorsByFieldRef, pFieldRef, pError );
      }

      @Override
      public boolean hasErrors() {
        return true;
      }

      @Override
      public List<String> getHostErrors() {
        return Collections.unmodifiableList( mHostErrors );
      }

      @Override
      public Map<String, List<String>> getFieldErrors() {
        return Collections.unmodifiableMap( mErrorsByFieldRef );
      }

      @Override
      public String format( Class<?> pHost, Object pHostId ) {
        String zHostRef = NotNull.AssertArgument.namedValue( "Host", pHost ).getSimpleName();
        if ( pHostId != null ) {
          String zStringHostId = Significant.ConstrainTo.valueOrNull( pHostId.toString() );
          if ( zStringHostId != null ) {
            zHostRef += '(' + zStringHostId + ')';
          }
        }
        return format( "Errors with " + zHostRef );
      }

      @Override
      public String toString() {
        return format( "Errors found" );
      }

      private String format( String pHostLeadin ) {
        StringBuilder sb = new StringBuilder().append( pHostLeadin ).append( ':' );
        addHostErrors( sb );
        addFieldErrors( sb );
        return sb.toString();
      }

      private void addHostErrors( StringBuilder pSB ) {
        if ( !mHostErrors.isEmpty() ) {
          addErrors( pSB, mHostErrors, 1, "Host Errors" );
        }
      }

      private void addFieldErrors( StringBuilder pSB ) {
        if ( !mErrorsByFieldRef.isEmpty() ) {
          newLineIndented( pSB, 1 ).append( "Field Errors:" );
          ArrayList<String> zFieldRefs = new ArrayList<String>( mErrorsByFieldRef.keySet() );
          Collections.sort( zFieldRefs );
          for ( String zFieldRef : zFieldRefs ) {
            addErrors( pSB, mErrorsByFieldRef.get( zFieldRef ), 2, zFieldRef );
          }
        }
      }

      private StringBuilder newLineIndented( StringBuilder pSB, int pIndents ) {
        pSB.append( '\n' ).append( INDENT_PADDING );
        while ( --pIndents > 0 ) {
          pSB.append( INDENT_PADDING );
        }
        return pSB;
      }

      private void addErrors( StringBuilder pSB, List<String> pErrors, int pIndents, String pLabel ) {
        newLineIndented( pSB, pIndents++ ).append( pLabel ).append( ':' );
        for ( String zError : pErrors ) {
          newLineIndented( pSB, pIndents++ ).append( zError );
        }
      }
    }

    private Validation addHostErrorTo( @NotNull @Verified List<String> pExistingHostErrors,
                                       @NotNull @Verified Map<String, List<String>> pExistingFieldErrors,
                                       @Significant @Verified String pError ) {

      List<String> zErrors = new ArrayList<String>( pExistingHostErrors );
      zErrors.add( pError );
      return new WithErrors( zErrors, pExistingFieldErrors );
    }

    private Validation addFieldErrorTo( @NotNull @Verified List<String> pExistingHostErrors,
                                        @NotNull @Verified Map<String, List<String>> pExistingFieldErrors,
                                        @DefaultingOnInsignificant String pFieldRef,
                                        @Significant @Verified String pError ) {

      pFieldRef = Significant.ConstrainTo.valueOr( pFieldRef, Expectation.DEFAULT_ON_INSIGNIFICANT );
      Map<String, List<String>> zExistingFieldErrors = new HashMap<String, List<String>>( pExistingFieldErrors );
      List<String> zErrors = zExistingFieldErrors.get( pFieldRef );
      if ( zErrors == null ) {
        zExistingFieldErrors.put( pFieldRef, zErrors = new ArrayList<String>() );
      }
      zErrors.add( pError );
      return new WithErrors( pExistingHostErrors, zExistingFieldErrors );
    }
  };
}
