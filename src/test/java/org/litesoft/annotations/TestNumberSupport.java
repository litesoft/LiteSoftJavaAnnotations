package org.litesoft.annotations;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.DoubleAccumulator;
import java.util.concurrent.atomic.DoubleAdder;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

import org.litesoft.annotations.support.Assert_rNumber;
import org.litesoft.annotations.support.Assert_rNumberWithExpectation;
import org.litesoft.annotations.support.Validate_rNumber;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("SameParameterValue")
public abstract class TestNumberSupport extends TestSupport {
    protected static DoubleAdder doubleAdder( int value ) {
        DoubleAdder zAdder = new DoubleAdder();
        zAdder.add( value );
        return zAdder;
    }

    protected static LongAdder longAdder( int value ) {
        LongAdder zAdder = new LongAdder();
        zAdder.add( value );
        return zAdder;
    }

    protected void check_ValidateNumber( Validate_rNumber validator, List<CheckParams<?>> params ) {
        for ( CheckParams<?> entry : params ) {
            Exp expectation = new Exp();
            boolean result = valueValidator( validator, entry, expectation );
            assertEquals( entry.expectedResult, result, entry.contextMsg );
            checkExpectation( result, expectation.params, entry.contextMsg, entry.toCheck );
        }
    }

    @SuppressWarnings("EnhancedSwitchMigration")
    private static boolean valueValidator( Validate_rNumber rNumber, CheckParams<?> entry, Exp expectation ) {
        String simpleName = entry.toCheck.getClass().getSimpleName();
        switch ( simpleName ) {
            case "Byte":
                return rNumber.value( entry.contextMsg, (Byte)entry.toCheck, expectation );
            case "Short":
                return rNumber.value( entry.contextMsg, (Short)entry.toCheck, expectation );
            case "Integer":
                return rNumber.value( entry.contextMsg, (Integer)entry.toCheck, expectation );
            case "Long":
                return rNumber.value( entry.contextMsg, (Long)entry.toCheck, expectation );
            case "Float":
                return rNumber.value( entry.contextMsg, (Float)entry.toCheck, expectation );
            case "Double":
                return rNumber.value( entry.contextMsg, (Double)entry.toCheck, expectation );
            case "AtomicInteger":
                return rNumber.value( entry.contextMsg, (AtomicInteger)entry.toCheck, expectation );
            case "AtomicLong":
                return rNumber.value( entry.contextMsg, (AtomicLong)entry.toCheck, expectation );
            case "DoubleAccumulator":
                return rNumber.value( entry.contextMsg, (DoubleAccumulator)entry.toCheck, expectation );
            case "DoubleAdder":
                return rNumber.value( entry.contextMsg, (DoubleAdder)entry.toCheck, expectation );
            case "LongAccumulator":
                return rNumber.value( entry.contextMsg, (LongAccumulator)entry.toCheck, expectation );
            case "LongAdder":
                return rNumber.value( entry.contextMsg, (LongAdder)entry.toCheck, expectation );
            case "BigInteger":
                return rNumber.value( entry.contextMsg, (BigInteger)entry.toCheck, expectation );
            case "BigDecimal":
                return rNumber.value( entry.contextMsg, (BigDecimal)entry.toCheck, expectation );
            default:
                throw new Error( "Missed an entry for: " + simpleName );
        }
    }

    protected void check_AssertNumber( Assert_rNumber asserter, List<CheckParams<?>> params ) {
        for ( CheckParams<?> entry : params ) {
            Exp expectation = new Exp();
            Object result = valueAssert( asserter, entry, expectation );
            assertEquals( entry.toCheck, result, entry.contextMsg );
            checkExpectation( entry.expectedResult, expectation.params, entry.contextMsg, entry.toCheck );
        }
    }

    @SuppressWarnings("EnhancedSwitchMigration")
    private static Object valueAssert( Assert_rNumber rNumber, CheckParams<?> entry, Exp expectation ) {
        String simpleName = entry.toCheck.getClass().getSimpleName();
        switch ( simpleName ) {
            case "Byte":
                return rNumber.namedValueExpectation( entry.contextMsg, (Byte)entry.toCheck, expectation );
            case "Short":
                return rNumber.namedValueExpectation( entry.contextMsg, (Short)entry.toCheck, expectation );
            case "Integer":
                return rNumber.namedValueExpectation( entry.contextMsg, (Integer)entry.toCheck, expectation );
            case "Long":
                return rNumber.namedValueExpectation( entry.contextMsg, (Long)entry.toCheck, expectation );
            case "Float":
                return rNumber.namedValueExpectation( entry.contextMsg, (Float)entry.toCheck, expectation );
            case "Double":
                return rNumber.namedValueExpectation( entry.contextMsg, (Double)entry.toCheck, expectation );
            case "AtomicInteger":
                return rNumber.namedValueExpectation( entry.contextMsg, (AtomicInteger)entry.toCheck, expectation );
            case "AtomicLong":
                return rNumber.namedValueExpectation( entry.contextMsg, (AtomicLong)entry.toCheck, expectation );
            case "DoubleAccumulator":
                return rNumber.namedValueExpectation( entry.contextMsg, (DoubleAccumulator)entry.toCheck, expectation );
            case "DoubleAdder":
                return rNumber.namedValueExpectation( entry.contextMsg, (DoubleAdder)entry.toCheck, expectation );
            case "LongAccumulator":
                return rNumber.namedValueExpectation( entry.contextMsg, (LongAccumulator)entry.toCheck, expectation );
            case "LongAdder":
                return rNumber.namedValueExpectation( entry.contextMsg, (LongAdder)entry.toCheck, expectation );
            case "BigInteger":
                return rNumber.namedValueExpectation( entry.contextMsg, (BigInteger)entry.toCheck, expectation );
            case "BigDecimal":
                return rNumber.namedValueExpectation( entry.contextMsg, (BigDecimal)entry.toCheck, expectation );
            default:
                throw new Error( "Missed an entry for: " + simpleName );
        }
    }

    public static class CheckNumberParams<T> extends CheckParams<T> {
        public final String simpleClassName;

        public CheckNumberParams( boolean pExpectedResult, String pContextMsg, T pToCheck ) {
            super( pExpectedResult, pToCheck.getClass().getSimpleName() + "(" + pContextMsg + ")", pToCheck );
            simpleClassName = pToCheck.getClass().getSimpleName();
        }
    }

    protected static class _Asserter_Number_CheckParams extends Abstract_Asserter {
        private final Assert_rNumberWithExpectation asserter;

        public _Asserter_Number_CheckParams( Assert_rNumberWithExpectation pAsserter, Class<?> pExpectedThrowableClass ) {
            super( pExpectedThrowableClass );
            asserter = pAsserter;
        }

        public void checkAll( List<CheckParams<?>> OurParams ) {
            for ( CheckParams<?> params : OurParams ) {
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
                case "Byte":
                    return rNumber.namedValue( entry.contextMsg, (Byte)entry.toCheck );
                case "Short":
                    return rNumber.namedValue( entry.contextMsg, (Short)entry.toCheck );
                case "Integer":
                    return rNumber.namedValue( entry.contextMsg, (Integer)entry.toCheck );
                case "Long":
                    return rNumber.namedValue( entry.contextMsg, (Long)entry.toCheck );
                case "Float":
                    return rNumber.namedValue( entry.contextMsg, (Float)entry.toCheck );
                case "Double":
                    return rNumber.namedValue( entry.contextMsg, (Double)entry.toCheck );
                case "AtomicInteger":
                    return rNumber.namedValue( entry.contextMsg, (AtomicInteger)entry.toCheck );
                case "AtomicLong":
                    return rNumber.namedValue( entry.contextMsg, (AtomicLong)entry.toCheck );
                case "DoubleAccumulator":
                    return rNumber.namedValue( entry.contextMsg, (DoubleAccumulator)entry.toCheck );
                case "DoubleAdder":
                    return rNumber.namedValue( entry.contextMsg, (DoubleAdder)entry.toCheck );
                case "LongAccumulator":
                    return rNumber.namedValue( entry.contextMsg, (LongAccumulator)entry.toCheck );
                case "LongAdder":
                    return rNumber.namedValue( entry.contextMsg, (LongAdder)entry.toCheck );
                case "BigInteger":
                    return rNumber.namedValue( entry.contextMsg, (BigInteger)entry.toCheck );
                case "BigDecimal":
                    return rNumber.namedValue( entry.contextMsg, (BigDecimal)entry.toCheck );
                default:
                    throw new Error( "Missed an entry for: " + simpleName );
            }
        }
    }
}
