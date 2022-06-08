package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class AddCafeListFirstPageController {

    private Stage addListStage;
    private String name;
    private String address;

    public void initFunction(Stage addListStage , String name , String address){
        this.addListStage=addListStage;
        this.name=name;
        this.address=address;
    }

    @FXML
    private TableColumn<?, ?> cakeItem;

    @FXML
    private TableColumn<?, ?> cakePrice;

    @FXML
    private TableView<?> cakeTable;

    @FXML
    private TableColumn<?, ?> coldDrinkItem;

    @FXML
    private TableColumn<?, ?> coldDrinkPrice;

    @FXML
    private TableView<?> coldDrinkTable;

    @FXML
    private TableColumn<?, ?> hotDrinkItem;

    @FXML
    private TableColumn<?, ?> hotDrinkPrice;

    @FXML
    private TableView<?> hotDrinkTable;

    @FXML
    void backHandler(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/addCafe.fxml"));
        loader.load();
        AddCafeController addCafeController = loader.getController();
        addListStage.setScene(new Scene((Parent) loader.getRoot()));
        addListStage.setTitle("Add Cafe");
        addListStage.setResizable(false);
        addCafeController.initFunction(addListStage);
        addListStage.show();
    }


    @FXML
    void pressCakeAdd(ActionEvent event) {

    }

    @FXML
    void pressColdDrinkAdd(ActionEvent event) {

    }

    @FXML
    void pressHotDrinkAdd(ActionEvent event) {

    }
}
