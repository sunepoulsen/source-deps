//-----------------------------------------------------------------------------
package dk.sunepoulsen.sds.analysis

//-----------------------------------------------------------------------------
import dk.sunepoulsen.clt.cli.CliApplication
import dk.sunepoulsen.sds.dao.entities.BranchEntity
import dk.sunepoulsen.sds.dao.entities.RepositoryEntity
import dk.sunepoulsen.sds.dao.storage.DataStorage
import dk.sunepoulsen.sds.vcs.api.VCSBranch
import dk.sunepoulsen.sds.vcs.api.VCSRepository
import org.slf4j.ext.XLogger
import org.slf4j.ext.XLoggerFactory

import javax.persistence.EntityManager
import javax.persistence.Query

//-----------------------------------------------------------------------------
/**
 * Created by sunepoulsen on 12/05/16.
 */
class AnalyzeVCSRepository implements Runnable {
    public AnalyzeVCSRepository( VCSRepository repository, DataStorage storage ) {
        this.repository = repository
        this.storage = storage
    }

    @Override
    public void run() {
        try {
            RepositoryEntity entity = storage.find( RepositoryEntity.class ) { EntityManager em ->
                Query query = em.createNamedQuery( "findByName", RepositoryEntity.class )
                query.setParameter( "name", repository.name )
            }

            boolean isNewRepository = false
            if( entity == null ) {
                output.info( "Found new repository: {}", repository.name )
                isNewRepository = true
                entity = new RepositoryEntity( repository.name )
            }

            List<BranchEntity> branchEntities = entity.branches
            if( branchEntities == null ) {
                branchEntities = []
            }

            List<VCSBranch> branches = repository.getBranches()
            if( branches == null ) {
                entity.branches = null
            }
            else {
                entity.branches = branches.collect { vcsBranch ->
                    def branchEntity = branchEntities.find { it -> vcsBranch.name == it.name }
                    if( branchEntity != null ) {
                        return branchEntity
                    }

                    return new BranchEntity( entity, vcsBranch.name )
                }
            }

            storage.persist { EntityManager em -> isNewRepository ? em.persist( entity ) : em.merge( entity ) }
        }
        catch( Exception ex ) {
            output.error( ex.message )
            logger.error( ex.message, ex )
        }
    }

    //-------------------------------------------------------------------------
    //              Members
    //-------------------------------------------------------------------------

    private static final XLogger output = XLoggerFactory.getXLogger( CliApplication.OUTPUT_LOGGER_NAME )
    private static final XLogger logger = XLoggerFactory.getXLogger( AnalyzeVCSService.class )

    private VCSRepository repository
    private DataStorage storage
}
