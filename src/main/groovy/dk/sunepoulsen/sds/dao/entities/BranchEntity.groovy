//-----------------------------------------------------------------------------
package dk.sunepoulsen.sds.dao.entities

import javax.persistence.*

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

    Integer getId() {
        return id
    }

    void setId( Integer id ) {
        this.id = id
    }

    RepositoryEntity getRepository() {
        return repository
    }

    void setRepository( RepositoryEntity repository ) {
        this.repository = repository
    }

    String getName() {
        return name
    }

    void setName( String name ) {
        this.name = name
    }

    List<ProjectEntity> getProjects() {
        return projects
    }

    void setProjects( List<ProjectEntity> projects ) {
        this.projects = projects
    }

    //-------------------------------------------------------------------------
    //              Members
    //-------------------------------------------------------------------------

    @Id
    @GeneratedValue
    @Column( name = "branch_id" )
    private Integer id;

    @ManyToOne
    @JoinColumn( name = "repository_id", nullable = false )
    private RepositoryEntity repository;

    @Column( unique = false )
    private String name;

    @OneToMany( cascade = CascadeType.ALL, mappedBy = "branch", orphanRemoval = true )
    private List<ProjectEntity> projects
}
