import View.TaskWidget;
import View.TimeWidget;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class Controller {

    @FXML
    private VBox main_VBox;

    @FXML
    public void initialize() {
        TaskWidget taskWidget = new TaskWidget();
        main_VBox.getChildren().addAll(taskWidget);
    }
}
