package org.litesoft.annotations;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NotNullTest extends TestUntypedSupport {
    @Override
    protected String getExpectation() {
        return NotNull.EXPECTATION;
    }

    private static final List<CheckParams<Object>> OurParams = new ListOf<CheckParams<Object>>()
            .with( new CheckParams<>( true, "Space", " " ) )
            .with( new CheckParams<>( true, "!Space", "" ) )
            .with( new CheckParams<>( true, "Spaces", Collections.singletonList( " " ) ) )
            .with( new CheckParams<>( true, "!Spaces", Collections.emptyList() ) )
            .with( new CheckParams<>( false, "null", null ) );

    @Test
    void _Check() {
        check_Check( NotNull.Check, OurParams );
    }

    @Test
    void _Validate() {
        check_Validate( NotNull.Validate, OurParams );
    }

    @Test
    void _Assert() {
        check_Assert( NotNull.Assert, OurParams );
    }

    @Test
    void _AssertArgument() {
        new _Asserter_Untyped_CheckParams( NotNull.AssertArgument, IllegalArgumentException.class )
                .checkAll( OurParams );
    }

    @Test
    void _AssertState() {
        new _Asserter_Untyped_CheckParams( NotNull.AssertState, IllegalStateException.class )
                .checkAll( OurParams );
    }

    @Test
    void _AssertError() {
        new _Asserter_Untyped_CheckParams( NotNull.AssertError, Error.class )
                .checkAll( OurParams );
    }

    @Test
    void constrainTo() {
        String whatever = "whatever";
        String notWhatever = "!whatever";
        assertEquals( whatever, NotNull.ConstrainTo.valueOr( whatever, notWhatever ) );
        assertEquals( notWhatever, NotNull.ConstrainTo.valueOr( null, notWhatever ) );
    }

    @Test
    void countNotNull() {
        assertEquals( 0, NotNull.Count.of( EMPTY ), "EMPTY" );
        assertEquals( 0, NotNull.Count.of( JUST_NULLS ), "JUST_NULLS" );
        assertEquals( 4, NotNull.Count.of( VALUES ), "VALUES" );
    }

    private static final Object[] EMPTY = {};
    private static final Object[] JUST_NULLS = {null};
    private static final Object[] VALUES = {5, 2, null, 7, "Martin Fowler"};
}