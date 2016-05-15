//-----------------------------------------------------------------------------
package dk.sunepoulsen.sds.analysis

import dk.sunepoulsen.sds.dao.entities.BranchEntity
import dk.sunepoulsen.sds.dao.entities.RepositoryEntity
import dk.sunepoulsen.sds.dao.storage.DataStorage

//-----------------------------------------------------------------------------
import dk.sunepoulsen.sds.vcs.api.VCSService
import dk.sunepoulsen.sds.vcs.mocks.VCSMockService
import org.junit.After
import org.junit.Before
import org.junit.Test

import javax.persistence.EntityManager
import javax.persistence.Query

//-----------------------------------------------------------------------------
class AnalyzeVCSRepositoryTest {
    //-------------------------------------------------------------------------
    //              Setup
    //-------------------------------------------------------------------------

    @Before
    public void setupDatabase() {
        storage = new DataStorage( "source-deps-test" )
    }

    @After
    public void shutdownDatabase() {
        storage.close()
    }

    //-------------------------------------------------------------------------
    //              Testing
    //-------------------------------------------------------------------------

    @Test
    public void testNewRepository() {
        VCSService vcs = VCSMockService.newInstance( [
                [
                        name: "mycash",
                        description: "",
                        branches: [ "master" ]
                ]
        ] )

        AnalyzeVCSRepository instance = new AnalyzeVCSRepository( vcs.repositories[0], storage )
        instance.run()

        RepositoryEntity entity = storage.find( RepositoryEntity.class ) { EntityManager em ->
            Query query = em.createNamedQuery( "findByName", RepositoryEntity.class )
            query.setParameter( "name", "mycash" )
        }

        assert entity != null
        assert entity.name == "mycash"
        assert entity.branches.collect{ it.name } == [ "master" ]
    }

    @Test
    public void testUpdateRepository_NewBranch() {
        VCSService vcs = VCSMockService.newInstance( [
                [
                        name: "mycash",
                        description: "",
                        branches: [ "master", "develop" ]
                ]
        ] )

        storage.persist { EntityManager em ->
            RepositoryEntity entity = new RepositoryEntity( "mycash" )
            entity.branches = [
                new BranchEntity( entity, "master" )
            ]

            em.persist( entity )
        }

        AnalyzeVCSRepository instance = new AnalyzeVCSRepository( vcs.repositories[0], storage )
        instance.run()

        RepositoryEntity entity = storage.find( RepositoryEntity.class ) { EntityManager em ->
            Query query = em.createNamedQuery( "findByName", RepositoryEntity.class )
            query.setParameter( "name", "mycash" )
        }

        assert entity != null
        assert entity.name == "mycash"
        assert entity.branches.collect{ it.name } == [ "master", "develop" ]
    }

    @Test
    public void testUpdateRepository_DeleteBranch() {
        VCSService vcs = VCSMockService.newInstance( [
                [
                        name: "mycash",
                        description: "",
                        branches: [ "master" ]
                ]
        ] )

        storage.persist { EntityManager em ->
            RepositoryEntity entity = new RepositoryEntity( "mycash" )
            entity.branches = [
                new BranchEntity( entity, "master" ),
                new BranchEntity( entity, "develop" )
            ]

            em.persist( entity )
        }

        AnalyzeVCSRepository instance = new AnalyzeVCSRepository( vcs.repositories[0], storage )
        instance.run()

        RepositoryEntity entity = storage.find( RepositoryEntity.class ) { EntityManager em ->
            Query query = em.createNamedQuery( "findByName", RepositoryEntity.class )
            query.setParameter( "name", "mycash" )
        }

        assert entity != null
        assert entity.name == "mycash"
        assert entity.branches.collect{ it.name } == [ "master" ]
    }

    //-------------------------------------------------------------------------
    //              Members
    //-------------------------------------------------------------------------

    private DataStorage storage
}
