package Controller;

import View.DeleteButton;
import javafx.scene.layout.VBox;

/**
 * Controller for DeleteButton, for removing appended tasks (TaskWidget(s) from #main_VBox)
 */
public class DeleteButtonController {

    private final DeleteButton deleteButton;

    public DeleteButtonController(DeleteButton deleteButton) {
        this.deleteButton = deleteButton;
    }

    public void setEventProperty() {
        deleteButton.setOnAction(actionEvent -> {
            VBox main_VBox = (VBox) deleteButton.getScene().lookup("#main_VBox");
            main_VBox.getChildren().remove(deleteButton.getParent());
        });
    }
}
