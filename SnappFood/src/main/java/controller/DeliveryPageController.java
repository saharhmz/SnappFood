package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Delivery;

import java.io.IOException;

public class DeliveryPageController {

    private Stage deliveryStage;
    public void initFunction(Stage deliveryStage , Delivery delivery){
        this.deliveryStage=deliveryStage;
    }

    @FXML
    void backHandler(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/loginPage.fxml"));
        loader.load();
        LoginPageController loginPageController= loader.getController();
        deliveryStage.setScene(new Scene((Parent) loader.getRoot()));
        deliveryStage.setTitle("Login");
        deliveryStage.setResizable(false);
        loginPageController.initFunction(deliveryStage);
        deliveryStage.show();
    }

    @FXML
    void pressListCustomer(ActionEvent event) {

    }
}
