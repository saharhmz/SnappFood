package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Admin;
import model.Regex;

import java.io.IOException;

public class AddRestaurantController {

    private Stage addRestaurantStage;
    private Regex regex;
    private Admin admin;

    public void initFunction(Stage addRestaurantStage , Admin admin){
        this.addRestaurantStage=addRestaurantStage;
        this.admin=admin;
        regex=new Regex();
    }

    @FXML
    private TextField addressFLD;

    @FXML
    private Label errorLBL;

    @FXML
    private TextField nameFLD;

    @FXML
    void backHandler(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/adminPage.fxml"));
        loader.load();
        AdminPageController adminPageController= loader.getController();
        addRestaurantStage.setScene(new Scene((Parent) loader.getRoot()));
        addRestaurantStage.setTitle("Admin");
        addRestaurantStage.setResizable(false);
        adminPageController.initFunction(addRestaurantStage,admin);
        addRestaurantStage.show();

    }

    @FXML
    void pressAddList(ActionEvent event) throws IOException{
        if(regex.nameRegex(nameFLD.getText()) && regex.addressRegex(addressFLD.getText())){
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/addRestaurantList.fxml"));
            loader.load();
            AddRestaurantListController addRestaurantListController= loader.getController();
            addRestaurantStage.setScene(new Scene((Parent) loader.getRoot()));
            addRestaurantStage.setTitle("Restaurant List");
            addRestaurantStage.setResizable(false);
            addRestaurantListController.initFunction(addRestaurantStage , nameFLD.getText(),addressFLD.getText(),admin);
            addRestaurantStage.show();
        }
        else
            errorLBL.setText("Not Valid");
    }
}
