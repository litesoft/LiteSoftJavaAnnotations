package org.litesoft.annotations;

import java.util.List;

import org.litesoft.annotations.support.CollectionAssert_r;
import org.litesoft.annotations.support.CollectionAssert_rWithExpectation;
import org.litesoft.annotations.support.CollectionCheck_r;
import org.litesoft.annotations.support.CollectionValidate_r;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("SameParameterValue")
public abstract class TestCollectionSupport extends TestSupport {
    protected <T> void check_CheckList( CollectionCheck_r checker, List<? extends CheckParamsList<T>> params ) {
        for ( CheckParamsList<T> entry : params ) {
            boolean result = checker.value( entry.toCheck );
            assertEquals( entry.expectedResult, result, entry.contextMsg );
        }
    }

    protected <T> void check_ValidateList( CollectionValidate_r validator, List<CheckParamsList<T>> params ) {
        for ( CheckParamsList<T> entry : params ) {
            Exp expectation = new Exp();
            boolean result = validator.value( entry.contextMsg, entry.toCheck, expectation );
            assertEquals( entry.expectedResult, result, entry.contextMsg );
            checkExpectation( result, expectation.params, entry.contextMsg, entry.toCheck );
        }
    }

    protected <T> void check_AssertList( CollectionAssert_r asserter, List<CheckParamsList<T>> params ) {
        for ( CheckParamsList<T> entry : params ) {
            Exp expectation = new Exp();
            List<T> result = asserter.namedValueExpectation( entry.contextMsg, entry.toCheck, expectation );
            assertEquals( entry.toCheck, result, entry.contextMsg );
            checkExpectation( entry.expectedResult, expectation.params, entry.contextMsg, entry.toCheck );
        }
    }

    protected static class _Asserter_Collections_CheckParams extends Abstract_Asserter {
        private final CollectionAssert_rWithExpectation asserter;

        public _Asserter_Collections_CheckParams( CollectionAssert_rWithExpectation pAsserter, Class<?> pExpectedThrowableClass ) {
            super( pExpectedThrowableClass );
            asserter = pAsserter;
        }

        public <T> void checkAll( List<CheckParamsList<T>> OurParams ) {
            for ( CheckParamsList<?> params : OurParams ) {
                check( params );
            }
        }

        void check( CheckParamsList<?> params ) {
            List<?> actual;
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
