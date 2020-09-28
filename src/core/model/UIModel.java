package core.model;

import java.util.*;

public class UIModel {

    private Map<String, Integer> selectedIndexes;
    private Map<String, List<FormedMember>> formedTeam;
    private List<Student> allStudents;

    public UIModel(List<Student> allStudents) {
        this.selectedIndexes = new HashMap<>();
        this.formedTeam = new LinkedHashMap<>();
        this.allStudents = allStudents;
    }

    public Map<String, Integer> getSelectedIndexes() {
        return selectedIndexes;
    }

    public Map<String, List<FormedMember>> getFormedTeam() {
        return formedTeam;
    }

    public List<Student> getAllStudents() {
        return allStudents;
    }
}
