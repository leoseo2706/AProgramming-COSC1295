//package core.view;
//
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.layout.GridPane;
//
//public class MainView {
//
//    protected GridPane grdPane;
//
//    public MainView() {
//        super();
//    }
//
//    protected void addAll(GridPane grdPane, Node... nodes) {
//        if (grdPane != null) {
//            int count = 0;
//            for (Node node: nodes) {
//                grdPane.addRow(count, node);
//                count ++;
//            }
//        }
//    }
//
//
//    protected void createAndConfigurePane(int inset) {
//        grdPane = new GridPane();
//        grdPane.setPadding(new Insets(inset, inset, inset, inset));
//        grdPane.setHgap(10);
//        grdPane.setVgap(10);
//        grdPane.setAlignment(Pos.CENTER);
//    }
//
//
//    public Parent asParent() {
//        return grdPane;
//    }
//
//}
//
