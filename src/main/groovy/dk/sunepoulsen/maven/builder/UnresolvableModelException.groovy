package dk.sunepoulsen.maven.builder

class UnresolvableModelException extends Exception {
    public UnresolvableModelException( ModelIdentifier modelIdentifier ) {
        super( "Unable to resolve model ${modelIdentifier.groupId}.${modelIdentifier.artifactId}:${modelIdentifier.version}" )
    }

    public UnresolvableModelException( ModelIdentifier modelIdentifier, Throwable cause ) {
        super( "Unable to resolve model ${modelIdentifier.groupId}.${modelIdentifier.artifactId}:${modelIdentifier.version}", cause )
    }
}
