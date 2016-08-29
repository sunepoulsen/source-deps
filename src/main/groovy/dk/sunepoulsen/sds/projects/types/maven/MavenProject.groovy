//-----------------------------------------------------------------------------
package dk.sunepoulsen.sds.projects.types.maven

import dk.sunepoulsen.maven.builder.*
import dk.sunepoulsen.sds.projects.SourceProject
import dk.sunepoulsen.sds.projects.SourceVersion
import dk.sunepoulsen.sds.projects.types.api.ProjectType
import dk.sunepoulsen.sds.vcs.api.VCSFile
import org.apache.maven.model.Model

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

        if( vcsFile.type == VCSFile.VCSFileType.FILE ) {
            throw new IllegalArgumentException( "vcsFile must be a directory" )
        }

        VCSFile pomFile = vcsFile.listFiles().find { it.name == "pom.xml" }
        DefaultModelBuilderRequest request = new DefaultModelBuilderRequest()
        request.setModelSource( new StringModelSource( pomFile.content ) )

        ModelBuilder builder = new DefaultModelBuilder()
        ModelBuilderResult modelBuilderResult = builder.build( request )

        Model rawModel = modelBuilderResult.rawModel

        SourceProject project = new SourceProject()
        project.name = rawModel.groupId + "." + rawModel.artifactId
        project.version = new SourceVersion( rawModel.version )

        return project
    }

}
