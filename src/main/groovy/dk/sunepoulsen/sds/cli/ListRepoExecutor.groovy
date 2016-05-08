//-----------------------------------------------------------------------------
package dk.sunepoulsen.sds.cli

//-----------------------------------------------------------------------------
import dk.sunepoulsen.clt.api.CliException
import dk.sunepoulsen.clt.api.SubCommandExecutor
import dk.sunepoulsen.sds.vcs.api.VCSRepository
import dk.sunepoulsen.sds.vcs.api.VCSService
import dk.sunepoulsen.sds.vcs.api.VCSServiceFactory
import org.slf4j.ext.XLogger
import org.slf4j.ext.XLoggerFactory

//-----------------------------------------------------------------------------
/**
 * Executor for the ListRepo command.
 * <p>
 *     The syntax of the command 'list-repos' is defined by
 *     {@link dk.sunepoulsen.sds.cli.ListRepoDefinition}.
 * </p>
 */
class ListRepoExecutor implements SubCommandExecutor {
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
            for( VCSRepository repo : service.repositories() ) {
                logger.info( "{}: {}", repo.name, repo.description );
            }

            /*
                GitHubClient client = new GitHubClient();
                client.setOAuth2Token("<oauth-token>");

                RepositoryService service = new RepositoryService( client );
                for (Repository repo : service.getRepositories()) {
                    logger.info( "Name: {}, Private: {}", repo.name, repo.private);
                }

                ContentsService contentsService = new ContentsService( client );
                List<RepositoryContents> contents = contentsService.getContents(service.getRepository("<username>", "<repository-name>"), "<filename>", "<branch-name>");
                for( RepositoryContents repositoryContents : contents ) {
                    logger.info( "" );
                    logger.info( "sunepoulsen/mycash/master/pom.xml:" );
                    logger.info( "====================================" );
                    logger.info( "Name: {}/{}", repositoryContents.path, repositoryContents.name );
                    logger.info( "Encoding: {}", repositoryContents.encoding );
                    logger.info( "SHA: {}", repositoryContents.sha );

                    logger.info( "Contents:\n{}", new String( DatatypeConverter.parseBase64Binary( repositoryContents.content ), StandardCharsets.UTF_8 ) )
                }
            */
        }
        finally {
            logger.exit();
        }
    }

    //-------------------------------------------------------------------------
    //              Members
    //-------------------------------------------------------------------------

    private static final XLogger logger = XLoggerFactory.getXLogger( ListRepoExecutor.class );
}
