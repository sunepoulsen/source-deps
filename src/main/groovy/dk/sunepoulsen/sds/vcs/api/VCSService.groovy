//-----------------------------------------------------------------------------
package dk.sunepoulsen.sds.vcs.api

//-----------------------------------------------------------------------------
/**
 * This interface represent a VCS service used as the main access point to a VCS
 * provider.
 * <p>
 *
 * </p>
 */
interface VCSService {
    /**
     * Returns an unique type identifier for this VCS service.
     * <p>
     *     A type identifier can be any string but a good convention will include
     *     a n unique id that identifies the VCS provider that this instance implements.
     * </p>
     * <p>
     *     Examples are: <code>git</code> for a single git server, <code>github</code> for github,
     *     <code>svn</code> for a subversion server, etc.
     * </p>
     */
    String type()

    List<VCSRepository> repositories() throws VCSException
}
