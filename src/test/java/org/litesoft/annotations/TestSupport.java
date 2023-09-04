package org.litesoft.annotations;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.DoubleAccumulator;
import java.util.concurrent.atomic.DoubleAdder;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

import org.litesoft.annotations.expectations.Expectation;
import org.litesoft.annotations.support.Assert_rNumber;
import org.litesoft.annotations.support.Assert_rNumberWithExpectation;
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
import org.litesoft.annotations.support.Validate_rNumber;
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

    protected <T> void check_ValidateNumber( Validate_rNumber validator, List<CheckParams<T>> params ) {
        for ( CheckParams<T> entry : params ) {
            Exp expectation = new Exp();
            boolean result = valueValidator( validator, entry, expectation );
            assertEquals( entry.expectedResult, result, entry.contextMsg );
            checkExpectation( result, expectation.params, entry.contextMsg, entry.toCheck );
        }
    }

    @SuppressWarnings("EnhancedSwitchMigration")
    private static <T> boolean valueValidator( Validate_rNumber rNumber, CheckParams<T> entry, Exp expectation ) {
        String simpleName = entry.toCheck.getClass().getSimpleName();
        switch ( simpleName ) {
            case "Byte": return rNumber.value( entry.contextMsg, (Byte)entry.toCheck, expectation );
            case "Short": return rNumber.value( entry.contextMsg, (Short)entry.toCheck, expectation );
            case "Integer": return rNumber.value( entry.contextMsg, (Integer)entry.toCheck, expectation );
            case "Long": return rNumber.value( entry.contextMsg, (Long) entry.toCheck, expectation );
            case "Float": return rNumber.value( entry.contextMsg, (Float) entry.toCheck, expectation );
            case "Double": return rNumber.value( entry.contextMsg, (Double) entry.toCheck, expectation );
            case "AtomicInteger": return rNumber.value( entry.contextMsg, (AtomicInteger) entry.toCheck, expectation );
            case "AtomicLong": return rNumber.value( entry.contextMsg, (AtomicLong) entry.toCheck, expectation );
            case "DoubleAccumulator": return rNumber.value( entry.contextMsg, (DoubleAccumulator) entry.toCheck, expectation );
            case "DoubleAdder": return rNumber.value( entry.contextMsg, (DoubleAdder) entry.toCheck, expectation );
            case "LongAccumulator": return rNumber.value( entry.contextMsg, (LongAccumulator) entry.toCheck, expectation );
            case "LongAdder": return rNumber.value( entry.contextMsg, (LongAdder) entry.toCheck, expectation );
            case "BigInteger": return rNumber.value( entry.contextMsg, (BigInteger) entry.toCheck, expectation );
            case "BigDecimal": return rNumber.value( entry.contextMsg, (BigDecimal) entry.toCheck, expectation );
            default: throw new Error( "Missed an entry for: " + simpleName );
        }
    }

    protected <T> void check_AssertNumber( Assert_rNumber asserter, List<CheckParams<T>> params ) {
        for ( CheckParams<T> entry : params ) {
            Exp expectation = new Exp();
            Object result = valueAssert( asserter, entry, expectation );
            assertEquals( entry.toCheck, result, entry.contextMsg );
            checkExpectation( entry.expectedResult, expectation.params, entry.contextMsg, entry.toCheck );
        }
    }

    @SuppressWarnings("EnhancedSwitchMigration")
    private static <T> Object valueAssert( Assert_rNumber rNumber, CheckParams<T> entry, Exp expectation ) {
        String simpleName = entry.toCheck.getClass().getSimpleName();
        switch ( simpleName ) {
            case "Byte": return rNumber.namedValueExpectation( entry.contextMsg, (Byte)entry.toCheck, expectation );
            case "Short": return rNumber.namedValueExpectation( entry.contextMsg, (Short)entry.toCheck, expectation );
            case "Integer": return rNumber.namedValueExpectation( entry.contextMsg, (Integer)entry.toCheck, expectation );
            case "Long": return rNumber.namedValueExpectation( entry.contextMsg, (Long) entry.toCheck, expectation );
            case "Float": return rNumber.namedValueExpectation( entry.contextMsg, (Float) entry.toCheck, expectation );
            case "Double": return rNumber.namedValueExpectation( entry.contextMsg, (Double) entry.toCheck, expectation );
            case "AtomicInteger": return rNumber.namedValueExpectation( entry.contextMsg, (AtomicInteger) entry.toCheck, expectation );
            case "AtomicLong": return rNumber.namedValueExpectation( entry.contextMsg, (AtomicLong) entry.toCheck, expectation );
            case "DoubleAccumulator": return rNumber.namedValueExpectation( entry.contextMsg, (DoubleAccumulator) entry.toCheck, expectation );
            case "DoubleAdder": return rNumber.namedValueExpectation( entry.contextMsg, (DoubleAdder) entry.toCheck, expectation );
            case "LongAccumulator": return rNumber.namedValueExpectation( entry.contextMsg, (LongAccumulator) entry.toCheck, expectation );
            case "LongAdder": return rNumber.namedValueExpectation( entry.contextMsg, (LongAdder) entry.toCheck, expectation );
            case "BigInteger": return rNumber.namedValueExpectation( entry.contextMsg, (BigInteger) entry.toCheck, expectation );
            case "BigDecimal": return rNumber.namedValueExpectation( entry.contextMsg, (BigDecimal) entry.toCheck, expectation );
            default: throw new Error( "Missed an entry for: " + simpleName );
        }
    }

    protected <T> void check_AssertNumber( Assert_rNumberWithExpectation asserter, Class<?> pExpectedThrowableClass, List<CheckParams<T>> params ) {
        new _Asserter_Number_CheckParams(asserter, pExpectedThrowableClass).checkAll( params );
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

    protected static class _Asserter_Number_CheckParams extends Abstract_Asserter {
        private final Assert_rNumberWithExpectation asserter;

        public _Asserter_Number_CheckParams( Assert_rNumberWithExpectation pAsserter, Class<?> pExpectedThrowableClass ) {
            super( pExpectedThrowableClass );
            asserter = pAsserter;
        }

        public <T> void checkAll( List<CheckParams<T>> OurParams ) {
            for ( CheckParams<T> params : OurParams ) {
                check( params );
            }
        }

        void check( CheckParams<?> params ) {
            Object actual;
            try {
                actual = valueAssert( asserter, params );
            }
            catch ( Throwable e ) {
                checkT( params.expectedResult, e, params.contextMsg );
                return;
            }
            assertEquals( params.toCheck, actual, params.contextMsg );
        }

        @SuppressWarnings("EnhancedSwitchMigration")
        private static <T> Object valueAssert( Assert_rNumberWithExpectation rNumber, CheckParams<T> entry ) {
            String simpleName = entry.toCheck.getClass().getSimpleName();
            switch ( simpleName ) {
                case "Byte": return rNumber.namedValue( entry.contextMsg, (Byte)entry.toCheck );
                case "Short": return rNumber.namedValue( entry.contextMsg, (Short)entry.toCheck );
                case "Integer": return rNumber.namedValue( entry.contextMsg, (Integer)entry.toCheck );
                case "Long": return rNumber.namedValue( entry.contextMsg, (Long) entry.toCheck );
                case "Float": return rNumber.namedValue( entry.contextMsg, (Float) entry.toCheck );
                case "Double": return rNumber.namedValue( entry.contextMsg, (Double) entry.toCheck );
                case "AtomicInteger": return rNumber.namedValue( entry.contextMsg, (AtomicInteger) entry.toCheck );
                case "AtomicLong": return rNumber.namedValue( entry.contextMsg, (AtomicLong) entry.toCheck );
                case "DoubleAccumulator": return rNumber.namedValue( entry.contextMsg, (DoubleAccumulator) entry.toCheck );
                case "DoubleAdder": return rNumber.namedValue( entry.contextMsg, (DoubleAdder) entry.toCheck );
                case "LongAccumulator": return rNumber.namedValue( entry.contextMsg, (LongAccumulator) entry.toCheck );
                case "LongAdder": return rNumber.namedValue( entry.contextMsg, (LongAdder) entry.toCheck );
                case "BigInteger": return rNumber.namedValue( entry.contextMsg, (BigInteger) entry.toCheck );
                case "BigDecimal": return rNumber.namedValue( entry.contextMsg, (BigDecimal) entry.toCheck );
                default: throw new Error( "Missed an entry for: " + simpleName );
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
