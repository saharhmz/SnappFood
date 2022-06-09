package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customer;
import model.Order;
import model.OrderTrackingFile;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrderTrackingController implements Initializable {

    private Stage orderTrackingStage;
    private Customer customer;
    private OrderTrackingFile orderTrackingFile;

    public void initFunction(Stage checkoutCustomer,Customer customer){
        this.orderTrackingStage =checkoutCustomer;
        this.customer=customer;
        orderTrackingFile = new OrderTrackingFile();
        ArrayList<Order> allOrders = orderTrackingFile.getAllOrders(customer);
        updateTable(allOrders);
    }

    public void updateTable(ArrayList<Order> orders){
        ObservableList<Order> orderObservableList = FXCollections.observableArrayList(orders);
        checkoutListTable.setItems(orderObservableList);
    }

    @FXML
    private TableView<Order> checkoutListTable;

    @FXML
    private TableColumn<Order, String> statusColumn;

    @FXML
    private TableColumn<Order, String> itemColumn;

    @FXML
    void backHandler(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/customerPage.fxml"));
        loader.load();
        CustomerPageController customerPageController = loader.getController();
        orderTrackingStage.setScene(new Scene((Parent) loader.getRoot()));
        orderTrackingStage.setTitle("Customer");
        orderTrackingStage.setResizable(false);
        customerPageController.initFunction(orderTrackingStage , customer);
        orderTrackingStage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        itemColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
    }
}
