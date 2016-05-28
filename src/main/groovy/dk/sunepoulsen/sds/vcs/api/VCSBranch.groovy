//-----------------------------------------------------------------------------
package dk.sunepoulsen.sds.vcs.api

//-----------------------------------------------------------------------------
/**
 * Representation of a branch, tag or commit in a VCS repository.
 */
interface VCSBranch {
    //-------------------------------------------------------------------------
    //                  Methods
    //-------------------------------------------------------------------------

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

    /**
     * Returns the root directory of this branch.
     */
    VCSFile rootFile()

    /**
     * Returns a list of files and directories in the root of this branch.
     */
    List<VCSFile> listFiles()
}
