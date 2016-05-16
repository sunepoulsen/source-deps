//-----------------------------------------------------------------------------
package dk.sunepoulsen.sds.vcs.api

import javax.naming.OperationNotSupportedException
import java.nio.charset.Charset

//-----------------------------------------------------------------------------
/**
 * Represents a file or directory from a VCS.
 */
interface VCSFile {
    //-------------------------------------------------------------------------
    //                  Types
    //-------------------------------------------------------------------------

    enum VCSFileType {
        FILE,
        DIRECTORY
    }

    //-------------------------------------------------------------------------
    //                  Methods
    //-------------------------------------------------------------------------

    /**
     * File or directory name of this VCSFile
     */
    String getName()

    /**
     * Path relative to the root of the branch that contains this VCSFile.
     */
    String getPath()

    /**
     * Contains the type of this VCSFile.
     */
    VCSFile.VCSFileType getType()

    /**
     * Returns the contents of the file.
     * <p>
     *     The content is decoded with StandardCharsets.UTF_8
     * </p>
     *
     * @throws OperationNotSupportedException if this VCSFile is a directory.
     */
    public String getContent() throws OperationNotSupportedException

    /**
     * Returns the contents of the file.
     *
     * @param charset The charset to read the content of the file.
     *
     * @throws OperationNotSupportedException if this VCSFile is a directory.
     */
    public String getContent( Charset charset ) throws OperationNotSupportedException

    /**
     * Returns a list of files and directories in the directory by this VCSFile.
     *
     * @throws OperationNotSupportedException If this VCSFile is a file.
     */
    public List<VCSFile> listFiles() throws OperationNotSupportedException
}
