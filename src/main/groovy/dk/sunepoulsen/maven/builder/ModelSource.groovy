//-----------------------------------------------------------------------------
package dk.sunepoulsen.maven.builder

//-----------------------------------------------------------------------------
/**
 * Provides access to the contents of a POM independently of the backing store (e.g. file system, database, memory).
 */
interface ModelSource {
    /**
     * Gets a byte stream to the POM contents. Closing the returned stream is the responsibility of the caller.
     *
     * @return A byte stream to the POM contents, never <code>null</code>.
     */
    Reader getInputStream()

    /**
     * Provides a user-friendly hint about the location of the POM.
     */
    String getLocation()
}