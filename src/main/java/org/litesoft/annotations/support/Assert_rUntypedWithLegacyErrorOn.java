package org.litesoft.annotations.support;

public class Assert_rUntypedWithLegacyErrorOn extends Assert_rUntyped {
    public Assert_rUntypedWithLegacyErrorOn( String pExpectationString, Check_r<Object> pChecker ) {
        super( pExpectationString, pChecker );
    }

    public <T> T errorOn( String pName, T pToCheck ) {
        if ( mChecker.value( pToCheck ) ) {
            return pToCheck;
        }
        throw new Error( "No '" + pName + "' provided -- coding error" );
    }
}
