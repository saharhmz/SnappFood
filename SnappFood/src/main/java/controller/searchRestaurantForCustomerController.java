package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Customer;

import java.io.IOException;

public class searchRestaurantForCustomerController
{
    private Stage SearchController;
    private Customer customer;
    public void initFunction(Stage SearchController , Customer customer){
        this.SearchController=SearchController;
        this.customer=customer;
    }
    @FXML
    private TableColumn<?, ?> cafeColumn;

    @FXML
    private Button backBTN;

    @FXML
    private Button searchBTN;

    @FXML
    private Label errorLBL;

    @FXML
    private Button showBTN;

    @FXML
    private TextField searchFDL;

    @FXML
    private TableColumn<?, ?> restaurantColumn;

    @FXML
    private TableView<?> searchTable;

    @FXML
    void backHandler(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/customerPage.fxml"));
        loader.load();
        CustomerPageController customerPageController = loader.getController();
        SearchController.setScene(new Scene((Parent) loader.getRoot()));
        SearchController.setTitle("Login");
        SearchController.setResizable(false);
        customerPageController.initFunction(SearchController,customer);
        SearchController.show();
    }



    @FXML
    void pressSearch(ActionEvent event) {

    }


    @FXML
    void pressShow(ActionEvent event) {

    }



}
