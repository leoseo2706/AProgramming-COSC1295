package core.model;

public class Project {

    private String id;
    private String title;
    private String description;
    private String ownerId;
    private int skillRankP;
    private int skillRankN;
    private int skillRankA;
    private int skillRankW;

    private int sumPreference;

    public Project(String id, String title, String description, String ownerId,
                   int skillRankP, int skillRankN, int skillRankA, int skillRankW) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.ownerId = ownerId;
        this.skillRankP = skillRankP;
        this.skillRankN = skillRankN;
        this.skillRankA = skillRankA;
        this.skillRankW = skillRankW;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public int getSkillRankP() {
        return skillRankP;
    }

    public void setSkillRankP(int skillRankP) {
        this.skillRankP = skillRankP;
    }

    public int getSkillRankN() {
        return skillRankN;
    }

    public void setSkillRankN(int skillRankN) {
        this.skillRankN = skillRankN;
    }

    public int getSkillRankA() {
        return skillRankA;
    }

    public void setSkillRankA(int skillRankA) {
        this.skillRankA = skillRankA;
    }

    public int getSkillRankW() {
        return skillRankW;
    }

    public void setSkillRankW(int skillRankW) {
        this.skillRankW = skillRankW;
    }

    public int getSumPreference() {
        return sumPreference;
    }

    public void setSumPreference(int sumPreference) {
        this.sumPreference = sumPreference;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", skillRankP=" + skillRankP +
                ", skillRankN=" + skillRankN +
                ", skillRankA=" + skillRankA +
                ", skillRankW=" + skillRankW +
                ", sumPreference=" + sumPreference +
                '}';
    }
}
