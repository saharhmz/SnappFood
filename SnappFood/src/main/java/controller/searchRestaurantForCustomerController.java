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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class searchRestaurantForCustomerController implements Initializable {

    private Stage searchStage;
    private Customer customer;
    private Regex regex;
    private RestaurantFile restaurantFile;
    private CafeFile cafeFile;
    private ArrayList<Place> places ;

    public void initFunction(Stage searchController , Customer customer){
        this.searchStage =searchController;
        this.customer=customer;
        regex= new Regex();
        restaurantFile=new RestaurantFile();
        cafeFile= new CafeFile();
        places=new ArrayList<>();
    }

    @FXML
    private TableColumn<Place, String> cafeOrRestaurantColumn;

    @FXML
    private Label errorLBL;

    @FXML
    private TextField searchFDL;

    @FXML
    private TableView<Place> searchTable;

    @FXML
    void backHandler(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/customerPage.fxml"));
        loader.load();
        CustomerPageController customerPageController = loader.getController();
        searchStage.setScene(new Scene((Parent) loader.getRoot()));
        searchStage.setTitle("Customer");
        searchStage.setResizable(false);
        customerPageController.initFunction(searchStage , customer);
        searchStage.show();
    }

    @FXML
    void pressSearch(ActionEvent event) {
        errorLBL.setText("");
        if(regex.addressRegex(searchFDL.getText())){

            if(cafeFile.isExist(searchFDL.getText())){
                ArrayList<Cafe> cafes = cafeFile.getAllCafes(searchFDL.getText());
                places.addAll(cafes);
            }
            if(restaurantFile.isExist(searchFDL.getText())){
                ArrayList<Restaurant> restaurants = restaurantFile.getAllRestaurants(searchFDL.getText());
                places.addAll(restaurants);
            }
            updateTable(places);
        }
        else
            errorLBL.setText("Not Valid");
    }

    public void updateTable(ArrayList<Place> places){
        ObservableList<Place> placeObservableList = FXCollections.observableArrayList(places);
        searchTable.setItems(placeObservableList);
    }

    @FXML
    void pressShow(ActionEvent event) throws IOException {
        errorLBL.setText("");
        Place selectedPlace = searchTable.getSelectionModel().getSelectedItem();
        if(selectedPlace != null){
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/registeringOrder.fxml"));
            loader.load();
            RegisteringOrderController registeringOrderController= loader.getController();
            searchStage.setScene(new Scene((Parent) loader.getRoot()));
            searchStage.setTitle("Registering Order");
            searchStage.setResizable(false);
            registeringOrderController.initFunction(searchStage , selectedPlace, customer);
            searchStage.show();
        }
        else
            errorLBL.setText("Select A Place");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cafeOrRestaurantColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    }
}
