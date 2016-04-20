//-----------------------------------------------------------------------------
package dk.sunepoulsen.sds.cli

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
            logger.debug( "Arguments: {}", args )

            logger.debug( "" )
            logger.debug( "Java System Properties:" )
            logger.debug( "=================================================" )
            for( p in System.properties ) {
                logger.debug( "{} = {}", p.key, p.value )
            }
            logger.debug( "=================================================" )
            logger.debug( "" )

            logger.info( "Hello World from 'sds'" )
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
