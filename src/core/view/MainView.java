package core.view;

import core.constant.Constants;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainView extends BaseView {

    public MainView() {
        super(10);
        createLayout();
    }

    public void createLayout() {

        for (int i = 1; i <= Constants.MAX_TEAM_NUMBER; i++) {

            VBox teamVB = new VBox();
//            teamVB.setStyle("-fx-background-color: lightblue;");
            teamVB.getStyleClass().add("custom-team-1");
            ObservableList<Node> children = teamVB.getChildren();

            // team label
            Label teamLabel = new Label("Team " + i);
            children.add(teamLabel);

            // team member
            for (int z = 1; z <= Constants.MAX_TEAM_MEMBER; z++) {
                TextField memberName = new TextField();
                CheckBox memberBox = new CheckBox();
                HBox memberHB = new HBox(memberName, memberBox);
                children.add(memberHB);
            }

            addAll(teamVB);
        }

    }

}
