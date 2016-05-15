//-----------------------------------------------------------------------------
package dk.sunepoulsen.sds.vcs.mocks

//-----------------------------------------------------------------------------
import dk.sunepoulsen.sds.vcs.api.VCSService
import org.junit.Test

//-----------------------------------------------------------------------------
class VCSMockTest {
    @Test
    public void testMocking() {
        VCSService service = VCSMockService.newInstance( [
                [
                        name: "mycash",
                        description: "",
                        branches: [ "master" ]
                ],
                [
                        name: "mycash-core",
                        description: "",
                        branches: [ "master", "feature/logging" ]
                ]
        ] )

        assert service != null
        assert service.repositories.size() == 2

        assert service.repositories[0].getName() == "mycash"
        assert service.repositories[0].getDescription() == ""
        assert service.repositories[0].branches.size() == 1
        assert service.repositories[0].branches[0].getName() == "master"

        assert service.repositories[1].getName() == "mycash-core"
        assert service.repositories[1].getDescription() == ""
        assert service.repositories[1].branches.size() == 2
        assert service.repositories[1].branches[0].getName() == "master"
        assert service.repositories[1].branches[1].getName() == "feature/logging"
    }
}
