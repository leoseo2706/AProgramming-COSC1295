package core.model;

public class Company {

    private String id;
    private String name;
    private String abn;
    private String url;
    private String address;

    public Company(String id, String name, String abn, String url, String address) {
        super();
        this.id = id;
        this.name = name;
        this.abn = abn;
        this.url = url;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbn() {
        return abn;
    }

    public void setAbn(String abn) {
        this.abn = abn;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", abn='" + abn + '\'' +
                ", url='" + url + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
