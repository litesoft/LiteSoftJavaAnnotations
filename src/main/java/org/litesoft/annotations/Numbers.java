package org.litesoft.annotations;

import java.math.BigDecimal;
import java.math.BigInteger;

@SuppressWarnings("unused")
public interface Numbers {
    // ZERO values
    Byte ZERO_Byte = 0;
    Short ZERO_Short = 0;
    Integer ZERO_Integer = 0;
    Long ZERO_Long = 0L;
    Float ZERO_Float = 0f;
    Double ZERO_Double = 0d;
    byte ZERO_byte = 0;
    short ZERO_short = 0;
    int ZERO_int = 0;
    long ZERO_long = 0;
    float ZERO_float = 0;
    double ZERO_double = 0;
    BigInteger ZERO_BigInteger = BigInteger.ZERO;
    BigDecimal ZERO_BigDecimal = BigDecimal.ZERO;

    // The following implementations of Number are mutable and hence should be exposed as static finals:
    //    AtomicInteger
    //    AtomicLong
    //    DoubleAccumulator
    //    DoubleAdder
    //    LongAccumulator
    //    LongAdder
}
