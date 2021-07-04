package ucf.assignments;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class ToDoApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        try {
            URL url = Paths.get("./src/main/resources/ucf.assignments/ToDoApp.fxml").toUri().toURL();
            Parent root = FXMLLoader.load(url);

            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.setTitle("ToDoApp");
            primaryStage.show();

        } catch (IOException e) {
            System.out.println("Error loading stage.");
            e.printStackTrace();
        }
    }
}
