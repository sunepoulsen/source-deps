//-----------------------------------------------------------------------------
package dk.sunepoulsen.maven.builder

import org.apache.maven.model.Model
import org.junit.Test

//-----------------------------------------------------------------------------
class DefaultModelBuilderTest {
    @Test
    public void testSettings() {
        Properties props = new Properties()
        props.load( getClass().getResourceAsStream( "settings.properties" ) )

        assert props.getProperty( "test.vcs.directory" ) != ""

        File file = new File( props.getProperty( "test.vcs.directory" ) )
        assert file.exists()
        assert file.isDirectory()
    }

    @Test
    public void testBuildRawModelFromSinglePom() {
        DefaultModelBuilderRequest request = new DefaultModelBuilderRequest()
        request.setModelSource( new FileModelSource( loadPom( "maven/single-project/pom.xml" ) ) )

        ModelBuilder builder = new DefaultModelBuilder()
        ModelBuilderResult modelBuilderResult = builder.build( request )

        Model rawModel = modelBuilderResult.rawModel
        assert rawModel != null
        assert rawModel.groupId == "dk.sunepoulsen"
        assert rawModel.artifactId == "mycash-core"
        assert rawModel.version == "1.0.0"
    }

    private File loadPom( String resourceName ) {
        Properties props = new Properties()
        props.load( getClass().getResourceAsStream( "settings.properties" ) )

        return new File( props.getProperty( "test.vcs.directory" ) + "/" + resourceName )
    }
}
