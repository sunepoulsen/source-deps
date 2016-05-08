//-----------------------------------------------------------------------------
package dk.sunepoulsen.sds.vcs.github

//-----------------------------------------------------------------------------
import dk.sunepoulsen.sds.vcs.api.VCSRepository
import org.eclipse.egit.github.core.Repository

//-----------------------------------------------------------------------------
/**
 * Implementation of a GitHub repository.
 */
class VCSGitHubRepository implements VCSRepository {
    public VCSGitHubRepository( Repository repo ) {
        this.name = repo.name
        this.description = repo.description
    }

    String getName() {
        return name
    }

    String getDescription() {
        return description
    }

    //-------------------------------------------------------------------------
    //              Members
    //-------------------------------------------------------------------------

    private String name;
    private String description;
}
