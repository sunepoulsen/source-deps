//-----------------------------------------------------------------------------
package dk.sunepoulsen.sds.dao.entities

//-----------------------------------------------------------------------------
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

//-----------------------------------------------------------------------------
/**
 * Entity a for repository from a VCS Service.
 */
@Entity( name = "repositories" )
class RepositoryEntity {
    public RepositoryEntity() {
    }

    public RepositoryEntity( String name ) {
        this.name = name
    }

    Integer getId() {
        return id
    }

    void setId(Integer id) {
        this.id = id
    }

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    //-------------------------------------------------------------------------
    //              Members
    //-------------------------------------------------------------------------

    @Id
    @GeneratedValue
    private Integer id;

    @Column( unique = true )
    private String name;
}
