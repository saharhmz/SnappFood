package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Regex;

import java.io.IOException;

public class AddRestaurantController {

    private Stage addRestaurantStage;
    private Regex regex;

    public void initFunction(Stage addRestaurantStage){
        this.addRestaurantStage=addRestaurantStage;
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
        adminPageController.initFunction(addRestaurantStage);
        addRestaurantStage.show();

    }

    @FXML
    void pressAddList(ActionEvent event) throws IOException{
        if(regex.nameRegex(nameFLD.getText()) && regex.addressRegex(addressFLD.getText())){
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/addRestaurantListFirstPage.fxml"));
            loader.load();
            AddRestaurantListFirstPageController addRestaurantListController= loader.getController();
            addRestaurantStage.setScene(new Scene((Parent) loader.getRoot()));
            addRestaurantStage.setTitle("Restaurant List");
            addRestaurantStage.setResizable(false);
            addRestaurantListController.initFunction(addRestaurantStage , nameFLD.getText(),addressFLD.getText());
            addRestaurantStage.show();
        }
        else
            errorLBL.setText("Not Valid");
    }
}
