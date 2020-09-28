package core.model;

import java.util.Map;

public class SkillShortfall {
    private Map<String, Integer> skillShortfall;

    public SkillShortfall(Map<String, Integer> skillShortfall) {
        this.skillShortfall = skillShortfall;
    }

    public Map<String, Integer> getSkillShortfall() {
        return skillShortfall;
    }

    public void setSkillShortfall(Map<String, Integer> skillShortfall) {
        this.skillShortfall = skillShortfall;
    }
}
