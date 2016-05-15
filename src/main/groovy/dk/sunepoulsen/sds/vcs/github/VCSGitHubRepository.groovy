//-----------------------------------------------------------------------------
package dk.sunepoulsen.sds.vcs.github

//-----------------------------------------------------------------------------
import dk.sunepoulsen.sds.vcs.api.VCSBranch
import dk.sunepoulsen.sds.vcs.api.VCSRepository
import org.eclipse.egit.github.core.Repository
import org.eclipse.egit.github.core.RepositoryBranch
import org.eclipse.egit.github.core.client.GitHubClient
import org.eclipse.egit.github.core.service.RepositoryService

//-----------------------------------------------------------------------------
/**
 * Implementation of a GitHub repository.
 */
class VCSGitHubRepository implements VCSRepository {
    public VCSGitHubRepository( GitHubClient client, Repository repository ) {
        this.client = client
        this.repository = repository
    }

    @Override
    String getName() {
        return repository.name
    }

    @Override
    String getDescription() {
        return repository.description
    }

    @Override
    List<VCSBranch> getBranches() {
        if( this.branches == null ) {
            this.branches = new ArrayList<>()

            RepositoryService service = new RepositoryService( client )

            List<RepositoryBranch> remoteBranches = service.getBranches( repository )
            if( remoteBranches != null ) {
                for( RepositoryBranch remoteBranch : remoteBranches ) {
                    this.branches.add( new VCSGitHubBranch( this, remoteBranch.name ) )
                }
            }
        }

        return branches
    }

    //-------------------------------------------------------------------------
    //              Members
    //-------------------------------------------------------------------------

    private GitHubClient client
    private Repository repository

    private List<VCSBranch> branches
}
