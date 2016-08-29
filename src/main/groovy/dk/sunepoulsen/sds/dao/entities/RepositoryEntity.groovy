//-----------------------------------------------------------------------------
package dk.sunepoulsen.sds.dao.entities

//-----------------------------------------------------------------------------
import javax.persistence.*

//-----------------------------------------------------------------------------
/**
 * Entity for a repository from a VCS Service.
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

    public Integer getId() {
        return id
    }

    public void setId(Integer id) {
        this.id = id
    }

    public String getName() {
        return name
    }

    public void setName(String name) {
        this.name = name
    }

    public List<BranchEntity> getBranches() {
        return branches
    }

    public void setBranches( List<BranchEntity> branches ) {
        this.branches = branches
    }

    //-------------------------------------------------------------------------
    //              Members
    //-------------------------------------------------------------------------

    @Id
    @GeneratedValue
    @Column( name = "repository_id" )
    private Integer id;

    @Column( unique = true )
    private String name;

    @OneToMany( cascade = CascadeType.ALL, mappedBy = "repository", orphanRemoval = true )
    private List<BranchEntity> branches
}
