//-----------------------------------------------------------------------------
package dk.sunepoulsen.sds.dao.storage

//-----------------------------------------------------------------------------
import org.slf4j.ext.XLogger
import org.slf4j.ext.XLoggerFactory

import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory
import javax.persistence.EntityTransaction
import javax.persistence.Persistence

//-----------------------------------------------------------------------------
/**
 * Main entry point to the DAO storage of dependencies.
 * <p>
 *     This class contains the connection to the database.
 * </p>
 */
class DataStorage {
    public DataStorage( String persistenceUnitName ) {
        this( Persistence.createEntityManagerFactory( persistenceUnitName ) );
    }

    public DataStorage( EntityManagerFactory emf ) {
        this.emf = emf;
    }

    public void close() {
        this.emf.close()
    }

    public boolean exists( Closure function ) {
        EntityManager em = emf.createEntityManager()

        try {
            def query = function( em )
            return !query.getResultList().isEmpty()
        }
        finally {
            em.close()
        }
    }

    public boolean persist( Closure consumer ) {
        EntityManager em = emf.createEntityManager()

        EntityTransaction tx = em.getTransaction()
        try {
            tx.begin()
            consumer( em )
            tx.commit()
        }
        catch( ex ) {
            logger.warn( "Rollback transaction because of exception.", ex )
            tx.rollback()

            throw ex
        }
        finally {
            em.close()
        }
    }

    //-------------------------------------------------------------------------
    //              Members
    //-------------------------------------------------------------------------

    private static final XLogger logger = XLoggerFactory.getXLogger( DataStorage.class );

    private EntityManagerFactory emf;
}
