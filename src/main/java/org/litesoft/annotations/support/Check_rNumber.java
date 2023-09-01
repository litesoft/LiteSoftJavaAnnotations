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
    public boolean value( Byte pToCheck ) { return (pToCheck != null) && value( pToCheck.byteValue() ); }
    public boolean value( Short pToCheck ) { return (pToCheck != null) && value( pToCheck.shortValue() ); }
    public boolean value( Integer pToCheck ) { return (pToCheck != null) && value( pToCheck.intValue() ); }
    public boolean value( Long pToCheck ) { return (pToCheck != null) && value( pToCheck.longValue() ); }
    public boolean value( Float pToCheck ) { return (pToCheck != null) && value( pToCheck.floatValue() ); }
    public boolean value( Double pToCheck ) { return (pToCheck != null) && value( pToCheck.doubleValue() ); }
    public boolean value( AtomicInteger pToCheck ) { return (pToCheck != null) && value( pToCheck.intValue() ); }
    public boolean value( AtomicLong pToCheck ) { return (pToCheck != null) && value( pToCheck.longValue() ); }
    public boolean value( DoubleAccumulator pToCheck ) { return (pToCheck != null) && value( pToCheck.doubleValue() ); }
    public boolean value( DoubleAdder pToCheck ) { return (pToCheck != null) && value( pToCheck.doubleValue() ); }
    public boolean value( LongAccumulator pToCheck ) { return (pToCheck != null) && value( pToCheck.longValue() ); }
    public boolean value( LongAdder pToCheck ) { return (pToCheck != null) && value( pToCheck.longValue() ); }

    public boolean value( byte pToCheck ) { return value( (long)pToCheck ); }
    public boolean value( short pToCheck ) { return value( (long)pToCheck ); }
    public boolean value( int pToCheck ) { return value( (long)pToCheck ); }

    public abstract boolean value( long pToCheck );
    public boolean value( float pToCheck ) { return !Float.isNaN( pToCheck ) && nonNan( pToCheck ); }
    public boolean value( double pToCheck ) {return !Double.isNaN( pToCheck ) && nonNan( pToCheck ); }

    public boolean value( BigInteger pToCheck ) { return (pToCheck != null) && notNullTest( pToCheck ); }
    public boolean value( BigDecimal pToCheck ) { return (pToCheck != null) && notNullTest( pToCheck ); }

    protected abstract boolean nonNan( double pToCheck );
    protected abstract boolean notNullTest( BigInteger pToCheck );
    protected abstract boolean notNullTest( BigDecimal pToCheck );
}
