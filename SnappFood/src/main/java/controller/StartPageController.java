package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartPageController {

    private Stage startPageStage;
    public void initFunction(Stage startPageStage){
        this.startPageStage=startPageStage;
    }

    @FXML
    void startHandler(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/loginPage.fxml"));
        loader.load();
        LoginPageController loginPageController = loader.getController();
        startPageStage.setScene(new Scene((Parent) loader.getRoot()));
        startPageStage.setTitle("Login Page");
        startPageStage.setResizable(false);
        loginPageController.initFunction(startPageStage);
        startPageStage.show();
    }
}
