package core.view;

import javafx.scene.control.CheckBox;

public class CustomCheckbox extends CheckBox {

    private String label;

    public CustomCheckbox() {
    }

    public CustomCheckbox(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
