package org.litesoft.pragmatics;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConditionalExceptionSwallowerTest {
    @Test
    void shouldEat() {
        ConditionalExceptionSwallower swallower = new ConditionalExceptionSwallower()
                .addMessage( "A" )
                .addMessage( "B" )
                .addMessage( "C" )
                .addMessage( "zy ", " moo" )
                .addType( IllegalStateException.class );

        assertTrue( swallower.shouldEat( new IllegalArgumentException( "A quick brown fox" ) ) );
        assertTrue( swallower.shouldEat( new IllegalArgumentException( "a quick Brown fox" ) ) );
        assertTrue( swallower.shouldEat( new IllegalArgumentException( "a quiCk brown fox" ) ) );
        assertTrue( swallower.shouldEat( new IllegalArgumentException( "fox jumped over the lazy moon" ) ) );

        assertFalse( swallower.shouldEat( new IllegalArgumentException( "a quick brown fox" ) ) );

        assertFalse( swallower.shouldEat( new IllegalArgumentException( "fox jumped over the tall building" ) ) );

        assertTrue( swallower.shouldEat( new IllegalStateException( "fox jumped over the tall building" ) ) );
    }

    @Test
    void checkMessageParts() {
        assertTrue( ConditionalExceptionSwallower.checkMessageParts( "A B C", List.of( "A" ) ) );
        assertTrue( ConditionalExceptionSwallower.checkMessageParts( "A B C", List.of( "B" ) ) );
        assertTrue( ConditionalExceptionSwallower.checkMessageParts( "A B C", List.of( "C" ) ) );
        assertTrue( ConditionalExceptionSwallower.checkMessageParts( "A B C", List.of( "A", "C" ) ) );
        assertTrue( ConditionalExceptionSwallower.checkMessageParts( "A B C", List.of( "A ", " B ", " C" ) ) );
        assertFalse( ConditionalExceptionSwallower.checkMessageParts( "A B C", List.of( "A", "D" ) ) );
    }

    @Test
    void checkClass() {
        assertTrue( ConditionalExceptionSwallower.checkClass( RuntimeException.class, Throwable.class ) );
        assertTrue( ConditionalExceptionSwallower.checkClass( RuntimeException.class, RuntimeException.class ) );
        assertTrue( ConditionalExceptionSwallower.checkClass( Throwable.class, Throwable.class ) );
        assertFalse( ConditionalExceptionSwallower.checkClass( Throwable.class, RuntimeException.class ) );
    }
}