package org.litesoft.annotations;

import java.util.List;

import org.litesoft.annotations.support.Assert_rTypedWithNormalizer;
import org.litesoft.annotations.support.Assert_rWithExpectation;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SuppressWarnings("SameParameterValue")
public abstract class TestTypedMorphedSupport extends TestTypedSupport {
    protected <T> void check_Assert( Assert_rTypedWithNormalizer<T> asserter, List<CheckParamsWithMorphedOutput<T>> params ) {
        for ( CheckParamsWithMorphedOutput<T> entry : params ) {
            Exp expectation = new Exp();
            T result = asserter.namedValueExpectation( entry.contextMsg, entry.toCheck, expectation );
            assertEquals( entry.morphedOutput, result, entry.contextMsg );
            checkExpectation( entry.expectedResult, expectation.params, entry.contextMsg, entry.toCheck );
        }
    }

    protected static class _Asserter_Typed_CheckParamsWithMorphedOutput<T> extends Abstract_Asserter {
        private final Assert_rWithExpectation<T> asserter;

        public _Asserter_Typed_CheckParamsWithMorphedOutput( Assert_rWithExpectation<T> pAsserter, Class<?> pExpectedThrowableClass ) {
            super( pExpectedThrowableClass );
            asserter = pAsserter;
        }

        public void checkAll( List<CheckParamsWithMorphedOutput<T>> OurParams ) {
            for ( CheckParamsWithMorphedOutput<T> params : OurParams ) {
                check( params );
            }
        }

        void check( CheckParamsWithMorphedOutput<T> params ) {
            T actual;
            try {
                actual = asserter.namedValue( params.contextMsg, params.toCheck );
            }
            catch ( Throwable e ) {
                checkT( params.expectedResult, e, params.contextMsg );
                return;
            }
            assertEquals( params.morphedOutput, actual, params.contextMsg );
        }
    }

    public static class CheckParamsWithMorphedOutput<T> extends CheckParams<T> {
        public final T morphedOutput;

        public CheckParamsWithMorphedOutput( boolean pExpectedResult, String pContextMsg, T pToCheck, T pMorphedOutput ) {
            super( pExpectedResult, pContextMsg, pToCheck );
            morphedOutput = pMorphedOutput;
        }
    }
}
