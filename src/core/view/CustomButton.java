package core.view;

import javafx.scene.control.Button;

public class CustomButton extends Button {

    private boolean clicked;
    private int index;

    public CustomButton() {
        super();
        this.clicked = false;
    }

    public CustomButton(int index) {
        super();
        this.index = index;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
