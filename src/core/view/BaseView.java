package core.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

public class BaseView {

    protected GridPane grdPane;

    public BaseView(int inset) {
        createAndConfigurePane(inset);
    }

    protected void addAll(Node... nodes) {
        if (grdPane != null) {
            int count = 0;
            for (Node node: nodes) {
                grdPane.addRow(count, node);
                count ++;
            }
        }
    }

    protected void createAndConfigurePane(int inset) {
        grdPane = new GridPane();
        grdPane.setPadding(new Insets(inset, inset, inset, inset));
        grdPane.setHgap(10);
        grdPane.setVgap(100);
        grdPane.setAlignment(Pos.CENTER);
    }


    public Parent asParent() {
        return grdPane;
    }

}

