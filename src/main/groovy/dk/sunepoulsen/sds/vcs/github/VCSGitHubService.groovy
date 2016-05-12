//-----------------------------------------------------------------------------
package dk.sunepoulsen.sds.vcs.github

//-----------------------------------------------------------------------------
import dk.sunepoulsen.sds.vcs.api.VCSException
import dk.sunepoulsen.sds.vcs.api.VCSRepository
import dk.sunepoulsen.sds.vcs.api.VCSService
import org.eclipse.egit.github.core.Repository
import org.eclipse.egit.github.core.client.GitHubClient
import org.eclipse.egit.github.core.service.RepositoryService

//-----------------------------------------------------------------------------
/**
 * CVSService implementation for accessing GitHub.
 */
class VCSGitHubService implements VCSService {
    public VCSGitHubService( Map settings ) {
        if( !settings.containsKey( "auth" ) ) {
            throw new VCSException( "Settings contains no 'auth' property" )
        }
        if( !settings.auth.containsKey( "token" ) ) {
            throw new VCSException( "Settings contains no 'auth.token' property" )
        }

        client = new GitHubClient();
        client.setOAuth2Token( settings.auth.token );
    }

    @Override
    String type() {
        return "github"
    }

    @Override
    URI url() {
        return new URI( "https://github.com" )
    }

    @Override
    List<VCSRepository> repositories() throws VCSException {
        try {
            List<VCSRepository> result = new ArrayList<>()

            RepositoryService service = new RepositoryService(client);
            for( Repository repo : service.getRepositories() ) {
                result.add( new VCSGitHubRepository( repo ) )
            }

            return result;
        }
        catch( IOException ex ) {
            throw new VCSException( ex.message, ex )
        }
    }

    //-------------------------------------------------------------------------
    //              Members
    //-------------------------------------------------------------------------

    private GitHubClient client;
}
