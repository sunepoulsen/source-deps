//-----------------------------------------------------------------------------
package dk.sunepoulsen.sds.cli


//-----------------------------------------------------------------------------
import dk.sunepoulsen.clt.api.CliException
import dk.sunepoulsen.clt.api.SubCommandExecutor
import dk.sunepoulsen.clt.cli.CliApplication
import dk.sunepoulsen.sds.analysis.AnalyzeVCSService
import dk.sunepoulsen.sds.dao.storage.DataStorage
import dk.sunepoulsen.sds.vcs.api.VCSService
import dk.sunepoulsen.sds.vcs.api.VCSServiceFactory
import org.slf4j.ext.XLogger
import org.slf4j.ext.XLoggerFactory

//-----------------------------------------------------------------------------
/**
 * Command to list remote repositories from GitHub.
 */
class AnalyzeExecutor implements SubCommandExecutor {
    @Override
    public void validateArguments() throws CliException {
    }

    @Override
    public void performAction() throws CliException {
        logger.entry();

        try {
            String settingsFilename = CliConstants.SETTINGS_FILENAME
            if( !new File( settingsFilename ).exists() ) {
                throw new CliException( "The settings file '${settingsFilename}' does not exist" )
            }

            VCSService service = VCSServiceFactory.newInstance( settingsFilename )
            output.info( "Analyzing dependencies from VCS: {}", service.url() )

            DataStorage storage = new DataStorage( "source-deps" )
            try {
                AnalyzeVCSService analyzer = new AnalyzeVCSService(service, storage)
                analyzer.analyze()
            }
            finally {
                storage.close()
            }
        }
        finally {
            logger.exit();
        }
    }

    //-------------------------------------------------------------------------
    //              Members
    //-------------------------------------------------------------------------

    private static final XLogger logger = XLoggerFactory.getXLogger( ListRepoExecutor.class );
    private static final XLogger output = XLoggerFactory.getXLogger( CliApplication.OUTPUT_LOGGER_NAME );
}
