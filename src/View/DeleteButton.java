package View;

import Controller.DeleteButtonController;
import javafx.scene.control.Button;

/**
 * Delete button to remove a TaskWidget
 */
public class DeleteButton extends Button {

    public DeleteButton() {
        super("Delete");
        this.getStyleClass().add("DeleteButton");
        DeleteButtonController deleteButtonController = new DeleteButtonController(this);
        deleteButtonController.setEventProperty();
    }
}
