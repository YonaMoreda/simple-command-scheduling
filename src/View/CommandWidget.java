package View;

import javafx.scene.control.TextField;

/**
 * Text field for receiving a command line command
 */
public class CommandWidget extends TextField {

    public CommandWidget() {
        super();
        this.setPromptText("Type your command here");
        this.getStyleClass().add("CommandWidget");
        this.setMinWidth(300);
    }
}