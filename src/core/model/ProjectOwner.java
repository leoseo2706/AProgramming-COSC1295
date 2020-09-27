package core.model;

public class ProjectOwner {

    private String id;
    private String fName;
    private String sName;
    private String email;
    private String cid;
    private String role;

    public ProjectOwner(String id, String fName, String sName,
                        String role, String email, String cid) {
        super();
        this.id = id;
        this.fName = fName;
        this.sName = sName;
        this.email = email;
        this.cid = cid;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "ProjectOwner{" +
                "id='" + id + '\'' +
                ", fName='" + fName + '\'' +
                ", sName='" + sName + '\'' +
                ", email='" + email + '\'' +
                ", cid='" + cid + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
