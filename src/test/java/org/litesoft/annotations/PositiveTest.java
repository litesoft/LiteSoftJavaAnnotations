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

import org.junit.jupiter.api.Test;

class PositiveTest extends TestSupport {
    @Override
    protected String getExpectation() {
        return Positive.EXPECTATION;
    }

    private static final List<CheckParams<Byte>> ParamsByte = new ListOf<CheckParams<Byte>>()
            .with( new CheckParams<>( false, "-1", (byte)-1 ) )
            .with( new CheckParams<>( false, "0", (byte)0 ) )
            .with( new CheckParams<>( true, "1", (byte)1 ) );
    private static final List<CheckParams<Short>> ParamsShort = new ListOf<CheckParams<Short>>()
            .with( new CheckParams<>( false, "-1", (short)-1 ) )
            .with( new CheckParams<>( false, "0", (short)0 ) )
            .with( new CheckParams<>( true, "1", (short)1 ) );
    private static final List<CheckParams<Integer>> ParamsInteger = new ListOf<CheckParams<Integer>>()
            .with( new CheckParams<>( false, "-1",  -1 ) )
            .with( new CheckParams<>( false, "0",  0 ) )
            .with( new CheckParams<>( true, "1",  1 ) );
    private static final List<CheckParams<Long>> ParamsLong = new ListOf<CheckParams<Long>>()
            .with( new CheckParams<>( false, "-1",  -1L ) )
            .with( new CheckParams<>( false, "0",  0L ) )
            .with( new CheckParams<>( true, "1",  1L ) );
    private static final List<CheckParams<Float>> ParamsFloat = new ListOf<CheckParams<Float>>()
            .with( new CheckParams<>( false, "-1",  -1f ) )
            .with( new CheckParams<>( false, "0",  0f ) )
            .with( new CheckParams<>( true, "1",  1f ) );
    private static final List<CheckParams<Double>> ParamsDouble = new ListOf<CheckParams<Double>>()
            .with( new CheckParams<>( false, "-1",  -1d ) )
            .with( new CheckParams<>( false, "0",  0d ) )
            .with( new CheckParams<>( true, "1",  1d ) );
    private static final List<CheckParams<AtomicInteger>> ParamsAtomicInteger = new ListOf<CheckParams<AtomicInteger>>()
            .with( new CheckParams<>( false, "-1", new AtomicInteger( -1 ) ) )
            .with( new CheckParams<>( false, "0", new AtomicInteger( 0 ) ) )
            .with( new CheckParams<>( true, "1", new AtomicInteger( 1 ) ) );
    private static final List<CheckParams<AtomicLong>> ParamsAtomicLong = new ListOf<CheckParams<AtomicLong>>()
            .with( new CheckParams<>( false, "-1", new AtomicLong( -1 ) ) )
            .with( new CheckParams<>( false, "0", new AtomicLong( 0 ) ) )
            .with( new CheckParams<>( true, "1", new AtomicLong( 1 ) ) );
    private static final List<CheckParams<DoubleAccumulator>> ParamsDoubleAccumulator = new ListOf<CheckParams<DoubleAccumulator>>()
            .with( new CheckParams<>( false, "-1", new DoubleAccumulator( null, -1 ) ) )
            .with( new CheckParams<>( false, "0", new DoubleAccumulator( null, 0 ) ) )
            .with( new CheckParams<>( true, "1", new DoubleAccumulator( null, 1 ) ) );
    private static final List<CheckParams<DoubleAdder>> ParamsDoubleAdder = new ListOf<CheckParams<DoubleAdder>>()
            .with( new CheckParams<>( false, "-1", doubleAdder( -1 ) ) )
            .with( new CheckParams<>( false, "0", doubleAdder( 0 ) ) )
            .with( new CheckParams<>( true, "1", doubleAdder( 1 ) ) );
    private static final List<CheckParams<LongAccumulator>> ParamsLongAccumulator = new ListOf<CheckParams<LongAccumulator>>()
            .with( new CheckParams<>( false, "-1", new LongAccumulator( null, -1 ) ) )
            .with( new CheckParams<>( false, "0", new LongAccumulator( null, 0 ) ) )
            .with( new CheckParams<>( true, "1", new LongAccumulator( null, 1 ) ) );
    private static final List<CheckParams<LongAdder>> ParamsLongAdder = new ListOf<CheckParams<LongAdder>>()
            .with( new CheckParams<>( false, "-1", longAdder( -1 ) ) )
            .with( new CheckParams<>( false, "0", longAdder( 0 ) ) )
            .with( new CheckParams<>( true, "1", longAdder( 1 ) ) );
    private static final List<CheckParams<BigInteger>> ParamsBigInteger = new ListOf<CheckParams<BigInteger>>()
            .with( new CheckParams<>( false, "-1", BigInteger.valueOf( -1 ) ) )
            .with( new CheckParams<>( false, "0", BigInteger.ZERO ) )
            .with( new CheckParams<>( true, "1", BigInteger.ONE ) );
    private static final List<CheckParams<BigDecimal>> ParamsBigDecimal = new ListOf<CheckParams<BigDecimal>>()
            .with( new CheckParams<>( false, "-1", BigDecimal.valueOf( -1 ) ) )
            .with( new CheckParams<>( false, "0", BigDecimal.ZERO ) )
            .with( new CheckParams<>( true, "1", BigDecimal.ONE ) );

//    @Test
//    void _Check() {
//        check_Check( Positive.Check, OurParams );
//    }

    @Test
    void _Validate() {
        check_ValidateNumber( Positive.Validate, ParamsByte );
        check_ValidateNumber( Positive.Validate, ParamsShort );
        check_ValidateNumber( Positive.Validate, ParamsInteger );
        check_ValidateNumber( Positive.Validate, ParamsLong );
        check_ValidateNumber( Positive.Validate, ParamsFloat );
        check_ValidateNumber( Positive.Validate, ParamsDouble );
        check_ValidateNumber( Positive.Validate, ParamsAtomicInteger );
        check_ValidateNumber( Positive.Validate, ParamsAtomicLong );
        check_ValidateNumber( Positive.Validate, ParamsDoubleAccumulator );
        check_ValidateNumber( Positive.Validate, ParamsDoubleAdder );
        check_ValidateNumber( Positive.Validate, ParamsLongAccumulator );
        check_ValidateNumber( Positive.Validate, ParamsLongAdder );
        check_ValidateNumber( Positive.Validate, ParamsBigInteger );
        check_ValidateNumber( Positive.Validate, ParamsBigDecimal );
    }

    @Test
    void _Assert() {
        check_AssertNumber( Positive.Assert, ParamsByte );
        check_AssertNumber( Positive.Assert, ParamsShort );
        check_AssertNumber( Positive.Assert, ParamsInteger );
        check_AssertNumber( Positive.Assert, ParamsLong );
        check_AssertNumber( Positive.Assert, ParamsFloat );
        check_AssertNumber( Positive.Assert, ParamsDouble );
        check_AssertNumber( Positive.Assert, ParamsAtomicInteger );
        check_AssertNumber( Positive.Assert, ParamsAtomicLong );
        check_AssertNumber( Positive.Assert, ParamsDoubleAccumulator );
        check_AssertNumber( Positive.Assert, ParamsDoubleAdder );
        check_AssertNumber( Positive.Assert, ParamsLongAccumulator );
        check_AssertNumber( Positive.Assert, ParamsLongAdder );
        check_AssertNumber( Positive.Assert, ParamsBigInteger );
        check_AssertNumber( Positive.Assert, ParamsBigDecimal );
    }

    @Test
    void _AssertArgument() {
        check_AssertNumber( Positive.AssertArgument, IllegalArgumentException.class, ParamsByte );
        check_AssertNumber( Positive.AssertArgument, IllegalArgumentException.class, ParamsShort );
        check_AssertNumber( Positive.AssertArgument, IllegalArgumentException.class, ParamsInteger );
        check_AssertNumber( Positive.AssertArgument, IllegalArgumentException.class, ParamsLong );
        check_AssertNumber( Positive.AssertArgument, IllegalArgumentException.class, ParamsFloat );
        check_AssertNumber( Positive.AssertArgument, IllegalArgumentException.class, ParamsDouble );
        check_AssertNumber( Positive.AssertArgument, IllegalArgumentException.class, ParamsAtomicInteger );
        check_AssertNumber( Positive.AssertArgument, IllegalArgumentException.class, ParamsAtomicLong );
        check_AssertNumber( Positive.AssertArgument, IllegalArgumentException.class, ParamsDoubleAccumulator );
        check_AssertNumber( Positive.AssertArgument, IllegalArgumentException.class, ParamsDoubleAdder );
        check_AssertNumber( Positive.AssertArgument, IllegalArgumentException.class, ParamsLongAccumulator );
        check_AssertNumber( Positive.AssertArgument, IllegalArgumentException.class, ParamsLongAdder );
        check_AssertNumber( Positive.AssertArgument, IllegalArgumentException.class, ParamsBigInteger );
        check_AssertNumber( Positive.AssertArgument, IllegalArgumentException.class, ParamsBigDecimal );
    }

//    void _AssertArgument() {
//        new _Asserter_Untyped_CheckParams( Positive.AssertArgument, IllegalArgumentException.class )
//                .checkAll( OurParams );
//    }
//
//    @Test
//    void _AssertState() {
//        new _Asserter_Untyped_CheckParams( Positive.AssertState, IllegalStateException.class )
//                .checkAll( OurParams );
//    }
//
//    @Test
//    void _AssertError() {
//        new _Asserter_Untyped_CheckParams( Positive.AssertError, Error.class )
//                .checkAll( OurParams );
//    }

    private static DoubleAdder doubleAdder(int value) {
        DoubleAdder zAdder = new DoubleAdder();
        zAdder.add( value );
        return zAdder;
    }

    private static LongAdder longAdder(int value) {
        LongAdder zAdder = new LongAdder();
        zAdder.add( value );
        return zAdder;
    }
}