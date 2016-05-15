//-----------------------------------------------------------------------------
package dk.sunepoulsen.sds.vcs.github

//-----------------------------------------------------------------------------
import dk.sunepoulsen.sds.vcs.api.VCSBranch
import dk.sunepoulsen.sds.vcs.api.VCSRepository

//-----------------------------------------------------------------------------
/**
 * Implements a GitHub branch found in a repository.
 */
class VCSGitHubBranch implements VCSBranch {
    public VCSGitHubBranch( VCSRepository repository, String name ) {
        this.name = name
        this.repository = repository
    }

    @Override
    String getName() {
        return name
    }

    @Override
    VCSRepository getRepository() {
        return repository
    }

    //-------------------------------------------------------------------------
    //              Members
    //-------------------------------------------------------------------------

    private String name
    private VCSRepository repository
}
