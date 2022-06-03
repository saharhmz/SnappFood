package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddRestaurantListSecondPageController {

    private Stage addListStage;
    private String name;
    private String address;

    public void initFunction(Stage addListStage, String name , String address){
        this.addListStage=addListStage;
        this.name=name;
        this.address=address;
    }

    @FXML
    private Label errorLBL;

    @FXML
    private TextField itemFDL;

    @FXML
    private TextField priceFDL;

    @FXML
    void backHandler(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/addRestaurantListFirstPage.fxml"));
        loader.load();
        AddRestaurantListFirstPageController addRestaurantListFirstPageController = loader.getController();
        addListStage.setScene(new Scene((Parent) loader.getRoot()));
        addListStage.setTitle("Add List");
        addListStage.setResizable(false);
        addRestaurantListFirstPageController.initFunction(addListStage,name,address);
        addListStage.show();
    }

    @FXML
    void pressAdd(ActionEvent event) {

    }
}
