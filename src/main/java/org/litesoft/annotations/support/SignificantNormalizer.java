package org.litesoft.annotations.support;

public class SignificantNormalizer extends Normalize_r<String> {
    public static final SignificantNormalizer INSTANCE = new SignificantNormalizer();

    private SignificantNormalizer() {
    }

    @Override
    protected String notNullNormalize( String pToCheck ) {
        return valueOr( pToCheck, null );
    }

    public static String valueOr( String pToCheck, String pValueIfNotSignificant ) {
        if ( pToCheck != null ) { // IMO, slightly more efficient than previous version checking isBlank()!
            pToCheck = pToCheck.trim();
            if ( !pToCheck.isEmpty() ) {
                return pToCheck;
            }
        }
        return pValueIfNotSignificant;
    }
}
