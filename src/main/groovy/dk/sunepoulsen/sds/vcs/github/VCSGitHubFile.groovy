//-----------------------------------------------------------------------------
package dk.sunepoulsen.sds.vcs.github

//-----------------------------------------------------------------------------
import dk.sunepoulsen.sds.vcs.api.VCSBranch
import dk.sunepoulsen.sds.vcs.api.VCSFile
import org.eclipse.egit.github.core.Repository
import org.eclipse.egit.github.core.RepositoryContents
import org.eclipse.egit.github.core.client.GitHubClient
import org.eclipse.egit.github.core.service.ContentsService

import javax.naming.OperationNotSupportedException
import javax.xml.bind.DatatypeConverter
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

//-----------------------------------------------------------------------------
/**
 * Created by sunepoulsen on 15/05/16.
 */
class VCSGitHubFile implements VCSFile {
    public VCSGitHubFile( GitHubClient client, Repository repository, VCSBranch branch, RepositoryContents repositoryContents ) {
        this.client = client
        this.repository = repository
        this.branch = branch

        this.repositoryContents = repositoryContents

        this.dirCache = null
        this.fileCache = null
    }

    @Override
    String getName() {
        return repositoryContents.name
    }

    @Override
    String getPath() {
        return repositoryContents.path
    }

    @Override
    VCSFile.VCSFileType getType() {
        if( repositoryContents.type == RepositoryContents.TYPE_DIR ) {
            return VCSFile.VCSFileType.DIRECTORY
        }

        return VCSFile.VCSFileType.FILE
    }

    @Override
    String getContent() throws OperationNotSupportedException {
        return getContent( StandardCharsets.UTF_8 )
    }

    @Override
    String getContent( Charset charset ) throws OperationNotSupportedException {
        if( type == VCSFile.VCSFileType.DIRECTORY ) {
            throw new OperationNotSupportedException( "The operation 'getContent' is not allowed on directories" )
        }

        if( fileCache == null ) {
            ContentsService contentsService = new ContentsService( client );
            List<RepositoryContents> contents = contentsService.getContents( repository, getPath(), branch.name );

            RepositoryContents fileContent = contents.find { it.path = getPath() }
            if( fileContent == null ) {
                throw new IOException( "Unable to retrieve content of file '" + getPath() + "' from GitHub" )
            }

            repositoryContents = fileContent
            fileCache = new String( DatatypeConverter.parseBase64Binary( repositoryContents.content ), charset )
        }

        return fileCache
    }

    @Override
    List<VCSFile> listFiles() throws OperationNotSupportedException {
        if( type == VCSFile.VCSFileType.FILE ) {
            throw new OperationNotSupportedException( "The operation 'listFiles' is not allowed on files" )
        }

        if( dirCache == null ) {
            ContentsService contentsService = new ContentsService( client );
            List<RepositoryContents> contents = contentsService.getContents( repository, getPath(), branch.name );

            dirCache = contents.collect { new VCSGitHubFile( client, repository, branch, it ) }
        }

        return dirCache
    }

    //-------------------------------------------------------------------------
    //              Members
    //-------------------------------------------------------------------------

    private GitHubClient client
    private Repository repository
    private VCSBranch branch

    private RepositoryContents repositoryContents

    /**
     * Cache of content if this VCSFile represents a directory on GitHub.
     */
    private List<VCSFile> dirCache

    /**
     * Cache of content if this VCSFile represents a file on GitHub.
     */
    private String fileCache
}
