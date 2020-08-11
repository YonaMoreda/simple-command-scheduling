package Controller;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * controller for TimeWidget View
 */
public class TimeWidgetController {

    private final Button plusButton;
    private final TextField textField;
    private final Button minusButton;

    public TimeWidgetController(Button plusButton, TextField textField, Button minusButton) {
        this.plusButton = plusButton;
        this.textField = textField;
        this.minusButton = minusButton;
    }

    /**
     * Setting event properties for plus, minus, and textfield elements
     */
    public void setTimeWidgetEventProperties() {
        plusButton.setOnAction(actionEvent -> {
            int number = 0;
            if (!("".equals(textField.getText()))) {
                number = Integer.parseInt(textField.getText());
            }
            if (number + 1 < 10) {
                textField.setText("0".concat(String.valueOf(number + 1)));
            } else {
                textField.setText(String.valueOf(number + 1));
            }
        });

        minusButton.setOnAction(actionEvent -> {
            int number = Integer.parseInt(textField.getText());
            if (number - 1 <= 0) {
                textField.setText("00");
            } else if (number - 1 < 10) {
                textField.setText("0".concat(String.valueOf(number - 1)));
            } else {
                textField.setText(String.valueOf(number - 1));
            }
        });
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }
}
