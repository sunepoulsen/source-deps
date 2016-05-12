//-----------------------------------------------------------------------------
package dk.sunepoulsen.sds.cli

//-----------------------------------------------------------------------------
import dk.sunepoulsen.clt.api.CliException
import dk.sunepoulsen.clt.api.SubCommand
import dk.sunepoulsen.clt.api.SubCommandDefinition
import dk.sunepoulsen.clt.api.SubCommandExecutor
import org.apache.commons.cli.CommandLine
import org.apache.commons.cli.Options
import org.slf4j.ext.XLogger
import org.slf4j.ext.XLoggerFactory

//-----------------------------------------------------------------------------
/**
 * Command to analyze the rdependencies between repositories.
 */
@SubCommand(
        name = "analyze",
        usage = "analyze",
        description = "Analyze dependencies between repositories"
)
class AnalyzeDefinition implements SubCommandDefinition {
    @Override
    public Options createOptions() {
        return new Options();
    }

    @Override
    public SubCommandExecutor createExecutor(final CommandLine line ) throws CliException {
        logger.entry();

        try {
            return new AnalyzeExecutor();
        }
        finally {
            logger.exit();
        }
    }

    //-------------------------------------------------------------------------
    //              Members
    //-------------------------------------------------------------------------

    private static final XLogger logger = XLoggerFactory.getXLogger( ListRepoDefinition.class );
}
