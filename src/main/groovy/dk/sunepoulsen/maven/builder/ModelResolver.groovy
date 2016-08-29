//-----------------------------------------------------------------------------
package dk.sunepoulsen.maven.builder

//-----------------------------------------------------------------------------
/**
 * Resolves a POM from its coordinates. During the build process, the ModelBuilder will add any relevant
 * repositories to the model resolver. In other words, the model resolver is stateful and should not be
 * reused across multiple model building requests.
 */
interface ModelResolver {
    /**
     * Tries to resolve the POM for the specified coordinates.
     *
     * @param modelIdentifier Model identifier.
     *
     * @return The source of the requested POM, never null.
     *
     * @throws UnresolvableModelException If the POM could not be resolved from any configured repository.
     */
    ModelSource resolveModel( ModelIdentifier modelIdentifier ) throws UnresolvableModelException
}
