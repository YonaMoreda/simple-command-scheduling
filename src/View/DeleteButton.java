package View;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class DeleteButton extends Button {

    public DeleteButton() {
        super("Delete");
        this.getStyleClass().add("DeleteButton");
        setEventProperty();
    }

    private void setEventProperty() {
        this.setOnAction(actionEvent -> {
            VBox main_VBox = (VBox) this.getScene().lookup("#main_VBox");
            main_VBox.getChildren().remove(this.getParent());
        });
    }
}
