import View.TaskWidget;
import View.TimeWidget;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;


public class Controller {

    @FXML
    private VBox main_VBox;

    @FXML
    public void initialize() {
        Button appendTaskButton = new Button("Append a new task");
        main_VBox.getChildren().add(appendTaskButton);

        appendTaskButton.setOnAction(actionEvent -> {
            TaskWidget taskWidget = new TaskWidget();
            main_VBox.getChildren().remove(appendTaskButton);
            main_VBox.getChildren().add(taskWidget);
            main_VBox.getChildren().add(appendTaskButton);
        });



    }
}
