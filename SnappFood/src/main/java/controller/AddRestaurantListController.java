package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddRestaurantListController implements Initializable {

    private Stage listStage;
    private String name;
    private String address;
    public void initFunction(Stage listStage , String name , String address){
        this.listStage=listStage;
        this.address=address;
        this.name=name;
    }

    @FXML
    private TableColumn<?, ?> drinkItem;

    @FXML
    private TableColumn<?, ?> drinkPrice;

    @FXML
    private TableView<?> drinkTable;

    @FXML
    private TableColumn<?, ?> fastFoodItem;

    @FXML
    private TableColumn<?, ?> fastFoodPrice;

    @FXML
    private TableView<?> fastFoodTable;

    @FXML
    private TableColumn<?, ?> persianFoodItem;

    @FXML
    private TableColumn<?, ?> persianFoodPrice;

    @FXML
    private TableView<?> persianFoodTable;

    @FXML
    void backHandler(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/addRestaurant.fxml"));
        loader.load();
        AddRestaurantController addRestaurantController = loader.getController();
        listStage.setScene(new Scene((Parent) loader.getRoot()));
        listStage.setTitle("Add Restaurant");
        listStage.setResizable(false);
        addRestaurantController.initFunction(listStage);
        listStage.show();
    }


    @FXML
    void pressDrinkAdd(ActionEvent event) {

    }

    @FXML
    void pressFastFoodAdd(ActionEvent event) {

    }

    @FXML
    void pressPersianFoodAdd(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
