package View;

import Controller.TimeWidgetController;
import Model.TimeUnit;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * TimeWidget consisting for hour, minutes and seconds text fields with plus, minus buttons per field
 */
public class TimeWidget extends HBox {
    private final Button plusButton;
    private final TextField textField;
    private final Button minusButton;
    private TimeUnit timeUnit;
    private TimeWidgetController timeWidgetController;

    public TimeWidget(TimeUnit timeUnit) {
        this(new Button("+"), new TextField("00"), new Button("-"));
        this.textField.setPrefWidth(30);
        this.getChildren().addAll(minusButton, textField, plusButton);
        this.timeUnit = timeUnit;
        setPromptText();
        this.timeWidgetController = new TimeWidgetController(plusButton, textField, minusButton);
        timeWidgetController.setTimeWidgetEventProperties();
    }

    /**
     * Setting prompt text for text field based on type
     */
    private void setPromptText() {
        switch (timeUnit) {
            case HOUR -> textField.setPromptText("hh");
            case MINUTE -> textField.setPromptText("mm");
            case SECOND -> textField.setPromptText("ss");
        }
    }

    public TimeWidget(Button plusButton, TextField textField, Button minusButton) {
        super();
        this.plusButton = plusButton;
        this.textField = textField;
        this.minusButton = minusButton;
    }

    public int getTextFieldTime() {
        return Integer.parseInt(textField.getText());
    }

    public void setTextFieldTime(int time) {
        if (time < 0) {
            textField.setText("00");
        } else if (time < 10) {
            textField.setText("0".concat(String.valueOf(time)));
        } else {
            textField.setText(String.valueOf(time));
        }
    }
}
