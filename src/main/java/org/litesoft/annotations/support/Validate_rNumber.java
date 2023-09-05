package org.litesoft.annotations.support;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.DoubleAccumulator;
import java.util.concurrent.atomic.DoubleAdder;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Supplier;

import org.litesoft.annotations.expectations.Expectation;

/**
 * <code>Number</code> "checker" that handles the built-in <code>Number</code>s.
 * <p>
 * Check the parameter against an abstract comparator to determine if it is acceptable (<code>true</code>).
 * <p>
 * <code>null</code>s or <code>NaN</code>s are never acceptable!
 */
public class Validate_rNumber extends UnmetCheck {
    private final Check_rNumber mChecker;

    public Validate_rNumber( String pExpectationString, Check_rNumber pChecker ) {
        super( pExpectationString );
        mChecker = assertNotNull( pChecker );
    }

    public boolean value( String pName, Byte pToCheck, Expectation pExpectation ) {
        return value( () -> pName, pToCheck, pExpectation );
    }

    public boolean value( String pName, Short pToCheck, Expectation pExpectation ) {
        return value( () -> pName, pToCheck, pExpectation );
    }

    public boolean value( String pName, Integer pToCheck, Expectation pExpectation ) {
        return value( () -> pName, pToCheck, pExpectation );
    }

    public boolean value( String pName, Long pToCheck, Expectation pExpectation ) {
        return value( () -> pName, pToCheck, pExpectation );
    }

    public boolean value( String pName, Float pToCheck, Expectation pExpectation ) {
        return value( () -> pName, pToCheck, pExpectation );
    }

    public boolean value( String pName, Double pToCheck, Expectation pExpectation ) {
        return value( () -> pName, pToCheck, pExpectation );
    }

    public boolean value( String pName, AtomicInteger pToCheck, Expectation pExpectation ) {
        return value( () -> pName, pToCheck, pExpectation );
    }

    public boolean value( String pName, AtomicLong pToCheck, Expectation pExpectation ) {
        return value( () -> pName, pToCheck, pExpectation );
    }

    public boolean value( String pName, DoubleAccumulator pToCheck, Expectation pExpectation ) {
        return value( () -> pName, pToCheck, pExpectation );
    }

    public boolean value( String pName, DoubleAdder pToCheck, Expectation pExpectation ) {
        return value( () -> pName, pToCheck, pExpectation );
    }

    public boolean value( String pName, LongAccumulator pToCheck, Expectation pExpectation ) {
        return value( () -> pName, pToCheck, pExpectation );
    }

    public boolean value( String pName, LongAdder pToCheck, Expectation pExpectation ) {
        return value( () -> pName, pToCheck, pExpectation );
    }

    public boolean value( String pName, byte pToCheck, Expectation pExpectation ) {
        return value( () -> pName, pToCheck, pExpectation );
    }

    public boolean value( String pName, short pToCheck, Expectation pExpectation ) {
        return value( () -> pName, pToCheck, pExpectation );
    }

    public boolean value( String pName, int pToCheck, Expectation pExpectation ) {
        return value( () -> pName, pToCheck, pExpectation );
    }

    public boolean value( String pName, long pToCheck, Expectation pExpectation ) {
        return value( () -> pName, pToCheck, pExpectation );
    }

    public boolean value( String pName, float pToCheck, Expectation pExpectation ) {
        return value( () -> pName, pToCheck, pExpectation );
    }

    public boolean value( String pName, double pToCheck, Expectation pExpectation ) {
        return value( () -> pName, pToCheck, pExpectation );
    }

    public boolean value( String pName, BigInteger pToCheck, Expectation pExpectation ) {
        return value( () -> pName, pToCheck, pExpectation );
    }

    public boolean value( String pName, BigDecimal pToCheck, Expectation pExpectation ) {
        return value( () -> pName, pToCheck, pExpectation );
    }

    public boolean value( Supplier<String> pContext, Byte pToCheck, Expectation pExpectation ) {
        return acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
    }

    public boolean value( Supplier<String> pContext, Short pToCheck, Expectation pExpectation ) {
        return acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
    }

    public boolean value( Supplier<String> pContext, Integer pToCheck, Expectation pExpectation ) {
        return acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
    }

    public boolean value( Supplier<String> pContext, Long pToCheck, Expectation pExpectation ) {
        return acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
    }

    public boolean value( Supplier<String> pContext, Float pToCheck, Expectation pExpectation ) {
        return acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
    }

    public boolean value( Supplier<String> pContext, Double pToCheck, Expectation pExpectation ) {
        return acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
    }

    public boolean value( Supplier<String> pContext, AtomicInteger pToCheck, Expectation pExpectation ) {
        return acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
    }

    public boolean value( Supplier<String> pContext, AtomicLong pToCheck, Expectation pExpectation ) {
        return acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
    }

    public boolean value( Supplier<String> pContext, DoubleAccumulator pToCheck, Expectation pExpectation ) {
        return acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
    }

    public boolean value( Supplier<String> pContext, DoubleAdder pToCheck, Expectation pExpectation ) {
        return acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
    }

    public boolean value( Supplier<String> pContext, LongAccumulator pToCheck, Expectation pExpectation ) {
        return acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
    }

    public boolean value( Supplier<String> pContext, LongAdder pToCheck, Expectation pExpectation ) {
        return acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
    }

    public boolean value( Supplier<String> pContext, byte pToCheck, Expectation pExpectation ) {
        return acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
    }

    public boolean value( Supplier<String> pContext, short pToCheck, Expectation pExpectation ) {
        return acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
    }

    public boolean value( Supplier<String> pContext, int pToCheck, Expectation pExpectation ) {
        return acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
    }

    public boolean value( Supplier<String> pContext, long pToCheck, Expectation pExpectation ) {
        return acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
    }

    public boolean value( Supplier<String> pContext, float pToCheck, Expectation pExpectation ) {
        return acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
    }

    public boolean value( Supplier<String> pContext, double pToCheck, Expectation pExpectation ) {
        return acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
    }

    public boolean value( Supplier<String> pContext, BigInteger pToCheck, Expectation pExpectation ) {
        return acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
    }

    public boolean value( Supplier<String> pContext, BigDecimal pToCheck, Expectation pExpectation ) {
        return acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
    }
}
