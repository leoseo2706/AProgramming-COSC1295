package core.service;

import core.model.FormedMember;
import core.model.UIModel;
import core.view.MainView;
import core.view.TeamInfoView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UIService {

    private UIModel uiModel;
    private MainView view;

    public UIService(UIModel uiModel, MainView view) {
        this.uiModel = uiModel;
        this.view = view;
    }

    public void formTeam(String studentID) {

        Map<String, List<FormedMember>> formedTeam = uiModel.getFormedTeam();
        FormedMember member = new FormedMember();
        String teamName = null;
        for (Map.Entry<String, Integer> entry: uiModel.getSelectedIndexes().entrySet()) {
            teamName = entry.getKey();
            member.setIndex(entry.getValue());
            member.setStudentId(studentID);
        }

        List<FormedMember> members = formedTeam.get(teamName);
        if (members == null) {
            members = new ArrayList<>();
        }

        members.add(member);
        formedTeam.put(teamName, members);
        System.out.println("Result: " + uiModel.getFormedTeam());

        // assign to UI list
        List<TeamInfoView> uiList = view.getTeamViewList();


        // UI reflection
        formedTeam.forEach((k, values) -> {
            values.forEach(v -> {
                uiList.forEach(u -> {
                    if (u.getTeamMember().getIndex() == v.getIndex()) {
                        System.out.println("entered " + v.getIndex());
                        u.getTeamMember().setText(v.getStudentId());
//                        u.getTeamMemberCB().setSelected(true);
                    }
                });
            });
        });
    }
}
