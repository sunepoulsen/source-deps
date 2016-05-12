//-----------------------------------------------------------------------------
package dk.sunepoulsen.sds.analysis

import dk.sunepoulsen.clt.cli.CliApplication
import dk.sunepoulsen.sds.dao.storage.DataStorage

//-----------------------------------------------------------------------------
import dk.sunepoulsen.sds.vcs.api.VCSRepository
import dk.sunepoulsen.sds.vcs.api.VCSService
import org.slf4j.ext.XLogger
import org.slf4j.ext.XLoggerFactory

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

//-----------------------------------------------------------------------------
/**
 * Analyze all repositories in a VCS Service in separate treads.
 */
class AnalyzeVCSService {
    public AnalyzeVCSService(VCSService service, DataStorage storage ) {
        this.service = service
        this.storage = storage

        this.pool = Executors.newWorkStealingPool()
        logger.info( "Parallelism level for analyzing pool: {}", pool.getParallelism() )
    }

    void analyze() {
        try {
            List<Future<Runnable>> tasks = new ArrayList<>();

            List<VCSRepository> repositories = service.repositories()
            for( VCSRepository repo : repositories ) {
                tasks.add( pool.submit( new AnalyzeVCSRepository( service, storage, repo ) ) )
            }

            // Wait for analyzing tasks to complete
            boolean tasksCompleted = false
            while( !tasksCompleted ) {
                logger.debug( "Check tasks for completion" )

                tasks.removeIf( { it -> it.done } )
                tasksCompleted = tasks.isEmpty()

                if( !tasksCompleted ) {
                    Thread.sleep( 500 )
                }
            }
        }
        catch( Exception ex ) {
            output.error( ex.getMessage() )
            logger.error( ex.getMessage(), ex )
        }
    }

    //-------------------------------------------------------------------------
    //              Members
    //-------------------------------------------------------------------------

    private static final XLogger output = XLoggerFactory.getXLogger( CliApplication.OUTPUT_LOGGER_NAME )
    private static final XLogger logger = XLoggerFactory.getXLogger( AnalyzeVCSService.class );

    private VCSService service
    private DataStorage storage
    private ExecutorService pool
}
