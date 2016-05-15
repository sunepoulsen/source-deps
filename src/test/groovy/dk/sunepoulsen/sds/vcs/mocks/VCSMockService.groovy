//-----------------------------------------------------------------------------
package dk.sunepoulsen.sds.vcs.mocks

import dk.sunepoulsen.sds.vcs.api.VCSException
import dk.sunepoulsen.sds.vcs.api.VCSRepository
import dk.sunepoulsen.sds.vcs.api.VCSService
import org.slf4j.ext.XLogger
import org.slf4j.ext.XLoggerFactory

//-----------------------------------------------------------------------------
/**
 * Created by sunepoulsen on 15/05/16.
 */
class VCSMockService implements VCSService {
    public VCSMockService() {
        this.repositories = null
    }

    public VCSMockService( List<VCSRepository> repositories ) {
        this.repositories = repositories
    }

    @Override
    String type() {
        return "mock"
    }

    @Override
    URI url() {
        return null
    }

    @Override
    List<VCSRepository> repositories() throws VCSException {
        return null
    }

    void setRepositories( List<VCSRepository> repositories ) {
        this.repositories = repositories
    }

    //-------------------------------------------------------------------------
    //              Factories
    //-------------------------------------------------------------------------

    public static VCSMockService newInstance() {
        return new VCSMockService()
    }

    public static VCSMockService newInstance( List repos ) {
        List<VCSRepository> repoResult = []

        repoResult = repos.collect { it ->
            logger.info( "Outer collect: {}", it )

            VCSMockRepository repo = new VCSMockRepository( it.name, it.description )
            repo.branches = []
            repo.branches = it.branches.collect { name ->
                logger.info( "Inner collect: {}", name )

                def branch = new VCSMockBranch( repo, name )
                logger.debug( "Inner adds: {}", branch )
                //repo.branches.add( branch )

                return branch
            }

            logger.debug( "Outer adds: {}", repo )
            //repoResult.add( repo )

            return repo
        }

        return new VCSMockService( repoResult )
    }

    //-------------------------------------------------------------------------
    //              Members
    //-------------------------------------------------------------------------

    private static final XLogger logger = XLoggerFactory.getXLogger( VCSMockService.class )

    List<VCSRepository> repositories
}
