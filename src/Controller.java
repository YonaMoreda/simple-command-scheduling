import View.TaskWidget;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import static javafx.geometry.Pos.CENTER_RIGHT;

//TODO:: added stop all or start all button
// and also add label on the top for delete
// maybe update the buttons to be icon only
public class Controller {

    @FXML
    private VBox main_VBox;

    @FXML
    public void initialize() {
        Button appendTaskButton = new Button("Append a new task");
        Button startAllButton = createStartAllButton();
        Button stopAllButton = createStopAllButton();
        Button deleteAllButton = createDeleteAllButton();

        HBox hBox = new HBox(startAllButton, stopAllButton, deleteAllButton);
        hBox.setSpacing(5);
        hBox.setAlignment(CENTER_RIGHT);
        main_VBox.getChildren().add(0, hBox);
        main_VBox.getChildren().add(appendTaskButton);

        appendTaskButton.setOnAction(actionEvent -> {
            TaskWidget taskWidget = new TaskWidget();
            main_VBox.getChildren().remove(appendTaskButton);
            main_VBox.getChildren().add(taskWidget);
            main_VBox.getChildren().add(appendTaskButton);
        });
    }

    private Button createDeleteAllButton() {
        Button deleteAllButton = new Button("Delete All");
        deleteAllButton.setOnAction(actionEvent -> {
            int size = main_VBox.getChildren().size();
            for(int i = 0; i < size; i++) {
                Node node = main_VBox.getChildren().get(i);
                if (node.getClass().getSimpleName().equals("TaskWidget")) {
                    ((TaskWidget) node).getDeleteButton().fire();
                    size--;
                    i--;
                }
            }
        });
        return deleteAllButton;
    }

    private Button createStopAllButton() {
        Button stopAllButton = new Button("Stop All");
        stopAllButton.setOnAction(actionEvent -> {
            for (Node node : main_VBox.getChildren()) {
                if (node.getClass().getSimpleName().equals("TaskWidget")) {
                    ((TaskWidget) node).getStopButton().fire();
                }
            }
        });
        return stopAllButton;
    }

    private Button createStartAllButton() {
        Button startAllButton = new Button("Start All");
        startAllButton.setOnAction(actionEvent -> {
            for (Node node : main_VBox.getChildren()) {
                if (node.getClass().getSimpleName().equals("TaskWidget")) {
                    ((TaskWidget) node).getStartButton().fire();
                }
            }
        });
        return startAllButton;
    }
}
