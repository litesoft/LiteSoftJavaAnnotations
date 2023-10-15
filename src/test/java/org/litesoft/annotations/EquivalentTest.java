package org.litesoft.annotations;

import java.util.function.Supplier;

import org.junit.jupiter.api.Test;
import org.litesoft.annotations.expectations.Expectation;
import org.litesoft.annotations.support.Assert_rTypedEquivalent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

// Actually test the underlying implementation: Assert_rTypedEquivalent
class EquivalentTest {
    public static class Exp implements Expectation {
        public String params;

        @Override
        public void unmet( String pContext, String pMessageSuffix ) {
            params = pContext + " is " + pMessageSuffix;
        }
    }

    Exp expectation = new Exp();
    Assert_rTypedEquivalent toCheck = new Assert_rTypedEquivalent( expectation );
    String value1 = "val1";
    String value1_2 = " val1 ".trim();
    String value2 = "val2";
    String expectedName = "1exp";
    String actualName = "1act";

    @Test
    void testSame() {
        // assertSame( value1, value1_2 );
        expectedOK( value1,
                    () -> toCheck.sameNotNull( expectedName, value1, actualName, value1 ) );
        expectedError( "Actual '" + actualName + "' is " + Assert_rTypedEquivalent.NOT_SAME + " (expected) '" + expectedName + "':"
                       + "\n  expected: " + value1
                       + "\n    actual: " + value1,
                       () -> toCheck.sameNotNull( expectedName, value1, actualName, value1_2 ) );
    }

    @Test
    void testEqual() {
        expectedOK( value1,
                    () -> toCheck.equalNotNull( expectedName, value1, actualName, value1 ) );
        expectedOK( value1,
                    () -> toCheck.equalNotNull( expectedName, value1, actualName, value1_2 ) );
        expectedError( "Actual '" + actualName + "' is " + Assert_rTypedEquivalent.NOT_EQUAL + " (expected) '" + expectedName + "':"
                       + "\n  expected: " + value1
                       + "\n    actual: " + value2,
                       () -> toCheck.equalNotNull( expectedName, value1, actualName, value2 ) );
    }

    void expectedOK( String expected, Supplier<String> supplier ) {
        expectation.params = null;
        String actual = supplier.get();
        assertEquals( expected, actual );
        assertNull( expectation.params );
    }

    void expectedError( String error, Supplier<String> supplier ) {
        expectation.params = null;
        supplier.get();
        assertEquals( error, expectation.params );
    }
}