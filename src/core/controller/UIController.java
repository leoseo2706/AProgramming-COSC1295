package core.controller;

import core.model.Student;
import core.model.UIModel;
import core.service.UIService;
import core.view.CustomButton;
import core.view.CustomCheckbox;
import core.view.MainView;
import core.view.TeamInfoView;

import java.util.List;

public class UIController {

    private MainView view;
    private UIModel model;
    private UIService service;

    public UIController(MainView view, List<Student> studentModel) {
        this.view = view;
        this.model = new UIModel(studentModel);
        this.service = new UIService(model, view);

        if (view == null) {
            return;
        }

        List<TeamInfoView> teamViewList = view.getTeamViewList();
        teamViewList.forEach(v -> {

            // Set button text and listener
            CustomButton memberButton = v.getTeamMember();
            memberButton.setOnAction(event -> {
                view.addButtonListener(teamViewList, v, memberButton, model);
            });

            // for each member checkbox
            CustomCheckbox memberCB = v.getTeamMemberCB();
            v.getTeamMemberCB().setOnAction(event -> {
                view.addCheckboxListener(teamViewList, v, memberCB, model);
            });

        });

        // add button
        view.getAddButton().setOnAction(event -> {
            if (view.validateSwapListener(model, view.getStudentTF())) {
                service.formTeam(view.getStudentTF().getText());
            }
        });

        // swap button
        view.getSwapButton().setOnAction(event -> {
            if (view.validateSwapListener(model)) {
                service.swapMember();
            }
        });

    }
}
