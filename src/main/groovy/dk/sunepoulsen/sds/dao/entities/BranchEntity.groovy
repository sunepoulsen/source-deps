//-----------------------------------------------------------------------------
package dk.sunepoulsen.sds.dao.entities

import javax.persistence.*

//-----------------------------------------------------------------------------
//-----------------------------------------------------------------------------
/**
 * Entity for a branch in a repository from a VCS Service.
 */
@Entity( name = "branches" )
class BranchEntity {
    public BranchEntity() {
    }

    public BranchEntity( RepositoryEntity repository, String name ) {
        this.name = name
        this.repository = repository
    }

    public Integer getId() {
        return id
    }

    public void setId( Integer id ) {
        this.id = id
    }

    public RepositoryEntity getRepository() {
        return repository
    }

    public void setRepository( RepositoryEntity repository ) {
        this.repository = repository
    }

    public String getName() {
        return name
    }

    public void setName( String name ) {
        this.name = name
    }

    //-------------------------------------------------------------------------
    //              Members
    //-------------------------------------------------------------------------

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn( name = "repository_id", nullable = false )
    private RepositoryEntity repository;

    @Column( unique = false )
    private String name;
}
