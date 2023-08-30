package org.litesoft.annotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.litesoft.annotations.expectations.Expectation;
import org.litesoft.annotations.support.Assert_rTyped;
import org.litesoft.annotations.support.Assert_rTypedWithCollection;
import org.litesoft.annotations.support.Assert_rTypedWithNormalizer;
import org.litesoft.annotations.support.Check_r;
import org.litesoft.annotations.support.Check_rWithCollection;
import org.litesoft.annotations.support.Validate_r;
import org.litesoft.annotations.support.Validate_rWithCollection;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("SameParameterValue")
public abstract class TestSupport {
    abstract protected String getExpectation();

    protected static class ListOf<T> extends ArrayList<T> {
        private Class<?> klass;

        public ListOf( Class<T> klass ) {
            this.klass = klass;
        }

        public ListOf() {
            this( null );
        }

        public ListOf<T> with( T entry ) {
            if ( entry != null ) {
                Class<?> entryClass = entry.getClass();
                if ( klass == null ) {
                    klass = entryClass;
                } else {
                    assertSame( klass, entryClass );
                }
            }
            add( entry );
            return this;
        }
    }

    protected <T> void check_Check( Check_r<T> checker, List<? extends CheckParams<T>> params) {
        for ( CheckParams<T> entry : params ) {
            boolean result = checker.value( entry.toCheck );
            assertEquals( entry.expectedProblem, result, entry.contextMsg );
        }
    }

    protected <T> void check_CheckList( Check_rWithCollection<T> checker, List<? extends CheckParamsList<T>> params) {
        for ( CheckParamsList<T> entry : params ) {
            boolean result = checker.value( entry.toCheck );
            assertEquals( entry.expectedProblem, result, entry.contextMsg );
        }
    }

    protected <T> void check_Validate( Validate_r<T> validator, List<? extends CheckParams<T>> params) {
        for ( CheckParams<T> entry : params ) {
            Exp expectation = new Exp();
            boolean result = validator.value( entry.contextMsg, entry.toCheck, expectation );
            assertEquals( entry.expectedProblem, result, entry.contextMsg );
            checkExpectation( result, expectation.params, entry.contextMsg, entry.toCheck );
        }
    }

    protected <T> void check_ValidateList( Validate_rWithCollection<T> validator, List<? extends CheckParamsList<T>> params) {
        for ( CheckParamsList<T> entry : params ) {
            Exp expectation = new Exp();
            boolean result = validator.value( entry.contextMsg, entry.toCheck, expectation);
            assertEquals( entry.expectedProblem, result, entry.contextMsg );
            checkExpectation( result, expectation.params, entry.contextMsg, entry.toCheck );
        }
    }

    protected <T> void check_Assert( Assert_rTyped<T> asserter, List<? extends CheckParams<T>> params ) {
        for ( CheckParams<T> entry : params ) {
            Exp expectation = new Exp();
            T result = asserter.namedValueExpectation( entry.contextMsg, entry.toCheck, expectation );
            assertEquals( entry.toCheck, result, entry.contextMsg );
            checkExpectation( entry.expectedProblem, expectation.params, entry.contextMsg, entry.toCheck );
        }
    }

    protected <T> void check_AssertList( Assert_rTypedWithCollection<T> asserter, List<? extends CheckParamsList<T>> params ) {
        for ( CheckParamsList<T> entry : params ) {
            Exp expectation = new Exp();
            List<?> result = asserter.namedValueExpectation( entry.contextMsg, entry.toCheck, expectation );
            assertEquals( entry.toCheck, result, entry.contextMsg );
            checkExpectation( entry.expectedProblem, expectation.params, entry.contextMsg, entry.toCheck );
        }
    }

    protected <T> void check_Assert( Assert_rTypedWithNormalizer<T> asserter, List<CheckParamsWithResults<T>> params ) {
        for ( CheckParamsWithResults<T> entry : params ) {
            Exp expectation = new Exp();
            T result = asserter.namedValueExpectation( entry.contextMsg, entry.toCheck, expectation );
            assertEquals( entry.expectedResult, result, entry.contextMsg );
            checkExpectation( entry.expectedProblem, expectation.params, entry.contextMsg, entry.toCheck );
        }
    }

    public void checkExpectation( boolean expected, UnmetParams actual, String pContext, Object pToCheck ) {
        if ( expected ) {
            assertNull( actual );
        } else {
            assertEquals( new UnmetParams( pContext, pToCheck, getExpectation() ), actual );
        }
    }

    public static class UnmetParams {
        private final String mContext;
        private final Object mFailedValue;
        private final String mExpectation;

        public UnmetParams( String pContext, Object pFailedValue, String pExpectation ) {
            mContext = pContext;
            mFailedValue = pFailedValue;
            mExpectation = pExpectation;
        }

        @Override
        public String toString() {
            return "UnmetParams: " + mContext + " | " + mExpectation + " | " + mFailedValue;
        }

        @Override
        public boolean equals( Object o ) {
            if ( o == null || getClass() != o.getClass() ) {
                return false;
            }
            UnmetParams them = (UnmetParams)o;
            return (this == o) || (
                    Objects.equals( mContext, them.mContext ) &&
                    Objects.equals( mFailedValue, them.mFailedValue ) &&
                    Objects.equals( mExpectation, them.mExpectation ));
        }

        @Override
        public int hashCode() {
            return 0;
        }
    }

    public static class Exp implements Expectation {
        public UnmetParams params;

        @Override
        public void unmet( String pContext, Object pFailedValue, String pExpectation ) {
            params = new UnmetParams( pContext, pFailedValue, pExpectation );
        }

        @Override
        public void unmet( String pContext, String pMessageSuffix ) {
            throw new Error();
        }
    }

    public static class AbstractParams {
        public final boolean expectedProblem;
        public final String contextMsg;

        public AbstractParams( boolean pExpectedProblem, String pContextMsg ) {
            expectedProblem = pExpectedProblem;
            contextMsg = pContextMsg;
        }
    }

    public static class CheckParams<T> extends AbstractParams {
        public final T toCheck;

        public CheckParams( boolean pExpectedProblem, String pContextMsg, T pToCheck ) {
            super( pExpectedProblem, pContextMsg );
            toCheck = pToCheck;
        }
    }

    public static class CheckParamsList<T> extends AbstractParams {
        public final List<T> toCheck;

        public CheckParamsList( boolean pExpectedProblem, String pContextMsg, List<T> pToCheck ) {
            super( pExpectedProblem, pContextMsg );
            toCheck = pToCheck;
        }
    }

    public static class CheckParamsWithResults<T> extends CheckParams<T> {
        public final T expectedResult;

        public CheckParamsWithResults( boolean pExpectedProblem, String pContextMsg, T pToCheck, T pExpectedResult ) {
            super( pExpectedProblem, pContextMsg, pToCheck );
            expectedResult = pExpectedResult;
        }
    }
}
