package org.litesoft.annotations;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.DoubleAccumulator;
import java.util.concurrent.atomic.LongAccumulator;

import org.junit.jupiter.api.Test;

class NotNegativeTest extends TestNumberSupport {
    @Override
    protected String getExpectation() {
        return NotNegative.EXPECTATION;
    }

    private static final List<CheckParams<?>> OurParams = new ListOf<CheckParams<?>>()
            .with( new CheckNumberParams<>( false, "-1", (byte)-1 ) )
            .with( new CheckNumberParams<>( true, "0", (byte)0 ) )
            .with( new CheckNumberParams<>( true, "1", (byte)1 ) )
            .with( new CheckNumberParams<>( false, "-1", (short)-1 ) )
            .with( new CheckNumberParams<>( true, "0", (short)0 ) )
            .with( new CheckNumberParams<>( true, "1", (short)1 ) )
            .with( new CheckNumberParams<>( false, "-1", -1 ) )
            .with( new CheckNumberParams<>( true, "0", 0 ) )
            .with( new CheckNumberParams<>( true, "1", 1 ) )
            .with( new CheckNumberParams<>( false, "-1", -1L ) )
            .with( new CheckNumberParams<>( true, "0", 0L ) )
            .with( new CheckNumberParams<>( true, "1", 1L ) )
            .with( new CheckNumberParams<>( false, "-1", -1f ) )
            .with( new CheckNumberParams<>( true, "0", 0f ) )
            .with( new CheckNumberParams<>( true, "1", 1f ) )
            .with( new CheckNumberParams<>( false, "-1", -1d ) )
            .with( new CheckNumberParams<>( true, "0", 0d ) )
            .with( new CheckNumberParams<>( true, "1", 1d ) )
            .with( new CheckNumberParams<>( false, "-1", new AtomicInteger( -1 ) ) )
            .with( new CheckNumberParams<>( true, "0", new AtomicInteger( 0 ) ) )
            .with( new CheckNumberParams<>( true, "1", new AtomicInteger( 1 ) ) )
            .with( new CheckNumberParams<>( false, "-1", new AtomicLong( -1 ) ) )
            .with( new CheckNumberParams<>( true, "0", new AtomicLong( 0 ) ) )
            .with( new CheckNumberParams<>( true, "1", new AtomicLong( 1 ) ) )
            .with( new CheckNumberParams<>( false, "-1", new DoubleAccumulator( null, -1 ) ) )
            .with( new CheckNumberParams<>( true, "0", new DoubleAccumulator( null, 0 ) ) )
            .with( new CheckNumberParams<>( true, "1", new DoubleAccumulator( null, 1 ) ) )
            .with( new CheckNumberParams<>( false, "-1", doubleAdder( -1 ) ) )
            .with( new CheckNumberParams<>( true, "0", doubleAdder( 0 ) ) )
            .with( new CheckNumberParams<>( true, "1", doubleAdder( 1 ) ) )
            .with( new CheckNumberParams<>( false, "-1", new LongAccumulator( null, -1 ) ) )
            .with( new CheckNumberParams<>( true, "0", new LongAccumulator( null, 0 ) ) )
            .with( new CheckNumberParams<>( true, "1", new LongAccumulator( null, 1 ) ) )
            .with( new CheckNumberParams<>( false, "-1", longAdder( -1 ) ) )
            .with( new CheckNumberParams<>( true, "0", longAdder( 0 ) ) )
            .with( new CheckNumberParams<>( true, "1", longAdder( 1 ) ) )
            .with( new CheckNumberParams<>( false, "-1", BigInteger.valueOf( -1 ) ) )
            .with( new CheckNumberParams<>( true, "0", BigInteger.ZERO ) )
            .with( new CheckNumberParams<>( true, "1", BigInteger.ONE ) )
            .with( new CheckNumberParams<>( false, "-1", BigDecimal.valueOf( -1 ) ) )
            .with( new CheckNumberParams<>( true, "0", BigDecimal.ZERO ) )
            .with( new CheckNumberParams<>( true, "1", BigDecimal.ONE ) );

    @Test
    void _Validate() {
        check_ValidateNumber( NotNegative.Validate, OurParams );
    }

    @Test
    void _Assert() {
        check_AssertNumber( NotNegative.Assert, OurParams );
    }

    @Test
    void _AssertArgument() {
        new _Asserter_Number_CheckParams( NotNegative.AssertArgument, IllegalArgumentException.class ).checkAll( OurParams );
    }

    @Test
    void _AssertState() {
        new _Asserter_Number_CheckParams( NotNegative.AssertState, IllegalStateException.class )
                .checkAll( OurParams );
    }

    @Test
    void _AssertError() {
        new _Asserter_Number_CheckParams( NotNegative.AssertError, Error.class )
                .checkAll( OurParams );
    }
}