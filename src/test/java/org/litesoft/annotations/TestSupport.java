package org.litesoft.annotations;

import java.util.Objects;

import org.litesoft.annotations.expectations.Expectation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public abstract class TestSupport {
    abstract protected String getExpectation();

    public void checkExpectation( boolean expected, UnmetParams actual, String pContext, Object pToCheck ) {
        if ( expected ) {
            assertNull( actual );
        } else {
            assertEquals( new UnmetParams( pContext, pToCheck, getExpectation() ), actual );
        }
    }

    public static class UnmetParams {
        private final String mContext;
        private final Object mFailedValue;
        private final String mExpectation;

        public UnmetParams( String pContext, Object pFailedValue, String pExpectation ) {
            mContext = pContext;
            mFailedValue = pFailedValue;
            mExpectation = pExpectation;
        }

        @Override
        public String toString() {
            return "UnmetParams: " + mContext + " | " + mExpectation + " | " + mFailedValue;
        }

        @Override
        public boolean equals( Object o ) {
            if ( o == null || getClass() != o.getClass() ) {
                return false;
            }
            UnmetParams them = (UnmetParams)o;
            return (this == o) || (
                    Objects.equals( mContext, them.mContext ) &&
                    Objects.equals( mFailedValue, them.mFailedValue ) &&
                    Objects.equals( mExpectation, them.mExpectation ));
        }

        @Override
        public int hashCode() {
            return 0;
        }
    }

    public static class Exp implements Expectation {
        public UnmetParams params;

        @Override
        public void unmet( String pContext, Object pFailedValue, String pExpectation ) {
            params = new UnmetParams( pContext, pFailedValue, pExpectation );
        }

        @Override
        public void unmet( String pContext, String pMessageSuffix ) {
            throw new Error();
        }
    }
}
