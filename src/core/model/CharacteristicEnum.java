package core.model;

import java.util.*;

public enum CharacteristicEnum {
    DIRECTOR("A", "Likes to be a Leader"),
    SOCIALIZER("B", "Outgoing and maintains good relationships"),
    THINKER("C", "Detail oriented"),
    SUPPORTIVE("D", "Less assertive");

    private String ch;
    private String description;
    private static Map<String, CharacteristicEnum> map;

    static {
        map = new HashMap<>();
        EnumSet.allOf(CharacteristicEnum.class).forEach(c -> map.put(c.ch, c));
    }

    CharacteristicEnum(String ch, String description) {
        this.ch = ch;
        this.description = description;
    }

    public String getCh() {
        return ch;
    }

    public String getDescription() {
        return description;
    }

    public static CharacteristicEnum lookup(String key) {
        return map != null ? map.get(key) : null;
    }

    public static Map<String, CharacteristicEnum> getMap() {
        return map;
    }
}
