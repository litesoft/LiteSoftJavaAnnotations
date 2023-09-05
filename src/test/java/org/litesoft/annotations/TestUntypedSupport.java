package org.litesoft.annotations;

import java.util.List;

import org.litesoft.annotations.support.Assert_rUntyped;
import org.litesoft.annotations.support.Assert_rUntypedWithExpectation;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("SameParameterValue")
public abstract class TestUntypedSupport extends TestSupport {
    protected <T> void check_Assert( Assert_rUntyped asserter, List<? extends CheckParams<T>> params ) {
        for ( CheckParams<T> entry : params ) {
            Exp expectation = new Exp();
            T result = asserter.namedValueExpectation( entry.contextMsg, entry.toCheck, expectation );
            assertEquals( entry.toCheck, result, entry.contextMsg );
            checkExpectation( entry.expectedResult, expectation.params, entry.contextMsg, entry.toCheck );
        }
    }

    protected static class _Asserter_Untyped_CheckParams extends Abstract_Asserter {
        private final Assert_rUntypedWithExpectation asserter;

        public _Asserter_Untyped_CheckParams( Assert_rUntypedWithExpectation pAsserter, Class<?> pExpectedThrowableClass ) {
            super( pExpectedThrowableClass );
            asserter = pAsserter;
        }

        public void checkAll( List<CheckParams<Object>> OurParams ) {
            for ( CheckParams<Object> params : OurParams ) {
                check( params );
            }
        }

        void check( CheckParams<Object> params ) {
            Object actual;
            try {
                actual = asserter.namedValue( params.contextMsg, params.toCheck );
            }
            catch ( Throwable e ) {
                checkT( params.expectedResult, e, params.contextMsg );
                return;
            }
            assertEquals( params.toCheck, actual, params.contextMsg );
        }
    }
}
