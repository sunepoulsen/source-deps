//-----------------------------------------------------------------------------
package dk.sunepoulsen.sds.vcs.mocks

//-----------------------------------------------------------------------------
import dk.sunepoulsen.sds.vcs.api.VCSFile

import javax.naming.OperationNotSupportedException
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

//-----------------------------------------------------------------------------
/**
 * Created by sunepoulsen on 16/05/16.
 */
class VCSMockFile implements VCSFile {
    public VCSMockFile( File file ) {
        this.file = file
    }

    String getName() {
        return file.name
    }

    String getPath() {
        return file.path
    }

    VCSFile.VCSFileType getType() {
        if( file.isDirectory() ) {
            return VCSFile.VCSFileType.DIRECTORY
        }

        return VCSFile.VCSFileType.FILE
    }

    String getContent() throws OperationNotSupportedException {
        return getContent( StandardCharsets.UTF_8 )
    }

    String getContent( Charset charset ) throws OperationNotSupportedException {
        if( type == VCSFile.VCSFileType.DIRECTORY ) {
            throw new OperationNotSupportedException( "The operation 'getContent' is not allowed on directories" )
        }

        return file.getText( charset.name() )
    }

    List<VCSFile> listFiles() throws OperationNotSupportedException {
        return file.listFiles().collect { new VCSMockFile( it ) }
    }

    //-------------------------------------------------------------------------
    //              Members
    //-------------------------------------------------------------------------

    private File file
}
