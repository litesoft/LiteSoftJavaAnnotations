package org.litesoft.annotations;

import java.util.List;

import org.litesoft.annotations.support.Assert_rTyped;
import org.litesoft.annotations.support.Assert_rTypedWithCollection;
import org.litesoft.annotations.support.Assert_rTypedWithExpectationWithCollection;
import org.litesoft.annotations.support.Assert_rWithExpectation;
import org.litesoft.annotations.support.Check_rWithCollection;
import org.litesoft.annotations.support.Validate_rWithCollection;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("SameParameterValue")
public abstract class TestTypedSupport extends TestSupport {
    protected <T> void check_CheckList( Check_rWithCollection<T> checker, List<? extends CheckParamsList<T>> params ) {
        for ( CheckParamsList<T> entry : params ) {
            boolean result = checker.value( entry.toCheck );
            assertEquals( entry.expectedResult, result, entry.contextMsg );
        }
    }

    protected <T> void check_ValidateList( Validate_rWithCollection<T> validator, List<? extends CheckParamsList<T>> params ) {
        for ( CheckParamsList<T> entry : params ) {
            Exp expectation = new Exp();
            boolean result = validator.value( entry.contextMsg, entry.toCheck, expectation );
            assertEquals( entry.expectedResult, result, entry.contextMsg );
            checkExpectation( result, expectation.params, entry.contextMsg, entry.toCheck );
        }
    }

    protected <T> void check_Assert( Assert_rTyped<T> asserter, List<? extends CheckParams<T>> params ) {
        for ( CheckParams<T> entry : params ) {
            Exp expectation = new Exp();
            T result = asserter.namedValueExpectation( entry.contextMsg, entry.toCheck, expectation );
            assertEquals( entry.toCheck, result, entry.contextMsg );
            checkExpectation( entry.expectedResult, expectation.params, entry.contextMsg, entry.toCheck );
        }
    }

    protected <T> void check_AssertList( Assert_rTypedWithCollection<T> asserter, List<? extends CheckParamsList<T>> params ) {
        for ( CheckParamsList<T> entry : params ) {
            Exp expectation = new Exp();
            List<?> result = asserter.namedValueExpectation( entry.contextMsg, entry.toCheck, expectation );
            assertEquals( entry.toCheck, result, entry.contextMsg );
            checkExpectation( entry.expectedResult, expectation.params, entry.contextMsg, entry.toCheck );
        }
    }

    protected static class _Asserter_Typed_CheckParamsList<T> extends Abstract_Asserter {
        private final Assert_rTypedWithExpectationWithCollection<T> asserter;

        public _Asserter_Typed_CheckParamsList( Assert_rTypedWithExpectationWithCollection<T> pAsserter, Class<?> pExpectedThrowableClass ) {
            super( pExpectedThrowableClass );
            asserter = pAsserter;
        }

        public void checkAll( List<CheckParamsList<T>> OurParams ) {
            for ( CheckParamsList<T> params : OurParams ) {
                check( params );
            }
        }

        void check( CheckParamsList<T> params ) {
            List<T> actual;
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

    protected static class _Asserter_Typed_CheckParams<T> extends Abstract_Asserter {
        private final Assert_rWithExpectation<T> asserter;

        public _Asserter_Typed_CheckParams( Assert_rWithExpectation<T> pAsserter, Class<?> pExpectedThrowableClass ) {
            super( pExpectedThrowableClass );
            asserter = pAsserter;
        }

        public void checkAll( List<CheckParams<T>> OurParams ) {
            for ( CheckParams<T> params : OurParams ) {
                check( params );
            }
        }

        void check( CheckParams<T> params ) {
            T actual;
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
