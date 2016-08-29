//-----------------------------------------------------------------------------
package dk.sunepoulsen.maven.builder

//-----------------------------------------------------------------------------
/**
 * Created by sunepoulsen on 19/06/16.
 */
class DefaultModelBuilderRequest implements ModelBuilderRequest {
    DefaultModelBuilderRequest() {
        this.modelResolver = null
        this.modelSource = null
        this.systemProperties = System.properties
    }

    ModelResolver modelResolver
    ModelSource modelSource
    Properties systemProperties
}
