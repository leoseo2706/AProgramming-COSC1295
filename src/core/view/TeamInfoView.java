package core.view;

import javafx.scene.control.Label;

public class TeamInfoView {

    private Label teamName;
    private CustomButton teamMember;
    private CustomCheckbox teamMemberCB;

    public TeamInfoView(Label teamName, CustomButton teamMember,
                        CustomCheckbox teamMemberCB) {
        this.teamName = teamName;
        this.teamMember = teamMember;
        this.teamMemberCB = teamMemberCB;
    }

    public Label getTeamName() {
        return teamName;
    }

    public CustomButton getTeamMember() {
        return teamMember;
    }

    public CustomCheckbox getTeamMemberCB() {
        return teamMemberCB;
    }

}
