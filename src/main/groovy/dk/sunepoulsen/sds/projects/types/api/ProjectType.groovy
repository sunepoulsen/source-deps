//-----------------------------------------------------------------------------
package dk.sunepoulsen.sds.projects.types.api

import dk.sunepoulsen.sds.projects.SourceProject
import dk.sunepoulsen.sds.vcs.api.VCSFile

//-----------------------------------------------------------------------------
/**
 * Interface that represent the responsibility of a project type.
 */
interface ProjectType {
    /**
     * Returns the name of this project type
     */
    String getName()

    /**
     * Checks if the VCSFile (file or directory) is supported by this project type.
     *
     * @param vcsFile The file or directory we want to check for.
     */
    boolean isProject( VCSFile vcsFile )

    /**
     * Analyze and returns a project from a given VCSFile.
     * <p>
     *     This analysis may results in a project with sub project, in which case the sub projects
     *     should be part of the returned project.
     * </p>
     *
     * @param vcsFile The VCSFile that contains one or more projects.
     *
     * @return The found project.
     */
    SourceProject analyze( VCSFile vcsFile )
}
