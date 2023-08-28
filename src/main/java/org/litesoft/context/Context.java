package org.litesoft.context;

import java.util.function.Supplier;

import org.litesoft.annotations.NotNull;
import org.litesoft.annotations.Significant;

public class Context implements Supplier<String> {
    private final Context mPrefix;
    private final Supplier<String> mSupplier;
    private String mRendered;
    private boolean mResolved;

    private Context( Context pPrefix, Supplier<String> pSupplier, String pRendered ) {
        mPrefix = pPrefix;
        mSupplier = pSupplier;
        mRendered = pRendered;
        mResolved = (pRendered != null);
    }

    public Context( Supplier<String> pSupplier ) {
        this( null, NotNull.AssertArgument.namedValue( "Supplier", pSupplier ), null );
    }

    public Context( String pValue ) {
        this( null, null, Significant.AssertArgument.namedValue( "Value", pValue ) );
    }

    public Context with( Supplier<String> pSupplier ) {
        return new Context( this, NotNull.AssertArgument.namedValue( "Supplier", pSupplier ), null );
    }

    public Context withChild( String pValue ) {
        return with( () -> "." + pValue );
    }

    public Context withArrayIndex( int pValue ) {
        return with( () -> "[" + pValue + "]" );
    }

    public Context withId( String pValue ) {
        return with( () -> "(" + pValue + ")" );
    }

    @Override
    public String get() {
        if (!mResolved) {
            mResolved = true;
            mRendered = mSupplier.get();
            if (mPrefix != null) {
                mRendered = mPrefix.get() + mRendered;
            }
        }
        return mRendered;
    }
}
