package core.view;

import javafx.scene.control.Label;

public class TeamInfoView {

    private Label teamName;
    private CustomButton teamMember;
    private CustomCheckbox teamMemberCB;
    private int elementIndex;

    public TeamInfoView(Label teamName, CustomButton teamMember,
                        CustomCheckbox teamMemberCB, int elementIndex) {
        this.teamName = teamName;
        this.teamMember = teamMember;
        this.teamMemberCB = teamMemberCB;
        this.elementIndex = elementIndex;
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

    public int getElementIndex() {
        return elementIndex;
    }
}
