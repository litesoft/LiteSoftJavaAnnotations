package org.litesoft.pragmatics.wrappedcheckedexceptions;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.nio.channels.ClosedByInterruptException;
import java.sql.DataTruncation;
import java.util.concurrent.TimeoutException;
import java.util.jar.JarException;
import javax.naming.ConfigurationException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.fail;

class CheckedExceptionWrapperTest {
    @Test
    void _wrap() {
        check( WrappedCheckedException.class, new Exception() ); // nothing matched!
        // Map matches
        check( Config_rtException.class, new ConfigurationException() );
        check( EOF_rtException.class, new EndOfFile_Exception() );
        check( FileNotFound_rtException.class, new FileNotFound_Exception() );
        check( Parse_rtException.class, new ParseDataException() );
        check( SQL_rtException.class, new SQLFormatException() );
        check( Timeout_rtException.class, new TimeoutException() );
        check( Unsupported_rtException.class, new FredUnsupportedException() );
        check( URI_rtException.class, new InternalURI_Exception() );
        check( URI_rtException.class, new InternalURL_Exception() );
        check( XML_rtException.class, new OurXML_Exception() );
        check( XML_rtException.class, new OurSAX_Exception() );
        check( XML_rtException.class, new OurXPathException() );
        check( ZIP_rtException.class, new YourZIP_Exception() );
        check( ZIP_rtException.class, new YourJAR_Exception() );

        check( Config_rtException.class, new TimeoutConfigException() ); // Config before Timeout

        // Hierarchies
        check( SQL_rtException.class, new DataTruncation( 1, false, true, 22, 21 ) );

        check( FileNotFound_rtException.class, new FileNotFoundException() );
        check( EOF_rtException.class, new EOFException() );
        check( ZIP_rtException.class, new JarException() );
        check( ZIP_rtException.class, new JarException() );
        check( URI_rtException.class, new MalformedURLException() );
        check( IO_rtException.class, new ClosedByInterruptException() );
    }

    void check( Class<?> expectedClass, Exception e ) {
        RuntimeException rte = null;
        try {
            CheckedExceptionWrapper.wrap( e );
            fail( "expected exception of type: " + expectedClass.getSimpleName() );
        }
        catch ( RuntimeException re ) {
            rte = re;
        }
        assertSame( expectedClass, rte.getClass() );
    }

    private static class TimeoutConfigException extends Exception {
    }

    private static class EndOfFile_Exception extends Exception {
    }

    private static class FileNotFound_Exception extends Exception {
    }

    private static class ParseDataException extends Exception {
    }

    private static class SQLFormatException extends Exception {
    }

    private static class FredUnsupportedException extends Exception {
    }

    private static class InternalURI_Exception extends Exception {
    }

    private static class InternalURL_Exception extends Exception {
    }

    private static class OurXML_Exception extends Exception {
    }

    private static class OurSAX_Exception extends Exception {
    }

    private static class OurXPathException extends Exception {
    }

    private static class YourZIP_Exception extends Exception {
    }

    private static class YourJAR_Exception extends Exception {
    }
}