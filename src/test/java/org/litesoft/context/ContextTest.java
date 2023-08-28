package org.litesoft.context;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContextTest {

    @Test
    void get() {
        check(new Context( " Hello " ), "Hello");
        check(new Context( () -> " Hello " ), " Hello ");
        check(new Context( () -> null ), null);
        check(new Context( " ! " ).with( () -> null ), "!null");
        check(new Context( " Hello " ).withChild( "Fred" ), "Hello.Fred");
        check(new Context( "root" ).withArrayIndex( 0).withId( "Fred" ), "root[0](Fred)");
    }

    void check(Context pContext, String expected) {
        String actual = pContext.get();
        assertEquals( expected, actual );
    }
}