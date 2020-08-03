package View;

import javafx.scene.control.TextField;

public class CommandWidget extends TextField {

    public CommandWidget() {
        super();
        this.setPromptText("Type your command here");
        this.getStyleClass().add("CommandWidget");
        this.setMinWidth(300);
    }
}