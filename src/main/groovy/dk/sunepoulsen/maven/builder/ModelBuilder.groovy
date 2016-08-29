//-----------------------------------------------------------------------------
package dk.sunepoulsen.maven.builder

//-----------------------------------------------------------------------------
/**
 * Builds the effective model from a POM.
 */
interface ModelBuilder {
    /**
     * Builds the effective model of the specified POM.
     *
     * @param request The model building request that holds the parameters, must not be null.
     *
     * @return The result of the model building, never null.
     */
    ModelBuilderResult build( ModelBuilderRequest request );
}
