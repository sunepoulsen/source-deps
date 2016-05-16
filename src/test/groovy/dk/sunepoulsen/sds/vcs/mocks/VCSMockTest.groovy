//-----------------------------------------------------------------------------
package dk.sunepoulsen.sds.vcs.mocks

import dk.sunepoulsen.sds.vcs.api.VCSBranch
import dk.sunepoulsen.sds.vcs.api.VCSFile
import dk.sunepoulsen.sds.vcs.api.VCSRepository
import dk.sunepoulsen.sds.vcs.api.VCSService

//-----------------------------------------------------------------------------
import org.junit.Test

//-----------------------------------------------------------------------------
class VCSMockTest {
    @Test
    public void testSettings() {
        Properties props = new Properties()
        props.load( getClass().getResourceAsStream( "settings.properties" ) )

        assert props.getProperty( "test.vcs.directory" ) != ""

        File file = new File( props.getProperty( "test.vcs.directory" ) )
        assert file.exists()
        assert file.isDirectory()
    }

    @Test
    public void testMocking() {
        VCSService service = new VCSMockService( "mocking" )

        assert service != null
        assert service.repositories().size() == 2

        assert service.repositories()[0].getName() == "mycash"
        assert service.repositories()[0].getDescription() == ""
        assert service.repositories()[0].branches.size() == 1
        assert service.repositories()[0].branches[0].getName() == "master"

        assert service.repositories()[1].getName() == "mycash-core"
        assert service.repositories()[1].getDescription() == ""
        assert service.repositories()[1].branches.size() == 2
        assert service.repositories()[1].branches[0].getName() == "feature-logging"
        assert service.repositories()[1].branches[1].getName() == "master"
    }

    @Test
    public void testReadPomFile() {
        VCSService service = new VCSMockService( "mocking" )

        VCSRepository repo = service.repositories().find { it.name == "mycash" }
        assert repo != null

        VCSBranch branch = repo.branches.find { it.name == "master" }
        assert branch != null

        VCSFile pomFile = branch.listFiles().find { it.name == "pom.xml" }
        assert pomFile != null

        assert pomFile.name == "pom.xml"
        assert pomFile.type == VCSFile.VCSFileType.FILE

        def xml = new XmlSlurper().parseText( pomFile.content )

        assert xml instanceof groovy.util.slurpersupport.GPathResult
        assert xml.groupId == "dk.sunepoulsen.mycash"
        assert xml.artifactId == "project"
        assert xml.version == "1.0.0"
        assert xml.wrongNode == ""
        assert xml.wrongParent.wrongChild == ""

        assert xml.properties instanceof groovy.util.slurpersupport.GPathResult
        assert xml.properties.children().size() == 5
        assert xml.properties.children()[0].name() == "project.build.sourceEncoding"
    }
}
