package View;


import javafx.geometry.Insets;
import javafx.scene.control.TextField;

public class CommandWidget extends TextField {

    public CommandWidget() {
        super();
        this.setPromptText("Type your command here");
        this.setMinWidth(300);
    }
}
