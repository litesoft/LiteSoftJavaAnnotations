package org.litesoft.pragmatics.wrappedcheckedexceptions;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.zip.ZipException;

public class CheckedExceptionWrapper {
    public static void wrap( Exception e ) {
        if ( e instanceof SQLException ) {
            throw new SQL_rtException( e );
        }
        if ( e instanceof IOException ) {
            if ( e instanceof FileNotFoundException ) {
                throw new FileNotFound_rtException( e );
            }
            if ( e instanceof EOFException ) {
                throw new EOF_rtException( e );
            }
            if ( e instanceof ZipException ) {
                throw new ZIP_rtException( e );
            }
            checkList( e );
            throw new IO_rtException( e );
        }
        checkList( e );
        throw new WrappedCheckedException( e );
    }

    static class KeyFunction {
        private final Function<Exception, RuntimeException> function;
        private final String[] keys;

        public KeyFunction( Function<Exception, RuntimeException> pFunction, String... pKeys ) {
            function = pFunction;
            keys = pKeys;
        }
    }

    private static void checkList( Exception e ) {
        String name = e.getClass().getSimpleName();
        for ( KeyFunction entry : MAPPER ) {
            for ( String key : entry.keys ) {
                if ( name.contains( key ) ) {
                    throw entry.function.apply( e );
                }
            }
        }
    }

    // every ..._rtException files should be listed below (currently ordered alphabetically, case-insensitive)
    private static final List<KeyFunction> MAPPER = Arrays.asList(
            new KeyFunction( Config_rtException::new, "Config" ),
            new KeyFunction( EOF_rtException::new, "EOF", "EndOfFile" ),
            new KeyFunction( FileNotFound_rtException::new, "FileNotFound" ),
            //  KeyFunction( IO_rtException::new, null ), // Only handled via inheritance
            new KeyFunction( Parse_rtException::new, "Parse" ),
            new KeyFunction( SQL_rtException::new, "SQL" ),
            new KeyFunction( Timeout_rtException::new, "Timeout" ),
            new KeyFunction( Unsupported_rtException::new, "Unsupported" ),
            new KeyFunction( URI_rtException::new, "URI", "URL" ),
            new KeyFunction( XML_rtException::new, "XML", "SAX", "XPath" ),
            new KeyFunction( ZIP_rtException::new, "ZIP", "JAR" )
    );
}
