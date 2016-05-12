//-----------------------------------------------------------------------------
package dk.sunepoulsen.sds.analysis

//-----------------------------------------------------------------------------
import dk.sunepoulsen.clt.cli.CliApplication
import dk.sunepoulsen.sds.dao.entities.RepositoryEntity
import dk.sunepoulsen.sds.dao.storage.DataStorage
import dk.sunepoulsen.sds.vcs.api.VCSRepository
import dk.sunepoulsen.sds.vcs.api.VCSService
import org.slf4j.ext.XLogger
import org.slf4j.ext.XLoggerFactory

import javax.persistence.EntityManager
import javax.persistence.Query

//-----------------------------------------------------------------------------
/**
 * Created by sunepoulsen on 12/05/16.
 */
class AnalyzeVCSRepository implements Runnable {
    public AnalyzeVCSRepository(VCSService service, DataStorage storage, VCSRepository repository ) {
        this.service = service
        this.storage = storage
        this.repository = repository
    }

    @Override
    public void run() {
        boolean repoExist = storage.exists { EntityManager em ->
            Query query = em.createNamedQuery( "findByName", RepositoryEntity.class )
            query.setParameter( "name", repository.name )
        }

        if( !repoExist ) {
            output.info( "Found new repository: {}", repository.name )
            storage.persist { EntityManager em -> em.persist( new RepositoryEntity( repository.name ) ) }
        }
    }

    //-------------------------------------------------------------------------
    //              Members
    //-------------------------------------------------------------------------

    private static final XLogger output = XLoggerFactory.getXLogger( CliApplication.OUTPUT_LOGGER_NAME )
    private static final XLogger logger = XLoggerFactory.getXLogger( AnalyzeVCSService.class )

    private VCSService service
    private DataStorage storage
    private VCSRepository repository
}
