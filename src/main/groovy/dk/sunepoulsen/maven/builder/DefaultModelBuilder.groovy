//-----------------------------------------------------------------------------
package dk.sunepoulsen.maven.builder

import org.apache.maven.model.Model
import org.apache.maven.model.Parent

//-----------------------------------------------------------------------------
/**
 * Created by sunepoulsen on 01/07/16.
 */
class DefaultModelBuilder implements ModelBuilder {
    DefaultModelBuilder() {
    }

    ModelBuilderResult build( ModelBuilderRequest request ) {
        if( request == null ) {
            throw new IllegalArgumentException( "request argument is required to be not null" )
        }

        if( request.modelSource == null ) {
            throw new IllegalArgumentException( "request.modelSource argument is required to be not null" )
        }

        Reader modelReader = request.modelSource.inputStream
        if( modelReader == null ) {
            throw new IllegalArgumentException( "Model source's input stream must not be null " )
        }

        String xmlContent = modelReader.text
        def xml = new XmlParser().parseText( xmlContent )
        Model rawModel = new Model()

        rawModel.setGroupId( xml.groupId.text() )
        rawModel.setArtifactId( xml.artifactId.text() )
        rawModel.setVersion( xml.version.text() )

        NodeList parentNodes = xml.get( "parent" )
        if( !parentNodes.isEmpty() ) {
            Node parentNode = parentNodes.get( 0 )
            Parent parent = new Parent()

            parent.setGroupId( parentNode.groupId.text() )
            parent.setArtifactId( parentNode.artifactId.text() )
            parent.setVersion( parentNode.version.text() )
            parent.setRelativePath( parentNode.relativePath.text() )

            rawModel.setParent( parent )
        }

        return new DefaultModelBuilderResult( rawModel, rawModel )
    }
}
