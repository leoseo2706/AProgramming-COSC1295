package core.view;

import javafx.scene.control.CheckBox;

public class CustomCheckbox extends CheckBox {

    private String label;
    private int index;

    public CustomCheckbox() {
    }

    public CustomCheckbox(int index) {
        this.index = index;
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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
