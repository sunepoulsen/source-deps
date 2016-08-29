//-----------------------------------------------------------------------------
package dk.sunepoulsen.maven.builder

//-----------------------------------------------------------------------------
/**
 * Implements a ModelSource from a file on disk.
 */
class FileModelSource implements ModelSource {
    public FileModelSource( File file ) {
        this.file = file;
    }

    Reader getInputStream() {
        return new FileReader( file )
    }

    String getLocation() {
        return file.canonicalPath
    }

    private File file
}
