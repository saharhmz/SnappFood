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
import model.Delivery;
import model.Order;
import model.OrderTrackingFile;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustomerListForDeliveryController implements Initializable {


    private Stage customerListStage;
    private Delivery delivery;
    private OrderTrackingFile orderTrackingFile;

    public void initFunction(Stage customerListStage , Delivery delivery){
        errorLBL.setText("");
        this.customerListStage = customerListStage;
        this.delivery=delivery;
        orderTrackingFile= new OrderTrackingFile();
        ArrayList<Order> allOrders = orderTrackingFile.getAllOrders();
        ArrayList<Order> notDeliveredOrders = getNotDeliveredOrders(allOrders);
        updateTable(notDeliveredOrders);
    }

    public ArrayList<Order> getNotDeliveredOrders(ArrayList<Order> allOrders){
        ArrayList<Order> notDeliveredOrders = new ArrayList<>();
        for(int i=0 ; i < allOrders.size() ;i++){
            if(allOrders.get(i).getStatus().equals("NotDelivered")){
                notDeliveredOrders.add(allOrders.get(i));
            }
        }

        return notDeliveredOrders;
    }

    public void updateTable(ArrayList<Order> notDeliveredOrders){
        ObservableList<Order> orderObservableList = FXCollections.observableArrayList(notDeliveredOrders);
        customerListTable.setItems(orderObservableList);
    }

    @FXML
    private TableColumn<Order, String> addressColumn;

    @FXML
    private TableView<Order> customerListTable;

    @FXML
    private TableColumn<Order, String> nameColumn;

    @FXML
    private TableColumn<Order, String> orderColumn;

    @FXML
    private Label errorLBL;

    @FXML
    void backHandler(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/deliveryPage.fxml"));
        loader.load();
        DeliveryPageController deliveryPageController= loader.getController();
        customerListStage.setScene(new Scene((Parent) loader.getRoot()));
        customerListStage.setTitle("Delivery");
        customerListStage.setResizable(false);
        deliveryPageController.initFunction(customerListStage , delivery);
        customerListStage.show();
    }

    @FXML
    void orderDeliveryHandler(ActionEvent event) {
        errorLBL.setText("");
        Order selectedOrder = customerListTable.getSelectionModel().getSelectedItem();
        if(selectedOrder != null){
            orderTrackingFile.deliverOrder(selectedOrder);
            try {
                backHandler(event);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        else
            errorLBL.setText("Select a Order");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("customerUsername"));
        orderColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
    }
}
