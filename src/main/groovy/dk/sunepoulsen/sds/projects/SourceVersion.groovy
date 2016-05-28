//-----------------------------------------------------------------------------
package dk.sunepoulsen.sds.projects

//-----------------------------------------------------------------------------
/**
 * Created by sunepoulsen on 22/05/16.
 */
class SourceVersion {
    SourceVersion( String version ) {
        this( version, "" )
    }

    SourceVersion( String version, String branchName ) {
        this.version = version
        this.branchName = branchName
    }

    boolean equals( o ) {
        if( this.is( o ) ) {
            return true
        }
        if( getClass() != o.class ) {
            return false
        }

        SourceVersion that = ( SourceVersion ) o

        if( branchName != that.branchName ) {
            return false
        }
        if( version != that.version ) {
            return false
        }

        return true
    }

    int hashCode() {
        int result
        result = ( version != null ? version.hashCode() : 0 )
        result = 31 * result + ( branchName != null ? branchName.hashCode() : 0 )
        return result
    }

    //-------------------------------------------------------------------------
    //              Properties
    //-------------------------------------------------------------------------

    /**
     * Version number of a project.
     * <p>
     *     The version number is required to be unique within each branch.
     * </p>
     */
    String version

    /**
     * Branch name in the repository that contains the sources of this version.
     */
    String branchName
}
