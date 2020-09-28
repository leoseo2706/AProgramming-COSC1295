package core.view;

import javafx.scene.control.Button;

public class CustomButton extends Button {

    private boolean clicked;

    public CustomButton() {
        super();
        this.clicked = false;
    }

    public CustomButton(boolean clicked) {
        super();
        this.clicked = clicked;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }
}
