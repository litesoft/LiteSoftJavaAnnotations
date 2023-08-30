package org.litesoft.annotations;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.litesoft.annotations.support.Assert_rTyped;
import org.litesoft.annotations.support.Assert_rTypedWithCollection;
import org.litesoft.annotations.support.Assert_rTypedWithExpectationWithCollection;
import org.litesoft.annotations.support.Check_r;
import org.litesoft.annotations.support.Check_rWithCollection;
import org.litesoft.annotations.support.Validate_r;
import org.litesoft.annotations.support.Validate_rWithCollection;

import static org.junit.jupiter.api.Assertions.*;

class NotEmptyTest extends TestSupport {
    @Override
    protected String getExpectation() {
        return NotEmpty.EXPECTATION;
    }

    private static final List<CheckParams<String>> OurStringParams = new ListOf<CheckParams<String>>()
            .with( new CheckParams<>( true, "Space", " " ) )
            .with( new CheckParams<>( false, "!Space", "" ) )
            .with( new CheckParams<>( false, "null", null ) );

    private static final List<CheckParamsList<String>> OurListParams = new ListOf<CheckParamsList<String>>()
            .with( new CheckParamsList<>( true, "Spaces", Collections.singletonList( " " ) ) )
            .with( new CheckParamsList<>( false, "!Spaces", Collections.emptyList() ) )
            .with( new CheckParamsList<>( false, "nullList", null ) );

    @Test
    void _Check() {
        check_Check( NotEmpty.Check, OurStringParams );
        check_CheckList( NotEmpty.Check, OurListParams );
    }

    @Test
    void _Validate() {
        check_Validate( NotEmpty.Validate, OurStringParams );
        check_ValidateList( NotEmpty.Validate, OurListParams );
    }

    @Test
    void _Assert() {
        check_Assert( NotEmpty.Assert, OurStringParams );
        check_AssertList( NotEmpty.Assert, OurListParams );
    }

    @Test
    void _AssertArgument() {
        new _Asserter( NotEmpty.AssertArgument, IllegalArgumentException.class )
                .checkAll();
    }

    @Test
    void _AssertState() {
        new _Asserter( NotEmpty.AssertState, IllegalStateException.class )
                .checkAll();
    }

    @Test
    void _AssertError() {
        new _Asserter( NotEmpty.AssertError, Error.class )
                .checkAll();
    }

    private static class _Asserter {
        private final Assert_rTypedWithExpectationWithCollection<String> asserter;
        private final Class<?> expectedThrowableClass;

        public _Asserter( Assert_rTypedWithExpectationWithCollection<String> pAsserter, Class<?> pExpectedThrowableClass ) {
            asserter = pAsserter;
            expectedThrowableClass = pExpectedThrowableClass;
        }

        void checkAll() {
            check( true, "Spaces", Collections.singletonList( " " ) );
            check( true, "Space", " " );
            check( false, "!Spaces", Collections.emptyList() );
            check( false, "!Space", "" );
            check( false, "null", (String)null );
            check( false, "nullList", (List<String>)null );
        }

        void check( boolean expected, String pContext, String pToCheck ) {
            String actual;
            try {
                actual = asserter.namedValue( pContext, pToCheck );
            }
            catch ( Throwable e ) {
                checkT( expected, e );
                return;
            }
            assertEquals( pToCheck, actual );
        }

        void check( boolean expected, String pContext, List<String> pToCheck ) {
            List<String> actual;
            try {
                actual = asserter.namedValue( pContext, pToCheck );
            }
            catch ( Throwable e ) {
                checkT( expected, e );
                return;
            }
            assertEquals( pToCheck, actual );
        }

        void checkT( boolean expected, Throwable t ) {
            if ( expected ) {
                assertNull( t );
            } else {
                assertInstanceOf( expectedThrowableClass, t );
            }
        }
    }
}