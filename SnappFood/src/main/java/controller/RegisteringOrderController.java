package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RegisteringOrderController implements Initializable {

    private Stage registeringOrderStage;
    private Customer customer;
    private Place place;
    private CartCustomerFile cartCustomerFile;

    public void initFunction(Stage registeringOrderStage , Place place , Customer customer){
        placeName.setText(place.getName());
        this.registeringOrderStage=registeringOrderStage;
        this.place= place;
        this.customer= customer;
        cartCustomerFile = new CartCustomerFile();
        ArrayList<Food> foods= new ArrayList<>();
        for (int i=0 ; i < place.getFoodCategories().size() ; i++) {
            for(int j=0 ; j < place.getFoodCategories().get(i).getItems().size() ; j++){
                foods.add(new Food(place.getFoodCategories().get(i).getItems().get(j).getItemName(),
                        place.getFoodCategories().get(i).getItems().get(j).getPrice() ,
                        place.getFoodCategories().get(i).getName()));
            }
        }

        updateTable(foods);


    }

    public void updateTable(ArrayList<Food> foods){
        ObservableList<Food> foodObservableList = FXCollections.observableArrayList(foods);
        listTable.setItems(foodObservableList);
    }

    @FXML
    private TableColumn<Food, String> categoryColumn;

    @FXML
    private Label errorLBL;

    @FXML
    private TableColumn<Food, String> itemColumn;

    @FXML
    private TableView<Food> listTable;

    @FXML
    private Label placeName;

    @FXML
    private TableColumn<Food, String> priceColumn;

    @FXML
    void addHandler(ActionEvent event) {
        Food selectedFood = listTable.getSelectionModel().getSelectedItem();
        if(selectedFood != null){
            cartCustomerFile.addOrder(selectedFood,customer);
        }
        else
            errorLBL.setText("Select a food");
    }

    @FXML
    void backHandler(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/customerPage.fxml"));
        loader.load();
        CustomerPageController customerPageController = loader.getController();
        registeringOrderStage.setScene(new Scene((Parent) loader.getRoot()));
        registeringOrderStage.setTitle("Customer");
        registeringOrderStage.setResizable(false);
        customerPageController.initFunction(registeringOrderStage , customer);
        registeringOrderStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        itemColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}
