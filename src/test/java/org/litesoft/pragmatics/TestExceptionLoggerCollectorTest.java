package org.litesoft.pragmatics;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestExceptionLoggerCollectorTest {
    TestExceptionLoggerCollector ec = new TestExceptionLoggerCollector();

    @Test
    void it() {
        assertFalse( ec.hasEntries() );

        ec.log( new IllegalStateException( "Bad Human Bad" ) );
        ec.log( "Bad Dog Bad" );

        assertTrue( ec.hasEntries() );

        List<String> matched = ec.getEntriesWithMessageContains();
        assertEquals( 0, matched.size() );

        matched = ec.getEntriesWithMessageContains( "Dogs" );
        assertEquals( 0, matched.size() );

        expected( ec.getEntriesWithMessageContains( " Dog " ),
                  ExceptionLogger.NON_EXCEPTION_TYPE + ": Bad Dog Bad" );

        expected( ec.getEntriesWithMessageContains( " Human " ),
                  "IllegalStateException(Bad Human Bad)" );

        expected( ec.getEntriesWithMessageContains( "Bad ", " Bad" ),
                  "IllegalStateException(Bad Human Bad)",
                  ExceptionLogger.NON_EXCEPTION_TYPE + ": Bad Dog Bad" );
    }

    private void expected( List<String> matched, String... expectedEntries ) {
        List<String> expected = Arrays.asList( expectedEntries );
        assertEquals( expected, matched );
    }
}