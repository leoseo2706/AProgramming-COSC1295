package core.controller;

import core.constant.Constants;
import core.model.Student;
import core.model.UIModel;
import core.utils.Utils;
import core.view.CustomButton;
import core.view.CustomCheckbox;
import core.view.MainView;
import core.view.TeamInfoView;
import javafx.scene.control.CheckBox;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UIController {

    private MainView view;
    private UIModel uiModel;

    public UIController(MainView view, List<Student> studentModel) {
        this.view = view;
        this.uiModel = new UIModel();

        if (view == null || Utils.isEmpty(studentModel)) {
            return;
        }

        List<TeamInfoView> teamViewList = view.getTeamViewList();
        teamViewList.forEach(v -> {

            int studentIndex = v.getElementIndex();
            String studentID = studentModel.get(studentIndex).getId();

            // Set button text and listener
            CustomButton memberButton = v.getTeamMember();
            memberButton.setText(studentID);
            memberButton.setOnAction(event -> {
                view.addButtonListener(memberButton, view.getStudentTF(), teamViewList, v);
            });

            // for each member checkbox
            CustomCheckbox memberCB = v.getTeamMemberCB();
            memberCB.setLabel(studentID);
            v.getTeamMemberCB().setOnAction(event -> {
                view.addCheckboxListener(memberCB, view.getStudentTF(), teamViewList, v);
            });

        });

        // add button
        view.getAddButton().setOnAction(event -> {
            String searchID = view.getStudentTF().getText();
            if (Utils.isBlank(searchID)) {
                view.showErrAlert(Constants.EMPTY_STUDENT_KEY);
            }
        });

        // swap button
        view.getSwapButton().setOnAction(event -> {

        });


    }
}
