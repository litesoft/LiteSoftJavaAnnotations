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
@SuppressWarnings("unused")
public class Assert_rNumber extends UnmetCheck {
    private final Check_rNumber mChecker;

    public Assert_rNumber( String pExpectationString, Check_rNumber pChecker ) {
        super( pExpectationString );
        mChecker = assertNotNull( pChecker );
    }

    public Byte namedValueExpectation( String pName, Byte pToCheck, Expectation pExpectation ) {
        return contextValueExpectation( () -> pName, pToCheck, pExpectation );
    }

    public Short namedValueExpectation( String pName, Short pToCheck, Expectation pExpectation ) {
        return contextValueExpectation( () -> pName, pToCheck, pExpectation );
    }

    public Integer namedValueExpectation( String pName, Integer pToCheck, Expectation pExpectation ) {
        return contextValueExpectation( () -> pName, pToCheck, pExpectation );
    }

    public Long namedValueExpectation( String pName, Long pToCheck, Expectation pExpectation ) {
        return contextValueExpectation( () -> pName, pToCheck, pExpectation );
    }

    public Float namedValueExpectation( String pName, Float pToCheck, Expectation pExpectation ) {
        return contextValueExpectation( () -> pName, pToCheck, pExpectation );
    }

    public Double namedValueExpectation( String pName, Double pToCheck, Expectation pExpectation ) {
        return contextValueExpectation( () -> pName, pToCheck, pExpectation );
    }

    public AtomicInteger namedValueExpectation( String pName, AtomicInteger pToCheck, Expectation pExpectation ) {
        return contextValueExpectation( () -> pName, pToCheck, pExpectation );
    }

    public AtomicLong namedValueExpectation( String pName, AtomicLong pToCheck, Expectation pExpectation ) {
        return contextValueExpectation( () -> pName, pToCheck, pExpectation );
    }

    public DoubleAccumulator namedValueExpectation( String pName, DoubleAccumulator pToCheck, Expectation pExpectation ) {
        return contextValueExpectation( () -> pName, pToCheck, pExpectation );
    }

    public DoubleAdder namedValueExpectation( String pName, DoubleAdder pToCheck, Expectation pExpectation ) {
        return contextValueExpectation( () -> pName, pToCheck, pExpectation );
    }

    public LongAccumulator namedValueExpectation( String pName, LongAccumulator pToCheck, Expectation pExpectation ) {
        return contextValueExpectation( () -> pName, pToCheck, pExpectation );
    }

    public LongAdder namedValueExpectation( String pName, LongAdder pToCheck, Expectation pExpectation ) {
        return contextValueExpectation( () -> pName, pToCheck, pExpectation );
    }

    public byte namedValueExpectation( String pName, byte pToCheck, Expectation pExpectation ) {
        return contextValueExpectation( () -> pName, pToCheck, pExpectation );
    }

    public short namedValueExpectation( String pName, short pToCheck, Expectation pExpectation ) {
        return contextValueExpectation( () -> pName, pToCheck, pExpectation );
    }

    public int namedValueExpectation( String pName, int pToCheck, Expectation pExpectation ) {
        return contextValueExpectation( () -> pName, pToCheck, pExpectation );
    }

    public long namedValueExpectation( String pName, long pToCheck, Expectation pExpectation ) {
        return contextValueExpectation( () -> pName, pToCheck, pExpectation );
    }

    public float namedValueExpectation( String pName, float pToCheck, Expectation pExpectation ) {
        return contextValueExpectation( () -> pName, pToCheck, pExpectation );
    }

    public double namedValueExpectation( String pName, double pToCheck, Expectation pExpectation ) {
        return contextValueExpectation( () -> pName, pToCheck, pExpectation );
    }

    public BigInteger namedValueExpectation( String pName, BigInteger pToCheck, Expectation pExpectation ) {
        return contextValueExpectation( () -> pName, pToCheck, pExpectation );
    }

    public BigDecimal namedValueExpectation( String pName, BigDecimal pToCheck, Expectation pExpectation ) {
        return contextValueExpectation( () -> pName, pToCheck, pExpectation );
    }

    public Byte contextValueExpectation( Supplier<String> pContext, Byte pToCheck, Expectation pExpectation ) {
        acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
        return pToCheck;
    }

    public Short contextValueExpectation( Supplier<String> pContext, Short pToCheck, Expectation pExpectation ) {
        acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
        return pToCheck;
    }

    public Integer contextValueExpectation( Supplier<String> pContext, Integer pToCheck, Expectation pExpectation ) {
        acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
        return pToCheck;
    }

    public Long contextValueExpectation( Supplier<String> pContext, Long pToCheck, Expectation pExpectation ) {
        acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
        return pToCheck;
    }

    public Float contextValueExpectation( Supplier<String> pContext, Float pToCheck, Expectation pExpectation ) {
        acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
        return pToCheck;
    }

    public Double contextValueExpectation( Supplier<String> pContext, Double pToCheck, Expectation pExpectation ) {
        acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
        return pToCheck;
    }

    public AtomicInteger contextValueExpectation( Supplier<String> pContext, AtomicInteger pToCheck, Expectation pExpectation ) {
        acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
        return pToCheck;
    }

    public AtomicLong contextValueExpectation( Supplier<String> pContext, AtomicLong pToCheck, Expectation pExpectation ) {
        acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
        return pToCheck;
    }

    public DoubleAccumulator contextValueExpectation( Supplier<String> pContext, DoubleAccumulator pToCheck, Expectation pExpectation ) {
        acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
        return pToCheck;
    }

    public DoubleAdder contextValueExpectation( Supplier<String> pContext, DoubleAdder pToCheck, Expectation pExpectation ) {
        acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
        return pToCheck;
    }

    public LongAccumulator contextValueExpectation( Supplier<String> pContext, LongAccumulator pToCheck, Expectation pExpectation ) {
        acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
        return pToCheck;
    }

    public LongAdder contextValueExpectation( Supplier<String> pContext, LongAdder pToCheck, Expectation pExpectation ) {
        acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
        return pToCheck;
    }

    public byte contextValueExpectation( Supplier<String> pContext, byte pToCheck, Expectation pExpectation ) {
        acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
        return pToCheck;
    }

    public short contextValueExpectation( Supplier<String> pContext, short pToCheck, Expectation pExpectation ) {
        acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
        return pToCheck;
    }

    public int contextValueExpectation( Supplier<String> pContext, int pToCheck, Expectation pExpectation ) {
        acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
        return pToCheck;
    }

    public long contextValueExpectation( Supplier<String> pContext, long pToCheck, Expectation pExpectation ) {
        acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
        return pToCheck;
    }

    public float contextValueExpectation( Supplier<String> pContext, float pToCheck, Expectation pExpectation ) {
        acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
        return pToCheck;
    }

    public double contextValueExpectation( Supplier<String> pContext, double pToCheck, Expectation pExpectation ) {
        acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
        return pToCheck;
    }

    public BigInteger contextValueExpectation( Supplier<String> pContext, BigInteger pToCheck, Expectation pExpectation ) {
        acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
        return pToCheck;
    }

    public BigDecimal contextValueExpectation( Supplier<String> pContext, BigDecimal pToCheck, Expectation pExpectation ) {
        acceptable( mChecker.value( pToCheck ), pContext, pToCheck, pExpectation );
        return pToCheck;
    }
}
