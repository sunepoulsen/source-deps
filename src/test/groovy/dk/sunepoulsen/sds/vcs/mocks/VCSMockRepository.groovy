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
    public VCSMockRepository( String name, String description ) {
        this.name = name
        this.description = description
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
        return branches
    }

    void setBranches( List<VCSBranch> branches ) {
        this.branches = branches
        this.branches.forEach { it.setRepository( this ) }
    }

    //-------------------------------------------------------------------------
    //              Members
    //-------------------------------------------------------------------------

    private String name
    private String description
    private List<VCSBranch> branches
}
