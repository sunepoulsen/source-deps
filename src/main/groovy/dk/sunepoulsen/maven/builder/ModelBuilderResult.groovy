//-----------------------------------------------------------------------------
package dk.sunepoulsen.maven.builder

//-----------------------------------------------------------------------------
import org.apache.maven.model.Model

//-----------------------------------------------------------------------------
/**
 * Collects the output of the model builder.
 */
interface ModelBuilderResult {
    /**
     * Gets the assembled model.
     *
     * @return The assembled model, never null.
     */
    Model getEffectiveModel()

    /**
     * Gets the raw model as it was read from the input model source. Apart from basic validation, the raw
     * model has not undergone any updates by the model builder, e.g. reflects neither inheritance nor
     * interpolation.
     *
     * @return The raw model, never null.
     */
    Model getRawModel()
}
