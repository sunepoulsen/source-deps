//-----------------------------------------------------------------------------
package dk.sunepoulsen.sds.dao.storage

import dk.sunepoulsen.sds.dao.entities.RepositoryEntity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.slf4j.ext.XLogger
import org.slf4j.ext.XLoggerFactory

import javax.persistence.*
import java.sql.SQLException

//-----------------------------------------------------------------------------
class DataStorageTest {
    @Before
    public void setupDatabase() {
        emf = Persistence.createEntityManagerFactory( "source-deps-test" )
    }

    @After
    public void shutdownDatabase() {
        emf.close()
    }

    @Test
    public void testExists_EntityFound() {
        persist( Arrays.asList( new RepositoryEntity("mycash"), new RepositoryEntity("mycash-core") ) )

        DataStorage instance = new DataStorage( emf )
        assert true == instance.exists { EntityManager em ->
            TypedQuery<RepositoryEntity> query = em.createNamedQuery("findByName", RepositoryEntity.class)
            query.setParameter("name", "mycash")
            query.maxResults = 1

            return query
        }
    }

    @Test
    public void testExists_EntityNotFound() {
        persist( Arrays.asList( new RepositoryEntity("mycash"), new RepositoryEntity("mycash-core") ) )

        DataStorage instance = new DataStorage( emf )
        assert false == instance.exists { EntityManager em ->
            TypedQuery<RepositoryEntity> query = em.createNamedQuery("findByName", RepositoryEntity.class)
            query.setParameter("name", "mycash-XX")
            query.maxResults = 1

            return query
        }
    }

    @Test
    public void testPersist_NoException() {
        DataStorage instance = new DataStorage( emf )

        instance.persist { EntityManager em ->
            em.persist( new RepositoryEntity("mycash") )
            em.persist( new RepositoryEntity("mycash-core") )
        }

        EntityManager em = emf.createEntityManager()

        try {
            assert em.find(RepositoryEntity.class, 1) != null
            assert em.find(RepositoryEntity.class, 2) != null
        }
        finally {
            em.close()
        }
    }

    @Test( expected = SQLException.class )
    public void testPersist_WithException() {
        DataStorage instance = new DataStorage( emf )

        def expectedException = new SQLException( "SQL error" )
        try {
            instance.persist { EntityManager em ->
                em.persist(new RepositoryEntity("mycash"))
                em.persist(new RepositoryEntity("mycash-core"))

                throw expectedException
            }

            assert "Expected exception not thrown"
        }
        catch( SQLException ex ) {
            EntityManager em = emf.createEntityManager()

            try {
                assert em.find(RepositoryEntity.class, 1) == null
                assert em.find(RepositoryEntity.class, 2) == null
            }
            finally {
                em.close()
            }

            throw ex
        }
    }

    private <T> void persist( List<T> list ) {
        EntityManager em = emf.createEntityManager()

        try {
            EntityTransaction tx = em.getTransaction()
            try {
                tx.begin()

                for( T v : list ) {
                    em.persist( v )
                }

                tx.commit()
            }
            catch (SQLException ex) {
                logger.error(ex.message, ex)
                tx.rollback()
                throw ex
            }
        }
        finally {
            em.close()
        }

    }

    //-------------------------------------------------------------------------
    //              Members
    //-------------------------------------------------------------------------

    private static final XLogger logger = XLoggerFactory.getXLogger( DataStorageTest.class );

    EntityManagerFactory emf;
}
