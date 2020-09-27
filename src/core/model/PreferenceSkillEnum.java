package core.model;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum PreferenceSkillEnum {

    MOST(4), SECOND(3), THIRD(2), LEAST(1);
    
    int preference;
    private static Map<Integer, PreferenceSkillEnum> map;
    static {
        map = new HashMap<>();
        EnumSet.allOf(PreferenceSkillEnum.class).forEach(p -> map.put(p.preference, p));
    }

    PreferenceSkillEnum(int preference) {
        this.preference = preference;
    }

    public int getPreference() {
        return preference;
    }

    public static Map<Integer, PreferenceSkillEnum> getMap() {
        return map;
    }

    public static PreferenceSkillEnum lookup(int key) {
        return map != null ? map.get(key) : null;
    }
}
