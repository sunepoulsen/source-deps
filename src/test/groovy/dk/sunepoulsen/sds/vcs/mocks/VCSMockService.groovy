//-----------------------------------------------------------------------------
package dk.sunepoulsen.sds.vcs.mocks

import dk.sunepoulsen.sds.vcs.api.VCSException
import dk.sunepoulsen.sds.vcs.api.VCSRepository
import dk.sunepoulsen.sds.vcs.api.VCSService
import org.slf4j.ext.XLogger
import org.slf4j.ext.XLoggerFactory

//-----------------------------------------------------------------------------
/**
 * Created by sunepoulsen on 15/05/16.
 */
class VCSMockService implements VCSService {
    public VCSMockService( String testname ) {
        Properties props = new Properties()
        props.load( getClass().getResourceAsStream( "settings.properties" ) )

        this.path = new File( props.getProperty( "test.vcs.directory" ) + "/" + testname )
        this.repositories = null
    }

    @Override
    String type() {
        return "mock"
    }

    @Override
    URI url() {
        return null
    }

    @Override
    List<VCSRepository> repositories() throws VCSException {
        if( repositories == null ) {
            List<File> dirs = path.listFiles().findAll { it.isDirectory() }
            repositories = dirs.collect { new VCSMockRepository( it ) }
        }

        return repositories
    }

    //-------------------------------------------------------------------------
    //              Members
    //-------------------------------------------------------------------------

    private static final XLogger logger = XLoggerFactory.getXLogger( VCSMockService.class )

    private File path
    private List<VCSRepository> repositories

}
