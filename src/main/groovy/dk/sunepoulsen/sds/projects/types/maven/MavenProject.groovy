//-----------------------------------------------------------------------------
package dk.sunepoulsen.sds.projects.types.maven

import dk.sunepoulsen.sds.projects.SourceProject
import dk.sunepoulsen.sds.projects.SourceVersion
import dk.sunepoulsen.sds.projects.types.api.ProjectType
import dk.sunepoulsen.sds.vcs.api.VCSFile

//-----------------------------------------------------------------------------
/**
 * Implements a Maven project type to analyze Maven projects.
 */
class MavenProject implements ProjectType {
    String getName() {
        return "maven"
    }

    boolean isProject( VCSFile vcsFile ) {
        if( vcsFile == null ) {
            return false
        }
        if( vcsFile.type == VCSFile.VCSFileType.DIRECTORY ) {
            return vcsFile.listFiles().find { it.name == "pom.xml" } != null
        }

        return false
    }

    SourceProject analyze( VCSFile vcsFile ) {
        if( vcsFile == null ) {
            throw new IllegalArgumentException( "vcsFile must not be (null)" )
        }

        return analyseRecursive( null, vcsFile )
    }

    SourceProject analyseRecursive( SourceProject parent, VCSFile vcsFile ) {
        if( vcsFile.type == VCSFile.VCSFileType.FILE ) {
            throw new IllegalArgumentException( "vcsFile must be a directory" )
        }

        VCSFile pomFile = vcsFile.listFiles().find { it.name == "pom.xml" }
        def xml = new XmlSlurper().parseText( pomFile.content )

        SourceProject project = new SourceProject()

        String groupId = xml.groupId
        if( groupId == "" ) {
            groupId = xml.parent.groupId
        }
        String version = xml.version
        if( version == "" ) {
            version = xml.parent.version
        }

        project.name = "${groupId}.${xml.artifactId}"
        project.version = new SourceVersion( version )
        project.parent = parent

        if( xml.packaging.text() == "pom" ) {
            List<VCSFile> subProjectFiles = vcsFile.listFiles().findAll { isProject( it ) }
            project.subProjects = subProjectFiles.collect { analyseRecursive( project, it ) }
        }

        return project
    }
}
