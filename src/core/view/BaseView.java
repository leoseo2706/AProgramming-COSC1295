package core.view;

import core.constant.Constants;
import core.model.FormedMember;
import core.model.UIModel;
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
import java.util.Map;
import java.util.Set;
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

    public void addButtonListener(List<TeamInfoView> teamViewList,
                                  TeamInfoView appliedView, CustomButton button, UIModel model) {

        if (Utils.isEmpty(teamViewList) || appliedView == null || button == null) {
            return;
        }

        String teamLabel = appliedView.getTeamName().getText();
        List<CustomButton> btnToClear = teamViewList.stream()
                .filter(e -> e.getTeamName().getText().equals(teamLabel)
                        && e.getTeamMember().getIndex() != button.getIndex())
                .map(e -> e.getTeamMember())
                .collect(Collectors.toList());

        List<CustomCheckbox> cbToClear = teamViewList.stream()
                .filter(e -> e.getTeamName().getText().equals(teamLabel)
                        && e.getTeamMemberCB().getIndex() != button.getIndex())
                .map(e -> e.getTeamMemberCB())
                .collect(Collectors.toList());

        CustomCheckbox equivalentCB = teamViewList.stream()
                .filter(e -> e.getTeamMemberCB().getIndex() == button.getIndex())
                .map(e -> e.getTeamMemberCB())
                .findAny().orElse(null);

        // clear all team buttons
        clearAllButtons(btnToClear);
        clearAllCheckbox(cbToClear);

        // toggle button
        if (button.isClicked()) {
            uncheck(equivalentCB);
            dimButton(button);
            model.getSelectedIndexes().remove(teamLabel);
        } else {
            highlightButton(button);
            check(equivalentCB);
            model.getSelectedIndexes().put(teamLabel, button.getIndex());
        }

        // change state
        button.setClicked(!button.isClicked());
    }

    public void addCheckboxListener(List<TeamInfoView> teamViewList,
                                    TeamInfoView appliedView, CustomCheckbox checkbox, UIModel model) {

        if (Utils.isEmpty(teamViewList) || appliedView == null || checkbox == null) {
            return;
        }

        String teamLabel = appliedView.getTeamName().getText();
        List<CustomCheckbox> cbToClear = teamViewList.stream()
                .filter(e -> e.getTeamName().getText().equals(teamLabel)
                        && e.getTeamMemberCB().getIndex() != checkbox.getIndex())
                .map(e -> e.getTeamMemberCB())
                .collect(Collectors.toList());

        List<CustomButton> btnToClear = teamViewList.stream()
                .filter(e -> e.getTeamName().getText().equals(teamLabel)
                        && e.getTeamMember().getIndex() != checkbox.getIndex())
                .map(e -> e.getTeamMember())
                .collect(Collectors.toList());

        CustomButton equivalentBtn = teamViewList.stream()
                .filter(e -> e.getTeamMemberCB().getIndex() == checkbox.getIndex())
                .map(e -> e.getTeamMember())
                .findAny().orElse(null);

        // clear all team buttons
        clearAllButtons(btnToClear);
        clearAllCheckbox(cbToClear);

        // toggle button
        if (!checkbox.isSelected()) {
            dimButton(equivalentBtn);
            model.getSelectedIndexes().remove(teamLabel);
        } else {
            highlightButton(equivalentBtn);
            model.getSelectedIndexes().put(teamLabel, checkbox.getIndex());
        }
        // change state
        equivalentBtn.setClicked(!equivalentBtn.isClicked());
    }

    public boolean validateAddListener(UIModel model, TextField tf) {

        Map<String, Integer> selectedIndexes = model.getSelectedIndexes();
        List<String> ids = model.getAllStudents().stream().map(x -> x.getId()).collect(Collectors.toList());
        int size = selectedIndexes.size();
        if (tf == null || Utils.isBlank(tf.getText())) {
            showErrAlert(Utils.getTextPropSilently(Constants.EMPTY_STUDENT_KEY));
            return false;
        } else if (size == 0) {
            showErrAlert(Utils.getTextPropSilently(Constants.EMPTY_CELL_KEY));
            return false;
        } else if (size > 1) {
            showErrAlert(Utils.getTextPropSilently(Constants.MULTIPLE_CELL_KEY));
            return false;
        } else if (!ids.contains(tf.getText())) {
            showErrAlert(Utils.getTextPropSilently(Constants.INVALID_STUDENT_ID_KEY));
            return false;
        } else if (containFormedMember(model, tf.getText())) {
            showErrAlert(Utils.getTextPropSilently(Constants.EXISTED_STUDENT_ID_KEY));
            return false;
        }

        return true;
    }

    private boolean containFormedMember(UIModel model, String studentId) {

        // only one element inside
        String teamName = model.getSelectedIndexes().keySet()
                .stream().findAny().orElse(Constants.EMPTY);
        int index = model.getSelectedIndexes()
                .values().stream().findAny().orElse(Constants.INVALID);


        // first time
        if (Utils.isEmpty(model.getFormedTeam())) {
            return false;
        }

        boolean flag = model.getFormedTeam().entrySet().stream().flatMap(x -> x.getValue().stream())
                .filter(x -> x.getStudentId().equals(studentId) || x.getIndex() == index)
                .findAny().isPresent();


        return flag;
    }

    public void clearAllCheckbox(List<CustomCheckbox> cbList) {
        if (!Utils.isEmpty(cbList)) {
            cbList.forEach(cb -> {
                uncheck(cb);
            });
        }
    }

    public void clearAllButtons(List<CustomButton> btnList) {
        if (!Utils.isEmpty(btnList)) {
            btnList.forEach(b -> {
                clearButton(b);
            });
        }
    }

    public void clearButton(CustomButton btn) {
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

    public void clearTextField(TextField tf) {
        tf.setText(Constants.EMPTY);
    }


    public String validateTextField(TextField tf) {
        if (tf == null) {
            return null;
        }
        String searchID = tf.getText();
        if (Utils.isBlank(searchID)) {
            showErrAlert(Constants.EMPTY_STUDENT_KEY);
        } else {
            String[] parts = searchID.split(Constants.COMMA);
            return parts.length == 1 ? parts[0] : null;
        }
        return null;
    }


    public Parent asParent() {
        return grdPane;
    }

}

