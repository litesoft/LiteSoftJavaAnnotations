package org.litesoft.annotations;

import org.litesoft.annotations.expectations.IllegalArgument;
import org.litesoft.annotations.expectations.IllegalState;
import org.litesoft.annotations.expectations.ThrowError;
import org.litesoft.annotations.support.Assert_rTypedEquivalent;

@SuppressWarnings("unused")
public interface Equivalent {
    Assert_rTypedEquivalent AssertArgument = new Assert_rTypedEquivalent( IllegalArgument.INSTANCE );
    Assert_rTypedEquivalent AssertState = new Assert_rTypedEquivalent( IllegalState.INSTANCE );
    Assert_rTypedEquivalent AssertError = new Assert_rTypedEquivalent( ThrowError.INSTANCE );

//    interface AssertArgument {
//        @SuppressWarnings("UnusedReturnValue")
//        static <T> T sameNotNull( String expectedName, T expected, String actualName, T actual ) {
//            if ( NotNull.AssertArgument.namedValue( expectedName, expected ) !=
//                 NotNull.AssertArgument.namedValue( actualName, actual ) ) {
//                throw new IllegalArgumentException( "Actual '" + actualName
//                                                    + "' not the same instance as (expected) '"
//                                                    + expectedName + "':"
//                                                    + "\n  expected: " + expected
//                                                    + "\n    actual: " + actual );
//            }
//            return actual;
//        }
//
//        @SuppressWarnings("UnusedReturnValue")
//        static <T> T equalNotNull( String expectedName, T expected, String actualName, T actual ) {
//            if ( !NotNull.AssertArgument.namedValue( expectedName, expected ).equals(
//                    NotNull.AssertArgument.namedValue( actualName, actual ) ) ) {
//                throw new IllegalArgumentException( "Actual '" + actualName
//                                                    + "' does not equal the (expected) '"
//                                                    + expectedName + "':"
//                                                    + "\n  expected: " + expected
//                                                    + "\n    actual: " + actual );
//            }
//            return actual;
//        }
//    }
}
