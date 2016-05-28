//-----------------------------------------------------------------------------
package dk.sunepoulsen.sds.projects

//-----------------------------------------------------------------------------
/**
 * Created by sunepoulsen on 22/05/16.
 */
class SourceProject {
    //-------------------------------------------------------------------------
    //              Constructors
    //-------------------------------------------------------------------------

    SourceProject() {
        this.id = null
        this.name = null
        this.version = null
        this.repositoryName = null
        this.parent = null
        this.subProjects = []
        this.dependencies = []
    }

    //-------------------------------------------------------------------------
    //              Properties
    //-------------------------------------------------------------------------

    /**
     * Unique project identifier.
     */
    String id

    /**
     * A human readable name of the project
     */
    String name

    /**
     * Version number of the project.
     */
    SourceVersion version

    /**
     * Name of the repository that contains the sources of the project.
     */
    String repositoryName

    /**
     * A link to the parent project.
     * <p>
     *     May be null.
     * </p>
     */
    SourceProject parent

    /**
     * List of any sub projects.
     */
    List<SourceProject> subProjects

    /**
     * Dependencies to other projects.
     */
    List<SourceProject> dependencies
}
