package dk.sunepoulsen.sds.dao.entities

import javax.persistence.*

/**
 * Entity for a project in a branch from a VCS Service.
 */
@Entity( name = "projects" )
class ProjectEntity {
    public ProjectEntity() {
    }

    public ProjectEntity( BranchEntity branch, String name, String version ) {
        this.name = name
        this.version = version
        this.branch = branch
    }

    Integer getId() {
        return id
    }

    void setId( Integer id ) {
        this.id = id
    }

    BranchEntity getBranch() {
        return branch
    }

    void setBranch( BranchEntity branch ) {
        this.branch = branch
    }

    String getName() {
        return name
    }

    void setName( String name ) {
        this.name = name
    }

    String getVersion() {
        return version
    }

    void setVersion( String version ) {
        this.version = version
    }

    //-------------------------------------------------------------------------
    //              Members
    //-------------------------------------------------------------------------

    @Id
    @GeneratedValue
    @Column( name = "project_id" )
    private Integer id;

    @ManyToOne
    @JoinColumn( name = "branch_id", nullable = false )
    private BranchEntity branch;

    @Column( nullable = false )
    private String name;

    @Column( nullable = false )
    private String version;
}
