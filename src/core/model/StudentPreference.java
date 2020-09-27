package core.model;

import java.util.HashMap;
import java.util.Map;

public class StudentPreference {

    private String sId;

    private String pId;

    private int score;

    private Map<String, Integer> preferences;

    public StudentPreference(String sId) {
        this.sId = sId;
        preferences = new HashMap<>();
    }

    public StudentPreference(String sId, String pId, int score) {
        this.sId = sId;
        this.pId = pId;
        this.score = score;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Map<String, Integer> getPreferences() {
        return preferences;
    }

    public void setPreferences(Map<String, Integer> preferences) {
        this.preferences = preferences;
    }
}
