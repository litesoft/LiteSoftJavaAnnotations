package org.litesoft.annotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.litesoft.annotations.expectations.Expectation;
import org.litesoft.annotations.support.Assert_rTyped;
import org.litesoft.annotations.support.Assert_rTypedWithCollection;
import org.litesoft.annotations.support.Assert_rTypedWithExpectationWithCollection;
import org.litesoft.annotations.support.Assert_rTypedWithNormalizer;
import org.litesoft.annotations.support.Assert_rUntyped;
import org.litesoft.annotations.support.Assert_rUntypedWithExpectation;
import org.litesoft.annotations.support.Assert_rWithExpectation;
import org.litesoft.annotations.support.Check_r;
import org.litesoft.annotations.support.Check_rWithCollection;
import org.litesoft.annotations.support.CollectionAssert_r;
import org.litesoft.annotations.support.CollectionAssert_rWithExpectation;
import org.litesoft.annotations.support.CollectionCheck_r;
import org.litesoft.annotations.support.CollectionValidate_r;
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

    protected <T> void check_Check( Check_r<T> checker, List<? extends CheckParams<T>> params ) {
        for ( CheckParams<T> entry : params ) {
            boolean result = checker.value( entry.toCheck );
            assertEquals( entry.expectedResult, result, entry.contextMsg );
        }
    }

    protected <T> void check_CheckList( Check_rWithCollection<T> checker, List<? extends CheckParamsList<T>> params ) {
        for ( CheckParamsList<T> entry : params ) {
            boolean result = checker.value( entry.toCheck );
            assertEquals( entry.expectedResult, result, entry.contextMsg );
        }
    }

    protected <T> void check_CheckList( CollectionCheck_r checker, List<? extends CheckParamsList<T>> params ) {
        for ( CheckParamsList<T> entry : params ) {
            boolean result = checker.value( entry.toCheck );
            assertEquals( entry.expectedResult, result, entry.contextMsg );
        }
    }

    protected <T> void check_Validate( Validate_r<T> validator, List<? extends CheckParams<T>> params ) {
        for ( CheckParams<T> entry : params ) {
            Exp expectation = new Exp();
            boolean result = validator.value( entry.contextMsg, entry.toCheck, expectation );
            assertEquals( entry.expectedResult, result, entry.contextMsg );
            checkExpectation( result, expectation.params, entry.contextMsg, entry.toCheck );
        }
    }

    protected <T> void check_ValidateList( Validate_rWithCollection<T> validator, List<? extends CheckParamsList<T>> params ) {
        for ( CheckParamsList<T> entry : params ) {
            Exp expectation = new Exp();
            boolean result = validator.value( entry.contextMsg, entry.toCheck, expectation );
            assertEquals( entry.expectedResult, result, entry.contextMsg );
            checkExpectation( result, expectation.params, entry.contextMsg, entry.toCheck );
        }
    }

    protected <T> void check_ValidateList( CollectionValidate_r validator, List<CheckParamsList<T>> params ) {
        for ( CheckParamsList<T> entry : params ) {
            Exp expectation = new Exp();
            boolean result = validator.value( entry.contextMsg, entry.toCheck, expectation );
            assertEquals( entry.expectedResult, result, entry.contextMsg );
            checkExpectation( result, expectation.params, entry.contextMsg, entry.toCheck );
        }
    }

    protected <T> void check_Assert( Assert_rUntyped asserter, List<? extends CheckParams<T>> params ) {
        for ( CheckParams<T> entry : params ) {
            Exp expectation = new Exp();
            T result = asserter.namedValueExpectation( entry.contextMsg, entry.toCheck, expectation );
            assertEquals( entry.toCheck, result, entry.contextMsg );
            checkExpectation( entry.expectedResult, expectation.params, entry.contextMsg, entry.toCheck );
        }
    }

    protected <T> void check_Assert( Assert_rTyped<T> asserter, List<? extends CheckParams<T>> params ) {
        for ( CheckParams<T> entry : params ) {
            Exp expectation = new Exp();
            T result = asserter.namedValueExpectation( entry.contextMsg, entry.toCheck, expectation );
            assertEquals( entry.toCheck, result, entry.contextMsg );
            checkExpectation( entry.expectedResult, expectation.params, entry.contextMsg, entry.toCheck );
        }
    }

    protected <T> void check_AssertList( Assert_rTypedWithCollection<T> asserter, List<? extends CheckParamsList<T>> params ) {
        for ( CheckParamsList<T> entry : params ) {
            Exp expectation = new Exp();
            List<?> result = asserter.namedValueExpectation( entry.contextMsg, entry.toCheck, expectation );
            assertEquals( entry.toCheck, result, entry.contextMsg );
            checkExpectation( entry.expectedResult, expectation.params, entry.contextMsg, entry.toCheck );
        }
    }

    protected <T> void check_AssertList( CollectionAssert_r asserter, List<CheckParamsList<T>> params ) {
        for ( CheckParamsList<T> entry : params ) {
            Exp expectation = new Exp();
            List<T> result = asserter.namedValueExpectation( entry.contextMsg, entry.toCheck, expectation );
            assertEquals( entry.toCheck, result, entry.contextMsg );
            checkExpectation( entry.expectedResult, expectation.params, entry.contextMsg, entry.toCheck );
        }
    }

    protected <T> void check_Assert( Assert_rTypedWithNormalizer<T> asserter, List<CheckParamsWithMorphedOutput<T>> params ) {
        for ( CheckParamsWithMorphedOutput<T> entry : params ) {
            Exp expectation = new Exp();
            T result = asserter.namedValueExpectation( entry.contextMsg, entry.toCheck, expectation );
            assertEquals( entry.morphedOutput, result, entry.contextMsg );
            checkExpectation( entry.expectedResult, expectation.params, entry.contextMsg, entry.toCheck );
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
        public final boolean expectedResult;
        public final String contextMsg;

        public AbstractParams( boolean pExpectedResult, String pContextMsg ) {
            expectedResult = pExpectedResult;
            contextMsg = pContextMsg;
        }
    }

    public static class CheckParams<T> extends AbstractParams {
        public final T toCheck;

        public CheckParams( boolean pExpectedResult, String pContextMsg, T pToCheck ) {
            super( pExpectedResult, pContextMsg );
            toCheck = pToCheck;
        }
    }

    public static class CheckParamsList<T> extends AbstractParams {
        public final List<T> toCheck;

        public CheckParamsList( boolean pExpectedResult, String pContextMsg, List<T> pToCheck ) {
            super( pExpectedResult, pContextMsg );
            toCheck = pToCheck;
        }
    }

    public static class CheckParamsWithMorphedOutput<T> extends CheckParams<T> {
        public final T morphedOutput;

        public CheckParamsWithMorphedOutput( boolean pExpectedResult, String pContextMsg, T pToCheck, T pMorphedOutput ) {
            super( pExpectedResult, pContextMsg, pToCheck );
            morphedOutput = pMorphedOutput;
        }
    }

    protected static abstract class Abstract_Asserter {
        private final Class<?> expectedThrowableClass;

        protected Abstract_Asserter( Class<?> pExpectedThrowableClass ) {
            expectedThrowableClass = pExpectedThrowableClass;
        }

        protected void checkT( boolean expected, Throwable t, String contextMsg ) {
            if ( expected ) {
                assertNull( t, contextMsg );
            } else {
                assertInstanceOf( expectedThrowableClass, t, contextMsg );
            }
        }
    }

    protected static class _Asserter_Untyped_CheckParams extends Abstract_Asserter {
        private final Assert_rUntypedWithExpectation asserter;

        public _Asserter_Untyped_CheckParams( Assert_rUntypedWithExpectation pAsserter, Class<?> pExpectedThrowableClass ) {
            super( pExpectedThrowableClass );
            asserter = pAsserter;
        }

        public void checkAll( List<CheckParams<Object>> OurParams ) {
            for ( CheckParams<Object> params : OurParams ) {
                check( params );
            }
        }

        void check( CheckParams<Object> params ) {
            Object actual;
            try {
                actual = asserter.namedValue( params.contextMsg, params.toCheck );
            }
            catch ( Throwable e ) {
                checkT( params.expectedResult, e, params.contextMsg );
                return;
            }
            assertEquals( params.toCheck, actual, params.contextMsg );
        }
    }

    protected static class _Asserter_Typed_CheckParamsList<T> extends Abstract_Asserter {
        private final Assert_rTypedWithExpectationWithCollection<T> asserter;

        public _Asserter_Typed_CheckParamsList( Assert_rTypedWithExpectationWithCollection<T> pAsserter, Class<?> pExpectedThrowableClass ) {
            super( pExpectedThrowableClass );
            asserter = pAsserter;
        }

        public void checkAll( List<CheckParamsList<T>> OurParams ) {
            for ( CheckParamsList<T> params : OurParams ) {
                check( params );
            }
        }

        void check( CheckParamsList<T> params ) {
            List<T> actual;
            try {
                actual = asserter.namedValue( params.contextMsg, params.toCheck );
            }
            catch ( Throwable e ) {
                checkT( params.expectedResult, e, params.contextMsg );
                return;
            }
            assertEquals( params.toCheck, actual, params.contextMsg );
        }
    }

    protected static class _Asserter_Collections_CheckParams extends Abstract_Asserter {
        private final CollectionAssert_rWithExpectation asserter;

        public _Asserter_Collections_CheckParams( CollectionAssert_rWithExpectation pAsserter, Class<?> pExpectedThrowableClass ) {
            super( pExpectedThrowableClass );
            asserter = pAsserter;
        }

        public <T> void checkAll( List<CheckParamsList<T>> OurParams ) {
            for ( CheckParamsList<?> params : OurParams ) {
                check( params );
            }
        }

        void check( CheckParamsList<?> params ) {
            List<?> actual;
            try {
                actual = asserter.namedValue( params.contextMsg, params.toCheck );
            }
            catch ( Throwable e ) {
                checkT( params.expectedResult, e, params.contextMsg );
                return;
            }
            assertEquals( params.toCheck, actual, params.contextMsg );
        }
    }

    protected static class _Asserter_Typed_CheckParams<T> extends Abstract_Asserter {
        private final Assert_rWithExpectation<T> asserter;

        public _Asserter_Typed_CheckParams( Assert_rWithExpectation<T> pAsserter, Class<?> pExpectedThrowableClass ) {
            super( pExpectedThrowableClass );
            asserter = pAsserter;
        }

        public void checkAll( List<CheckParams<T>> OurParams ) {
            for ( CheckParams<T> params : OurParams ) {
                check( params );
            }
        }

        void check( CheckParams<T> params ) {
            T actual;
            try {
                actual = asserter.namedValue( params.contextMsg, params.toCheck );
            }
            catch ( Throwable e ) {
                checkT( params.expectedResult, e, params.contextMsg );
                return;
            }
            assertEquals( params.toCheck, actual, params.contextMsg );
        }
    }

    protected static class _Asserter_Typed_CheckParamsWithMorphedOutput<T> extends Abstract_Asserter {
        private final Assert_rWithExpectation<T> asserter;

        public _Asserter_Typed_CheckParamsWithMorphedOutput( Assert_rWithExpectation<T> pAsserter, Class<?> pExpectedThrowableClass ) {
            super( pExpectedThrowableClass );
            asserter = pAsserter;
        }

        public void checkAll( List<CheckParamsWithMorphedOutput<T>> OurParams ) {
            for ( CheckParamsWithMorphedOutput<T> params : OurParams ) {
                check( params );
            }
        }

        void check( CheckParamsWithMorphedOutput<T> params ) {
            T actual;
            try {
                actual = asserter.namedValue( params.contextMsg, params.toCheck );
            }
            catch ( Throwable e ) {
                checkT( params.expectedResult, e, params.contextMsg );
                return;
            }
            assertEquals( params.morphedOutput, actual, params.contextMsg );
        }
    }
}
