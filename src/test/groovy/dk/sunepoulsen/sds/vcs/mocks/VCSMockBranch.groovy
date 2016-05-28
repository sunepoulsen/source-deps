//-----------------------------------------------------------------------------
package dk.sunepoulsen.sds.vcs.mocks

import dk.sunepoulsen.sds.vcs.api.VCSBranch
import dk.sunepoulsen.sds.vcs.api.VCSFile
import dk.sunepoulsen.sds.vcs.api.VCSRepository

//-----------------------------------------------------------------------------
/**
 * Created by sunepoulsen on 15/05/16.
 */
class VCSMockBranch implements VCSBranch {
    public VCSMockBranch( VCSRepository repository, File path ) {
        this.repository = repository
        this.path = path
    }

    @Override
    String getName() {
        return path.name
    }

    @Override
    VCSRepository getRepository() {
        return repository
    }

    @Override
    VCSFile rootFile() {
        return new VCSMockFile( path )
    }

    @Override
    List<VCSFile> listFiles() {
        return path.listFiles().collect { new VCSMockFile( it ) }
    }

    //-------------------------------------------------------------------------
    //              Members
    //-------------------------------------------------------------------------

    private VCSRepository repository
    private File path
}
