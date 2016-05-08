//-----------------------------------------------------------------------------
package dk.sunepoulsen.sds.vcs.api

//-----------------------------------------------------------------------------
/**
 * Representation of a VCS repository found in a VCS service.
 */
interface VCSRepository {
    /**
     * Name of the repository.
     * <p>
     *     This property is mandatory for all VCS services.
     * </p>
     */
    String getName()

    /**
     * Description of the repository.
     * <p>
     *     This property is optional for VCS services.
     * </p>
     * <p>
     *     If the VCS service does not support repository descriptions then
     *     this function must return the empty string.
     * </p>
     */
    String getDescription()
}
