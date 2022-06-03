import controller.StartPageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/startPage.fxml"));
        loader.load();
        StartPageController startPageController = loader.getController();
        primaryStage.setScene(new Scene((Parent) loader.getRoot()));
        primaryStage.setTitle("Snapp Food");
        primaryStage.setResizable(false);
        startPageController.initFunction(primaryStage);
        primaryStage.show();

    }
}
