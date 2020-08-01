package View;

import Model.TimeFieldType;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class TimeWidget extends HBox {
    private final Button plusButton;
    private final TextField textField;
    private final Button minusButton;
    private TimeFieldType timeFieldType;

    public TimeWidget(TimeFieldType timeFieldType) {
        this(new Button("+"), new TextField("00"), new Button("-"));
        this.textField.setPrefWidth(30);
        this.getChildren().addAll(minusButton, textField, plusButton);
        this.timeFieldType = timeFieldType;
        setPromptText();
        setEventProperties();
    }

    private void setPromptText() {
        switch (timeFieldType) {
            case HOUR -> textField.setPromptText("hh");
            case MINUTE -> textField.setPromptText("mm");
            case SECOND -> textField.setPromptText("ss");
        }
    }

    private void setEventProperties() {
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

    public TimeWidget(Button plusButton, TextField textField, Button minusButton) {
        super();
        this.plusButton = plusButton;
        this.textField = textField;
        this.minusButton = minusButton;
    }

    public int getTextFieldTime() {
        return Integer.parseInt(textField.getText());
    }

    public TextField getTextField() {
        return textField;
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
