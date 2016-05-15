//-----------------------------------------------------------------------------
package dk.sunepoulsen.sds.vcs.mocks

import dk.sunepoulsen.sds.vcs.api.VCSBranch
import dk.sunepoulsen.sds.vcs.api.VCSRepository

//-----------------------------------------------------------------------------
/**
 * Created by sunepoulsen on 15/05/16.
 */
class VCSMockBranch implements VCSBranch {
    public VCSMockBranch( VCSRepository repository, String name ) {
        this.repository = repository
        this.name = name
    }

    @Override
    String getName() {
        return name
    }

    @Override
    VCSRepository getRepository() {
        return repository
    }

    void setRepository( VCSRepository repository ) {
        this.repository = repository
    }

    //-------------------------------------------------------------------------
    //              Members
    //-------------------------------------------------------------------------

    private VCSRepository repository
    private String name
}
