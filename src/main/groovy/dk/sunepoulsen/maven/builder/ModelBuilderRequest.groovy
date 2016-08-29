//-----------------------------------------------------------------------------
package dk.sunepoulsen.maven.builder

//-----------------------------------------------------------------------------
/**
 * Collects settings that control the building of effective models.
 */
interface ModelBuilderRequest {
    /**
     * Gets the model resolver to use for resolution of mixins or parents that are not locally reachable
     * from the project directory.
     */
    ModelResolver getModelResolver()

    /**
     * Sets the model resolver to use for resolution of mixins or parents that are not locally reachable
     * from the project directory.
     *
     * @param modelResolver The model resolver to use, never null.
     */
    void setModelResolver( ModelResolver modelResolver )

    /**
     * Gets the source of the POM to process.
     */
    ModelSource getModelSource()

    /**
     * Sets the source of the POM to process.
     *
     * @param modelSource The source of the POM to process, never null.
     */
    void setModelSource( ModelSource modelSource )

    /**
     * Gets the system properties to use for interpolation and profile activation. The system properties are
     * collected from the runtime environment like System.getProperties() and environment variables.
     */
    Properties getSystemProperties()

    /**
     * Sets the system properties to use for interpolation and profile activation. The system properties are
     * collected from the runtime environment like System.getProperties() and environment variables.
     *
     * @param properties The system properties, may be null.
     */
    void setSystemProperties( Properties properties )
}
