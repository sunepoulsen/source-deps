//-----------------------------------------------------------------------------
package dk.sunepoulsen.sds.dao.entities

//-----------------------------------------------------------------------------
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.slf4j.ext.XLogger
import org.slf4j.ext.XLoggerFactory

import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.EntityTransaction
import javax.persistence.Persistence
import java.sql.SQLException

//-----------------------------------------------------------------------------
class RepositoryEntityTest {
    @Before
    public void setupDatabase() {
        emf = Persistence.createEntityManagerFactory( "source-deps-test" )
    }

    @After
    public void shutdownDatabase() {
        emf.close()
    }

    @Test
    public void createRepository() {
        EntityManager em = emf.createEntityManager()

        try {
            EntityTransaction tx = em.getTransaction()
            try {
                tx.begin()
                em.persist(new RepositoryEntity("mycash"))
                em.persist(new RepositoryEntity("mycash-core"))
                tx.commit()
            }
            catch (SQLException ex) {
                logger.error(ex.message, ex)
                tx.rollback()
                throw ex
            }

            RepositoryEntity entity

            entity = em.find(RepositoryEntity.class, 1)
            assert entity.id == 1
            assert entity.name == "mycash"

            entity = em.find(RepositoryEntity.class, 2)
            assert entity.id == 2
            assert entity.name == "mycash-core"
        }
        finally {
            em.close()
        }
    }

    @Test( expected = javax.persistence.RollbackException.class )
    public void uniqueRepositoryName() {
        EntityManager em = emf.createEntityManager()

        EntityTransaction tx = em.getTransaction()
        try {
            tx.begin()
            em.persist( new RepositoryEntity( "mycash" ) )
            em.persist( new RepositoryEntity( "mycash" ) )
            tx.commit()
        }
        finally {
            em.close()
        }
    }

    //-------------------------------------------------------------------------
    //              Members
    //-------------------------------------------------------------------------

    private static final XLogger logger = XLoggerFactory.getXLogger( RepositoryEntityTest.class );

    EntityManagerFactory emf;
}
