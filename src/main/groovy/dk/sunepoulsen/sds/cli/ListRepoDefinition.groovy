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
 * Command to list remote repositories from GitHub.
 */
@SubCommand(
    name = "list-repos",
    usage = "[command name]",
    description = "Returns a list of repositories from GitHub"
)
class ListRepoDefinition implements SubCommandDefinition {
    @Override
    public Options createOptions() {
        return new Options();
    }

    @Override
    public SubCommandExecutor createExecutor( final CommandLine line ) throws CliException {
        logger.entry();

        try {
            return new ListRepoExecutor();
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
