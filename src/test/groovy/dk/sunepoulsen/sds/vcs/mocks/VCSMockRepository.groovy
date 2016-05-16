//-----------------------------------------------------------------------------
package dk.sunepoulsen.sds.vcs.mocks

//-----------------------------------------------------------------------------
import dk.sunepoulsen.sds.vcs.api.VCSBranch
import dk.sunepoulsen.sds.vcs.api.VCSRepository

//-----------------------------------------------------------------------------
/**
 * Created by sunepoulsen on 15/05/16.
 */
class VCSMockRepository implements VCSRepository {
    public VCSMockRepository( File path ) {
        this.path = path

        this.name = path.name
        this.description = ""
        this.branches = null
    }

    @Override
    String getName() {
        return name
    }

    @Override
    String getDescription() {
        return description
    }

    @Override
    List<VCSBranch> getBranches() {
        if( branches == null ) {
            List<File> dirs = path.listFiles().findAll { it.isDirectory() }
            branches = dirs.collect { new VCSMockBranch( this, it ) }
        }

        return branches
    }

    //-------------------------------------------------------------------------
    //              Members
    //-------------------------------------------------------------------------

    private File path

    private String name
    private String description
    private List<VCSBranch> branches
}
