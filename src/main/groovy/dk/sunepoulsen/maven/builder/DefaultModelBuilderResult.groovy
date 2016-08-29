//-----------------------------------------------------------------------------
package dk.sunepoulsen.maven.builder

//-----------------------------------------------------------------------------
import org.apache.maven.model.Model

//-----------------------------------------------------------------------------
/**
 * Created by sunepoulsen on 19/06/16.
 */
class DefaultModelBuilderResult implements ModelBuilderResult {
    DefaultModelBuilderResult( Model rawModel, Model effectiveModel ) {
        this.effectiveModel = effectiveModel
        this.rawModel = rawModel
    }

    Model getEffectiveModel() {
        return this.effectiveModel
    }

    Model getRawModel() {
        return this.rawModel
    }

    private Model effectiveModel
    private Model rawModel
}
