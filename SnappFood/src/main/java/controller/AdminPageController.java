package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Admin;

import java.io.IOException;

public class AdminPageController {

    private Stage adminStage;
    @FXML
    private Label adminNameLBL;
    private Admin admin;

    public void initFunction(Stage adminStage , Admin admin){
        this.adminStage=adminStage;
        this.admin=admin;
        adminNameLBL.setText(admin.getName()+" "+admin.getFamily());
    }

    @FXML
    void backHandler(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/loginPage.fxml"));
        loader.load();
        LoginPageController loginPageController= loader.getController();
        adminStage.setScene(new Scene((Parent) loader.getRoot()));
        adminStage.setTitle("Login");
        adminStage.setResizable(false);
        loginPageController.initFunction(adminStage);
        adminStage.show();
    }

    @FXML
    void pressAddCafe(ActionEvent event) throws IOException{
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/addCafe.fxml"));
        loader.load();
        AddCafeController addCafeController = loader.getController();
        adminStage.setScene(new Scene((Parent) loader.getRoot()));
        adminStage.setTitle("Add Cafe");
        adminStage.setResizable(false);
        addCafeController.initFunction(adminStage,admin);
        adminStage.show();
    }

    @FXML
    void pressAddRestaurant(ActionEvent event) throws IOException{
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/addRestaurant.fxml"));
        loader.load();
        AddRestaurantController addRestaurantController = loader.getController();
        adminStage.setScene(new Scene((Parent) loader.getRoot()));
        adminStage.setTitle("Add Restaurant");
        adminStage.setResizable(false);
        addRestaurantController.initFunction(adminStage,admin);
        adminStage.show();
    }

}
