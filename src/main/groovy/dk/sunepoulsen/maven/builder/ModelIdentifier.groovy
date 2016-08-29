//-----------------------------------------------------------------------------
package dk.sunepoulsen.maven.builder

//-----------------------------------------------------------------------------
/**
 * Identifies a model based on group, artifact and version.
 */
class ModelIdentifier {
    public ModelIdentifier( String groupId, String artifactId, String version ) {
        this.groupId = groupId
        this.artifactId = artifactId
        this.version = version
    }

    //-------------------------------------------------------------------------
    //              Members
    //-------------------------------------------------------------------------

    public String groupId;
    public String artifactId;
    public String version;
}
