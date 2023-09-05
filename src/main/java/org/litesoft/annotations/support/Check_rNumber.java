package org.litesoft.annotations.support;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.DoubleAccumulator;
import java.util.concurrent.atomic.DoubleAdder;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * <code>Number</code> "checker" that handles the built-in <code>Number</code>s.
 * <p>
 * Check the parameter against an abstract comparator to determine if it is acceptable (<code>true</code>).
 * <p>
 * <code>null</code>s or <code>NaN</code>s are never acceptable!
 */
public abstract class Check_rNumber {
    public final boolean value( Byte pToCheck ) {
        return (pToCheck != null) && value( pToCheck.byteValue() );
    }

    public final boolean value( Short pToCheck ) {
        return (pToCheck != null) && value( pToCheck.shortValue() );
    }

    public final boolean value( Integer pToCheck ) {
        return (pToCheck != null) && value( pToCheck.intValue() );
    }

    public final boolean value( Long pToCheck ) {
        return (pToCheck != null) && value( pToCheck.longValue() );
    }

    public final boolean value( Float pToCheck ) {
        return (pToCheck != null) && value( pToCheck.floatValue() );
    }

    public final boolean value( Double pToCheck ) {
        return (pToCheck != null) && value( pToCheck.doubleValue() );
    }

    public final boolean value( AtomicInteger pToCheck ) {
        return (pToCheck != null) && value( pToCheck.intValue() );
    }

    public final boolean value( AtomicLong pToCheck ) {
        return (pToCheck != null) && value( pToCheck.longValue() );
    }

    public final boolean value( DoubleAccumulator pToCheck ) {
        return (pToCheck != null) && value( pToCheck.doubleValue() );
    }

    public final boolean value( DoubleAdder pToCheck ) {
        return (pToCheck != null) && value( pToCheck.doubleValue() );
    }

    public final boolean value( LongAccumulator pToCheck ) {
        return (pToCheck != null) && value( pToCheck.longValue() );
    }

    public final boolean value( LongAdder pToCheck ) {
        return (pToCheck != null) && value( pToCheck.longValue() );
    }

    public final boolean value( byte pToCheck ) {
        return longTestIt( pToCheck );
    }

    public final boolean value( short pToCheck ) {
        return longTestIt( pToCheck );
    }

    public final boolean value( int pToCheck ) {
        return longTestIt( pToCheck );
    }

    public final boolean value( long pToCheck ) {
        return longTestIt( pToCheck );
    }

    public final boolean value( float pToCheck ) {
        return !Float.isNaN( pToCheck ) && notNanTestIt( pToCheck );
    }

    public final boolean value( double pToCheck ) {
        return !Double.isNaN( pToCheck ) && notNanTestIt( pToCheck );
    }

    public final boolean value( BigInteger pToCheck ) {
        return (pToCheck != null) && notNullTestIt( pToCheck );
    }

    public final boolean value( BigDecimal pToCheck ) {
        return (pToCheck != null) && notNullTestIt( pToCheck );
    }

    protected abstract boolean longTestIt( long pToCheck );

    protected abstract boolean notNanTestIt( double pToCheck );

    protected abstract boolean notNullTestIt( BigInteger pToCheck );

    protected abstract boolean notNullTestIt( BigDecimal pToCheck );
}
