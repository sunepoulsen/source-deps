//-----------------------------------------------------------------------------
package dk.sunepoulsen.sds.projects.types.maven

import dk.sunepoulsen.sds.projects.SourceProject
import dk.sunepoulsen.sds.projects.SourceVersion
import dk.sunepoulsen.sds.vcs.api.VCSBranch
import dk.sunepoulsen.sds.vcs.api.VCSRepository
import dk.sunepoulsen.sds.vcs.api.VCSService
import dk.sunepoulsen.sds.vcs.mocks.VCSMockService
import org.junit.Test

//-----------------------------------------------------------------------------
class MavenProjectTest {
    @Test
    public void testIsProject_Success() {
        VCSService vcs = new VCSMockService( "analyze/projects/maven/single" )

        VCSRepository repo = vcs.repositories().find { it.name == "mycash-core" }
        assert repo != null

        VCSBranch branch = repo.branches.find { it.name == "master" }
        assert branch != null

        MavenProject instance = new MavenProject()
        assert instance.isProject( branch.rootFile() ) == true
    }

    @Test
    public void testIsProject_Null() {
        MavenProject instance = new MavenProject()
        assert instance.isProject( null ) == false
    }

    @Test
    public void testIsProject_DirectoryWithNoPom() {
        VCSService vcs = new VCSMockService( "analyze/projects/maven/single" )

        VCSRepository repo = vcs.repositories().find { it.name == "mycash-core" }
        assert repo != null

        VCSBranch branch = repo.branches.find { it.name == "master" }
        assert branch != null

        MavenProject instance = new MavenProject()
        assert instance.isProject( branch.rootFile().listFiles().find { it.name == "src" } ) == false
    }

    @Test
    public void testIsProject_File() {
        VCSService vcs = new VCSMockService( "analyze/projects/maven/single" )

        VCSRepository repo = vcs.repositories().find { it.name == "mycash-core" }
        assert repo != null

        VCSBranch branch = repo.branches.find { it.name == "master" }
        assert branch != null

        MavenProject instance = new MavenProject()
        assert instance.isProject( branch.rootFile().listFiles().find { it.name == "LICENSE" } ) == false
    }

    @Test
    public void testIsProject_PomFile() {
        VCSService vcs = new VCSMockService( "analyze/projects/maven/single" )

        VCSRepository repo = vcs.repositories().find { it.name == "mycash-core" }
        assert repo != null

        VCSBranch branch = repo.branches.find { it.name == "master" }
        assert branch != null

        MavenProject instance = new MavenProject()
        assert instance.isProject( branch.rootFile().listFiles().find { it.name == "pom.xml" } ) == false
    }

    @Test
    public void testAnalyze_Single_Success() {
        VCSService vcs = new VCSMockService( "analyze/projects/maven/single" )

        VCSRepository repo = vcs.repositories().find { it.name == "mycash-core" }
        assert repo != null

        VCSBranch branch = repo.branches.find { it.name == "master" }
        assert branch != null

        MavenProject instance = new MavenProject()
        SourceProject rootProject = instance.analyze( branch.rootFile() )

        assert rootProject.name == "dk.sunepoulsen.mycash-core"
        assert rootProject.version == new SourceVersion( "1.0.0" )
        assert rootProject.parent == null
        assert rootProject.subProjects == []
    }

    @Test
    public void testAnalyze_SubProjects_Success() {
        VCSService vcs = new VCSMockService( "analyze/projects/maven/subprojects" )

        VCSRepository repo = vcs.repositories().find { it.name == "mycash" }
        assert repo != null

        VCSBranch branch = repo.branches.find { it.name == "master" }
        assert branch != null

        MavenProject instance = new MavenProject()
        SourceProject rootProject = instance.analyze( branch.rootFile() )

        assert rootProject.name == "dk.sunepoulsen.mycash.project"
        assert rootProject.version == new SourceVersion( "1.0.0" )
        assert rootProject.parent == null

        ListIterator<SourceProject> iterator = rootProject.subProjects.listIterator()
        SourceProject project

        project = iterator.next()
        assert project.name == "dk.sunepoulsen.mycash.mycash"
        assert project.version == new SourceVersion( "1.0.0" )
        assert project.parent == rootProject

        project = iterator.next()
        assert project.name == "dk.sunepoulsen.mycash.branding"
        assert project.version == new SourceVersion( "1.0.0" )
        assert project.parent == rootProject

        assert iterator.hasNext() == false
    }
}
