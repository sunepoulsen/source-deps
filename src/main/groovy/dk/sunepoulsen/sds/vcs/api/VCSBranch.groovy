//-----------------------------------------------------------------------------
package dk.sunepoulsen.sds.vcs.api

//-----------------------------------------------------------------------------
/**
 * Representation of a branch in a VCS repository.
 */
interface VCSBranch {
    /**
     * Name of the branch.
     * <p>
     *     This property is mandatory for all VCS services.
     * </p>
     */
    String getName()

    /**
     * Reference to the repository that defines this branch.
     */
    VCSRepository getRepository()
}
