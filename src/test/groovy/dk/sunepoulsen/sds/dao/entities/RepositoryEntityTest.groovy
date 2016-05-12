//-----------------------------------------------------------------------------
package dk.sunepoulsen.sds.dao.entities

import org.junit.After

//-----------------------------------------------------------------------------
import org.junit.Before
import org.junit.Test
import org.slf4j.ext.XLogger
import org.slf4j.ext.XLoggerFactory

import javax.persistence.*
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
    public void create() {
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
    public void uniqueName() {
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

    @Test
    public void find() {
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

            TypedQuery<RepositoryEntity> query = em.createNamedQuery( "findByName", RepositoryEntity.class )
            query.setParameter( "name", "mycash-core" )

            List<RepositoryEntity> list = query.getResultList()
            assert list.size() == 1

            RepositoryEntity entity = list[0]
            assert entity.id == 2
            assert entity.name == "mycash-core"
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
