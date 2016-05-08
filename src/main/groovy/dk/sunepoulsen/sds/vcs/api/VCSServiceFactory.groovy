//-----------------------------------------------------------------------------
package dk.sunepoulsen.sds.vcs.api

import dk.sunepoulsen.sds.vcs.github.VCSGitHubService
import groovy.json.JsonSlurper

//-----------------------------------------------------------------------------
/**
 * Factory class to constructs VCSService instances.
 */
class VCSServiceFactory {
    public static VCSService newInstance( String settingsFilename ) {
        JsonSlurper slurper = new JsonSlurper()
        return newInstance( slurper.parse( new File( settingsFilename ) ) )
    }

    public static VCSService newInstance( Map settings ) {
        if( !settings.containsKey( "vcs" ) ) {
            throw new VCSException( "Settings is missing the 'vcs' property" )
        }

        Map vcs = settings.vcs
        if( !vcs.containsKey( "type" ) ) {
            throw new VCSException( "Settings is missing the 'vcs.type' property" )
        }

        switch( vcs.type ) {
            case "github": return new VCSGitHubService( vcs )

            default:
                throw new VCSException( "The VCS service '${vcs.type}' is not supported" )
        }
    }
}
