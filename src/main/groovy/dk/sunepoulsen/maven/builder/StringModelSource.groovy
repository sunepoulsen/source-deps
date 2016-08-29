//-----------------------------------------------------------------------------
package dk.sunepoulsen.maven.builder

//-----------------------------------------------------------------------------
/**
 * Implements a ModelSource from a String.
 */
class StringModelSource implements ModelSource {
    public StringModelSource( String data ) {
        this.data = data;
        this.location = location
    }

    Reader getInputStream() {
        return new StringReader( this.data )
    }

    String getLocation() {
        return this.location
    }

    private String data
    private String location
}
