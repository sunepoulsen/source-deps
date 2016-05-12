//-----------------------------------------------------------------------------
package dk.sunepoulsen.sds.dao.entities

import javax.persistence.*

//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------
/**
 * Entity a for repository from a VCS Service.
 */
@NamedQueries( [
        @NamedQuery( name = "findByName", query = "SELECT r FROM repositories r WHERE r.name = :name" )
])
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
