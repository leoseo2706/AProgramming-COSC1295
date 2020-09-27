package core.model;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum GradeEnum {

    HD(4, "High Distinction"), DI(3, "Distinction"),
    CR(2, "Credit"), PA(1, "Pass"), NN(0, "Fail");

    int score;
    String description;
    private static Map<Integer, GradeEnum> map;
    static {
        map = new HashMap<>();
        EnumSet.allOf(GradeEnum.class).forEach(s -> map.put(s.score, s));
    }

    GradeEnum(int score, String description) {
        this.score = score;
        this.description = description;
    }

    public int getScore() {
        return score;
    }

    public String getDescription() {
        return description;
    }

    public static Map<Integer, GradeEnum> getMap() {
        return map;
    }

    public static GradeEnum lookup(int key) {
        return map != null ? map.get(key) : null;
    }
}
