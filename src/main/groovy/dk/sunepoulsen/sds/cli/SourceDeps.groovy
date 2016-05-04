//-----------------------------------------------------------------------------
package dk.sunepoulsen.sds.cli

import dk.sunepoulsen.clt.cli.CliApplication

//-----------------------------------------------------------------------------
import org.slf4j.ext.XLogger
import org.slf4j.ext.XLoggerFactory

//-----------------------------------------------------------------------------
/**
 * Implements main function for 'sds' cli application.
 */
class SourceDeps {
    //-------------------------------------------------------------------------
    //              Main function
    //-------------------------------------------------------------------------

    public static void main( String[] args ) {
        logger.entry( args )

        try {
            CliApplication app = new CliApplication( SourceDeps.class.package.name )
            app.main( args )
        }
        finally {
            logger.exit()
        }
    }

    //-------------------------------------------------------------------------
    //              Members
    //-------------------------------------------------------------------------

    private static final XLogger logger = XLoggerFactory.getXLogger( SourceDeps.class )
}
