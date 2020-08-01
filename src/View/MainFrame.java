package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class MainFrame extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("layout.fxml")));
        primaryStage.setTitle("Simple Command Scheduling");
        Scene scene = new Scene(root);
        scene.getStylesheets().add("Styles/DarkMode.css");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("icon.png"));
        primaryStage.show();
    }
}
