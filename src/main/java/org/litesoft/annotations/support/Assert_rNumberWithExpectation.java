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
public class Assert_rNumberWithExpectation extends UnmetCheck {
    private final Check_rNumber mChecker;
    private final Expectation mExpectation;

    public Assert_rNumberWithExpectation( String pExpectationString, Check_rNumber pChecker, Expectation pExpectation ) {
        super( pExpectationString );
        mChecker = assertNotNull( pChecker );
        mExpectation = assertNotNull( pExpectation );
    }

    public Byte namedValue( String pName, Byte pToCheck ) { return contextValue( () -> pName, pToCheck ); }
    public Short namedValue( String pName, Short pToCheck ) { return contextValue( () -> pName, pToCheck ); }
    public Integer namedValue( String pName, Integer pToCheck ) { return contextValue( () -> pName, pToCheck ); }
    public Long namedValue( String pName, Long pToCheck ) { return contextValue( () -> pName, pToCheck ); }
    public Float namedValue( String pName, Float pToCheck ) { return contextValue( () -> pName, pToCheck ); }
    public Double namedValue( String pName, Double pToCheck ) { return contextValue( () -> pName, pToCheck ); }
    public AtomicInteger namedValue( String pName, AtomicInteger pToCheck ) { return contextValue( () -> pName, pToCheck ); }
    public AtomicLong namedValue( String pName, AtomicLong pToCheck ) { return contextValue( () -> pName, pToCheck ); }
    public DoubleAccumulator namedValue( String pName, DoubleAccumulator pToCheck ) { return contextValue( () -> pName, pToCheck ); }
    public DoubleAdder namedValue( String pName, DoubleAdder pToCheck ) { return contextValue( () -> pName, pToCheck ); }
    public LongAccumulator namedValue( String pName, LongAccumulator pToCheck ) { return contextValue( () -> pName, pToCheck ); }
    public LongAdder namedValue( String pName, LongAdder pToCheck ) { return contextValue( () -> pName, pToCheck ); }
    public byte namedValue( String pName, byte pToCheck ) { return contextValue( () -> pName, pToCheck ); }
    public short namedValue( String pName, short pToCheck ) { return contextValue( () -> pName, pToCheck ); }
    public int namedValue( String pName, int pToCheck ) { return contextValue( () -> pName, pToCheck ); }
    public long namedValue( String pName, long pToCheck ) { return contextValue( () -> pName, pToCheck ); }
    public float namedValue( String pName, float pToCheck ) { return contextValue( () -> pName, pToCheck ); }
    public double namedValue( String pName, double pToCheck ) { return contextValue( () -> pName, pToCheck ); }
    public BigInteger namedValue( String pName, BigInteger pToCheck ) { return contextValue( () -> pName, pToCheck ); }
    public BigDecimal namedValue( String pName, BigDecimal pToCheck ) { return contextValue( () -> pName, pToCheck ); }

    public Byte contextValue( Supplier<String> pContext, Byte pToCheck ) { acceptable( mChecker.value( pToCheck ), pContext, pToCheck, mExpectation ); return pToCheck; }
    public Short contextValue( Supplier<String> pContext, Short pToCheck ) { acceptable( mChecker.value( pToCheck ), pContext, pToCheck, mExpectation ); return pToCheck; }
    public Integer contextValue( Supplier<String> pContext, Integer pToCheck ) { acceptable( mChecker.value( pToCheck ), pContext, pToCheck, mExpectation ); return pToCheck; }
    public Long contextValue( Supplier<String> pContext, Long pToCheck ) { acceptable( mChecker.value( pToCheck ), pContext, pToCheck, mExpectation ); return pToCheck; }
    public Float contextValue( Supplier<String> pContext, Float pToCheck ) { acceptable( mChecker.value( pToCheck ), pContext, pToCheck, mExpectation ); return pToCheck; }
    public Double contextValue( Supplier<String> pContext, Double pToCheck ) { acceptable( mChecker.value( pToCheck ), pContext, pToCheck, mExpectation ); return pToCheck; }
    public AtomicInteger contextValue( Supplier<String> pContext, AtomicInteger pToCheck ) { acceptable( mChecker.value( pToCheck ), pContext, pToCheck, mExpectation ); return pToCheck; }
    public AtomicLong contextValue( Supplier<String> pContext, AtomicLong pToCheck ) { acceptable( mChecker.value( pToCheck ), pContext, pToCheck, mExpectation ); return pToCheck; }
    public DoubleAccumulator contextValue( Supplier<String> pContext, DoubleAccumulator pToCheck ) { acceptable( mChecker.value( pToCheck ), pContext, pToCheck, mExpectation ); return pToCheck; }
    public DoubleAdder contextValue( Supplier<String> pContext, DoubleAdder pToCheck ) { acceptable( mChecker.value( pToCheck ), pContext, pToCheck, mExpectation ); return pToCheck; }
    public LongAccumulator contextValue( Supplier<String> pContext, LongAccumulator pToCheck ) { acceptable( mChecker.value( pToCheck ), pContext, pToCheck, mExpectation ); return pToCheck; }
    public LongAdder contextValue( Supplier<String> pContext, LongAdder pToCheck ) { acceptable( mChecker.value( pToCheck ), pContext, pToCheck, mExpectation ); return pToCheck; }
    public byte contextValue( Supplier<String> pContext, byte pToCheck ) { acceptable( mChecker.value( pToCheck ), pContext, pToCheck, mExpectation ); return pToCheck; }
    public short contextValue( Supplier<String> pContext, short pToCheck ) { acceptable( mChecker.value( pToCheck ), pContext, pToCheck, mExpectation ); return pToCheck; }
    public int contextValue( Supplier<String> pContext, int pToCheck ) { acceptable( mChecker.value( pToCheck ), pContext, pToCheck, mExpectation ); return pToCheck; }
    public long contextValue( Supplier<String> pContext, long pToCheck ) { acceptable( mChecker.value( pToCheck ), pContext, pToCheck, mExpectation ); return pToCheck; }
    public float contextValue( Supplier<String> pContext, float pToCheck ) { acceptable( mChecker.value( pToCheck ), pContext, pToCheck, mExpectation ); return pToCheck; }
    public double contextValue( Supplier<String> pContext, double pToCheck ) { acceptable( mChecker.value( pToCheck ), pContext, pToCheck, mExpectation ); return pToCheck; }
    public BigInteger contextValue( Supplier<String> pContext, BigInteger pToCheck ) { acceptable( mChecker.value( pToCheck ), pContext, pToCheck, mExpectation ); return pToCheck; }
    public BigDecimal contextValue( Supplier<String> pContext, BigDecimal pToCheck ) { acceptable( mChecker.value( pToCheck ), pContext, pToCheck, mExpectation ); return pToCheck; }
}
