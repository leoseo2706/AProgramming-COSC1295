package core.view;

import core.constant.Constants;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class MainView extends BaseView {

    private List<TeamInfoView> teamViewList = null;
    private TextField studentTF = null;
    private Button addButton = null;
    private Button swapButton = null;

    public MainView() {
        super(10);
        teamViewList = new ArrayList<>();
        createLayout();
    }

    public void createLayout() {

        int count = 0;
        for (int i = 1; i <= Constants.MAX_TEAM_NUMBER; i++) {

            VBox teamVB = new VBox();
            teamVB.setAlignment(Pos.CENTER);
            teamVB.setSpacing(5);
            teamVB.setPadding(new Insets(5));
            teamVB.getStyleClass().add("custom-team-" + i);
            ObservableList<Node> teamVBChildren = teamVB.getChildren();

            // team label
            String label = "Team " + i;
            Label teamLabel = new Label(label);
            teamVBChildren.add(teamLabel);

            // team member
            for (int z = 1; z <= Constants.MAX_TEAM_MEMBER; z++) {
                CustomButton memberName = new CustomButton();
                memberName.setPrefWidth(100);
                memberName.setAlignment(Pos.CENTER);

                CustomCheckbox memberCB = new CustomCheckbox();
                memberCB.getStyleClass().add("big-check-box");

                HBox memberHB = new HBox(memberName, memberCB);
                memberHB.setSpacing(5);
                memberHB.setAlignment(Pos.CENTER);

                teamVBChildren.add(memberHB);
                teamViewList.add(new TeamInfoView(teamLabel, memberName, memberCB, count));
                count ++;
            }

            this.grdPane.add(teamVB, i - 1, 0);
        }

        // Student label
        Label studentLabel = new Label("Student ID");
        HBox addHB = new HBox(studentLabel);
        addHB.setAlignment(Pos.CENTER_RIGHT);
        addHB.setSpacing(5);

        // Search text field
        studentTF = new TextField();
        studentTF.setPrefWidth(150);

        // Buttons
        addButton = new Button("Add");
        swapButton = new Button("Swap");
        HBox buttonHB = new HBox(addButton, swapButton);
        buttonHB.setAlignment(Pos.CENTER);
        buttonHB.setSpacing(5);

        this.grdPane.addRow(1, addHB, studentTF, buttonHB);

    }

    public List<TeamInfoView> getTeamViewList() {
        return teamViewList;
    }

    public TextField getStudentTF() {
        return studentTF;
    }

    public Button getAddButton() {
        return addButton;
    }

    public Button getSwapButton() {
        return swapButton;
    }
}
