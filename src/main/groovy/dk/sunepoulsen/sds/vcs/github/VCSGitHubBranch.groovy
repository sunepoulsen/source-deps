//-----------------------------------------------------------------------------
package dk.sunepoulsen.sds.vcs.github

import dk.sunepoulsen.sds.vcs.api.VCSBranch

//-----------------------------------------------------------------------------
import dk.sunepoulsen.sds.vcs.api.VCSFile
import dk.sunepoulsen.sds.vcs.api.VCSRepository
import org.eclipse.egit.github.core.RepositoryContents
import org.eclipse.egit.github.core.client.GitHubClient

//-----------------------------------------------------------------------------
/**
 * Implements a GitHub branch found in a repository.
 */
class VCSGitHubBranch implements VCSBranch {
    public VCSGitHubBranch( GitHubClient client, VCSRepository repository, String name ) {
        this.client = client
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

    @Override
    List<VCSFile> listFiles() {
        RepositoryContents repositoryContents = new RepositoryContents()
        repositoryContents.setName( "" )
        repositoryContents.setPath( "" )
        repositoryContents.setSize( 0 )
        repositoryContents.setType( RepositoryContents.TYPE_DIR )

        VCSGitHubRepository gitHubRepository = (VCSGitHubRepository)repository
        VCSFile root = new VCSGitHubFile( client, gitHubRepository.getGitHubRepository(), this, repositoryContents )
        return root.listFiles()
    }

    //-------------------------------------------------------------------------
    //              Members
    //-------------------------------------------------------------------------

    private GitHubClient client

    private String name
    private VCSRepository repository
}
