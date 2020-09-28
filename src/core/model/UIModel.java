package core.model;

public class UIModel {

    private String selectedID;

    public UIModel() {
    }

    public UIModel(String selectedID) {
        this.selectedID = selectedID;
    }

    public String getSelectedID() {
        return selectedID;
    }

    public void setSelectedID(String selectedID) {
        this.selectedID = selectedID;
    }
}
