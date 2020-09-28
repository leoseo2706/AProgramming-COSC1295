package core.service;

import core.constant.Constants;
import core.model.*;
import core.utils.Utils;
import core.view.MainView;
import core.view.TeamInfoView;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UIService {

    private UIModel uiModel;
    private MainView view;

    public UIService(UIModel uiModel, MainView view) {
        this.uiModel = uiModel;
        this.view = view;
    }

    public void swapMember() {

        AtomicReference<String> member1AR = new AtomicReference(null);
        AtomicReference<String> member2AR = new AtomicReference(null);
        List<Integer> indexList = uiModel.getSelectedIndexes()
                .values().stream().collect(Collectors.toList());
        int index1 = indexList.get(0);
        int index2 = indexList.get(1);

        List<TeamInfoView> uiList = view.getTeamViewList();
        uiList.forEach(u -> {
            int tmpIdx = u.getTeamMember().getIndex();
            String studentID = u.getTeamMember().getText();
            if (tmpIdx == index1) {
                member1AR.set(studentID);
            } else if (tmpIdx == index2) {
                member2AR.set(studentID);
            }
        });
        String member1 = member1AR.get();
        String member2 = member2AR.get();

        // does not allow blank swap
        if (Utils.isBlank(member1) && Utils.isBlank(member2)) {
            return;
        }

        Map<String, List<FormedMember>> formedTeam = uiModel.getFormedTeam();
        Iterator<Map.Entry<String, List<FormedMember>>> iterator = formedTeam.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, List<FormedMember>> entry = iterator.next();
            List<FormedMember> values = entry.getValue();
            for (int i = 0; i < values.size(); i++) {
                FormedMember value = values.get(i);
                if (value.getIndex() == index1) {
                    if (Utils.isBlank(member2)) {
                        value.setIndex(index2);
                    } else {
                        value.setStudentId(member2);
                    }
                } else if (value.getIndex() == index2) {
                    if (Utils.isBlank(member1)) {
                        value.setIndex(index1);
                    } else {
                        value.setStudentId(member1);
                    }
                }
            }
            formedTeam.put(entry.getKey(), values);
        }

        // UI reflection
        printModelToView(uiList, formedTeam);
    }

    public void formTeam(String studentID) {
        Map<String, Student> studentsMap = uiModel.getAllStudents().stream().collect(Collectors.toMap(
                Student::getId,
                Function.identity(), (o1, o2) -> o1
        ));
        Map<String, List<FormedMember>> formedTeam = uiModel.getFormedTeam();
        FormedMember member = new FormedMember();
        String teamName = null;
        for (Map.Entry<String, Integer> entry : uiModel.getSelectedIndexes().entrySet()) {
            teamName = entry.getKey();
            member.setIndex(entry.getValue());
            member.setStudentId(studentID);
            member.setStudent(studentsMap.get(studentID));
        }

        List<FormedMember> members = formedTeam.get(teamName);
        if (members == null) {
            members = new ArrayList<>();
        }

        members.add(member);
        formedTeam.put(teamName, members);

        //get metrics 1
        TeamSkillCompetency tsc = studentSkillCompetency(formedTeam);

        // assign to UI list
        List<TeamInfoView> uiList = view.getTeamViewList();

        // UI reflection
        printModelToView(uiList, formedTeam);
    }

    public TeamSkillCompetency studentSkillCompetency(Map<String, List<FormedMember>> teamList) {
        if (Utils.isEmpty(teamList)) {
            return null;
        }
        List<Integer> teamsAverage = new ArrayList<Integer>();
        Map<String, Integer> teamSkills = new HashMap<>();

        teamList.forEach((k, v) -> {
            AtomicInteger sumW = new AtomicInteger();
            AtomicInteger sumP = new AtomicInteger();
            AtomicInteger sumN = new AtomicInteger();
            AtomicInteger sumA = new AtomicInteger();
            v.forEach((member) -> {
                sumW.addAndGet(member.getStudent().getGradeW());
                sumA.addAndGet(member.getStudent().getGradeA());
                sumN.addAndGet(member.getStudent().getGradeN());
                sumP.addAndGet(member.getStudent().getGradeP());
            });
            int size = v.size();
            int averageA = sumA.get() / size;
            int averageP = sumP.get() / size;
            int averageN = sumN.get() / size;
            int averageW = sumW.get() / size;
            int teamAverage = averageA + averageP + averageN + averageW;
            teamsAverage.add(teamAverage);
            teamSkills.put(k, teamAverage);
        });
        int standardDeviation = calculateStandardDeviation(teamsAverage);
        TeamSkillCompetency result = new TeamSkillCompetency(teamSkills, standardDeviation);
        return result;
    }

    public SkillShortfall skillShortfall (Map<String, List<FormedMember>> teamList){
        if (Utils.isEmpty(teamList)) {
            return null;
        }
        List<Integer> teamsAverage = new ArrayList<Integer>();
        Map<String, Integer> teamSkills = new HashMap<>();

        teamList.forEach((k, v) -> {
            AtomicInteger sumW = new AtomicInteger();
            AtomicInteger sumP = new AtomicInteger();
            AtomicInteger sumN = new AtomicInteger();
            AtomicInteger sumA = new AtomicInteger();
            v.forEach((member) -> {
                sumW.addAndGet(member.getStudent().getGradeW());
                sumA.addAndGet(member.getStudent().getGradeA());
                sumN.addAndGet(member.getStudent().getGradeN());
                sumP.addAndGet(member.getStudent().getGradeP());
            });
            int size = v.size();
            int averageA = sumA.get() / size;
            int averageP = sumP.get() / size;
            int averageN = sumN.get() / size;
            int averageW = sumW.get() / size;
            int teamAverage = averageA + averageP + averageN + averageW;
            teamsAverage.add(teamAverage);
            teamSkills.put(k, teamAverage);
        });

    }

    private int calculateStandardDeviation(List<Integer> numArray) {
        int sum = 0;
        int standardDeviation = 0;
        int length = numArray.size();

        for (Integer num : numArray) {
            sum += num;
        }

        int mean = sum / length;

        for (Integer num : numArray) {
            standardDeviation += Math.pow(num - mean, 2);
        }

        return (int) Math.sqrt(standardDeviation / length);
    }

    private void printModelToView(List<TeamInfoView> uiList,
                                  Map<String, List<FormedMember>> formedTeam) {
        uiList.forEach(u -> u.getTeamMember().setText(Constants.EMPTY));
        formedTeam.values().stream().flatMap(member -> member.stream()).forEach(m -> {
            uiList.forEach(u -> {
                if (u.getTeamMember().getIndex() == m.getIndex()) {
                    u.getTeamMember().setText(m.getStudentId());
                }
            });
        });
    }

}
