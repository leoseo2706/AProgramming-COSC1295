package core.model;

import java.util.Map;

public class TeamSkillCompetency {
    private Map<String, Integer> teamSkills;
    private int standardDeviation;

    public TeamSkillCompetency(Map<String, Integer> teamSkills, int standardDeviation) {
        this.teamSkills = teamSkills;
        this.standardDeviation = standardDeviation;
    }

    public Map<String, Integer> getTeamSkills() {
        return teamSkills;
    }

    public void setTeamSkills(Map<String, Integer> teamSkills) {
        this.teamSkills = teamSkills;
    }

    public int getStandardDeviation() {
        return standardDeviation;
    }

    public void setStandardDeviation(int standardDeviation) {
        this.standardDeviation = standardDeviation;
    }
}
