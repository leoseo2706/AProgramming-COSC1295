package core.view;

import core.constant.Constants;
import core.utils.Utils;
import javafx.css.CssMetaData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.util.List;
import java.util.stream.Collectors;

public class BaseView {

    protected GridPane grdPane;

    public BaseView(int inset) {
        createAndConfigurePane(inset);
    }

    protected void addRowAll(Node... nodes) {
        if (grdPane != null) {
            int count = 0;
            for (Node node : nodes) {
                grdPane.addRow(count, node);
                count++;
            }
        }
    }

    protected void addColAll(Node... nodes) {
        if (grdPane != null) {
            int count = 0;
            for (Node node : nodes) {
                grdPane.addColumn(count, node);
                count++;
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

    public void showErrAlert(String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR, content, ButtonType.OK);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.show();
    }

    public void highlightButton(Button button) {
        if (button != null) {
            button.getStyleClass().removeAll(Constants.HIGHLIGHT_CLASS + ", focus");
            button.getStyleClass().add(Constants.HIGHLIGHT_CLASS);
        }
    }

    public void dimButton(Button button) {
        if (button != null) {
            button.getStyleClass().removeAll(Constants.HIGHLIGHT_CLASS);
        }
    }

    public void uncheck(CheckBox cb) {
        if (cb != null) {
            cb.setSelected(false);
        }
    }

    public void check(CheckBox cb) {
        if (cb != null) {
            cb.setSelected(true);
        }
    }

    public boolean containCssClass(String cssClass, Region node) {
        return getActiveClass(node).contains(cssClass);
    }

    public String getActiveClass(Region node) {
        try {
            CssMetaData cssMetaData = node.getCssMetaData().stream()
                    .filter(p -> p.getProperty().equals("-fx-region-background"))
                    .findFirst().orElse(null);
            System.out.println("test: " + cssMetaData.toString());
            return cssMetaData.toString();
        } catch (Exception e) {
            // ignore
        }
        return Constants.EMPTY;
    }

    public void addButtonListener(CustomButton button, TextField textField,
                                  List<TeamInfoView> teamViewList, TeamInfoView appliedView) {

        if (Utils.isEmpty(teamViewList) || appliedView == null
                || button == null || textField == null) {
            return;
        }

        List<CustomButton> btnList = teamViewList.stream()
                .filter(e -> e.getTeamName().getText().equals(appliedView.getTeamName().getText()))
                .map(e -> e.getTeamMember())
                .collect(Collectors.toList());

        List<CustomCheckbox> cbList = teamViewList.stream()
                .filter(e -> e.getTeamName().getText().equals(appliedView.getTeamName().getText()))
                .map(e -> e.getTeamMemberCB())
                .collect(Collectors.toList());

        CustomCheckbox checkBox = teamViewList.stream()
                .filter(e -> e.getTeamMemberCB().getLabel().equals(button.getText()))
                .map(e -> e.getTeamMemberCB())
                .findAny().orElse(null);

        // clear all team buttons
        clearAllButtons(btnList, textField);
        clearAllCheckbox(cbList, textField);

        // toggle button
        if (button.isClicked()) {
            System.out.print("Already clicked. Unclick");
            uncheck(checkBox);
            clearButton(button);
        } else {
            System.out.print("Not clicked. Click");
            highlightButton(button);
            check(checkBox);
        }
        // change state
        setText(textField, teamViewList);
        button.setClicked(!button.isClicked());
        System.out.println(". Setting to " + button.isClicked());
    }

    public void addCheckboxListener(CustomCheckbox checkBox,
                                    TextField textField, List<TeamInfoView> teamViewList,
                                    TeamInfoView appliedView) {

        if (Utils.isEmpty(teamViewList) || appliedView == null
                || checkBox == null || textField == null) {
            return;
        }

        List<CustomCheckbox> cbList = teamViewList.stream()
                .filter(e -> e.getTeamName().getText().equals(appliedView.getTeamName().getText()))
                .map(e -> e.getTeamMemberCB())
                .collect(Collectors.toList());

        List<CustomButton> btnList = teamViewList.stream()
                .filter(e -> e.getTeamName().getText().equals(appliedView.getTeamName().getText()))
                .map(e -> e.getTeamMember())
                .collect(Collectors.toList());

        CustomButton button = teamViewList.stream()
                .filter(e -> e.getTeamMember().getText().equals(checkBox.getLabel()))
                .map(e -> e.getTeamMember())
                .findAny().orElse(null);

        // clear all other buttons
        clearAllCheckbox(cbList, textField);
        clearAllButtons(btnList, textField);

        // toggle button
        if (checkBox.isSelected()) {
            System.out.print("Already clicked. Unclick");
            dimButton(button);
            clearCB(checkBox);
        } else {
            System.out.print("Not clicked. Click");
            highlightButton(button);
            check(checkBox);
        }
        // change state
        setText(textField, teamViewList);
        button.setClicked(!button.isClicked());
        System.out.println(". Setting to " + button.isClicked());
    }

    public void clearAllCheckbox(List<CustomCheckbox> cbList, TextField tf) {
        if (!Utils.isEmpty(cbList) && tf != null) {
            cbList.forEach(cb -> {
                clearCB(cb);
            });
            clearTextField(tf);
        }
    }

    public void clearAllButtons(List<CustomButton> btnList, TextField tf) {
        if (!Utils.isEmpty(btnList) && tf != null) {
            btnList.forEach(b -> {
                clearButton(b);
                System.out.println("cleared " + b.getText());
            });
            clearTextField(tf);
        }
    }


    private void clearCB(CustomCheckbox cb) {
        uncheck(cb);
    }

    private void clearButton(CustomButton btn) {
        dimButton(btn);
        btn.setClicked(false);
    }

    public void setText(TextField tf, List<TeamInfoView> teamViewList) {

        if (tf != null) {
            String textToSet = teamViewList.stream().filter(e -> e.getTeamMemberCB().isSelected())
                    .map(e -> e.getTeamMember().getText()).collect(Collectors.joining(Constants.COMMA));
            tf.setText(textToSet);
        }
    }

    private void clearTextField(TextField tf) {
        tf.setText(Constants.EMPTY);
    }

    public void addCBListener(CheckBox cb) {
        if (cb != null) {

            if (cb.isSelected()) {

            }

        }
    }


    public Parent asParent() {
        return grdPane;
    }

}

