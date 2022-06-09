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

public class CartCustomerController implements Initializable {
    private Stage cartStage;
    private Customer customer;
    private CartCustomerFile cartCustomerFile;
    private SendRequestFile sendRequestFile;
    private CustomerFile customerFile;
    private Regex regex;
    private OrderTrackingFile orderTrackingFile;


    public void initFunction(Stage cartStage, Customer customer) {
        this.cartStage=cartStage;
        this.customer = customer;
        cartCustomerFile = new CartCustomerFile();
        sendRequestFile= new SendRequestFile();
        customerFile = new CustomerFile();
        orderTrackingFile = new OrderTrackingFile();
        regex=new Regex();
        ArrayList<Order> orders = cartCustomerFile.getCustomerOrders(customer);
        updateTable(orders);
    }

    public void updateTable(ArrayList<Order> orders){
        ObservableList<Order> orderObservableList = FXCollections.observableArrayList(orders);
        orderTable.setItems(orderObservableList);
    }

    @FXML
    private TextField addressFDL;

    @FXML
    private TableColumn<Order, String> itemColumn;

    @FXML
    private TableView<Order> orderTable;

    @FXML
    private TableColumn<Order, String> priceColumn;

    @FXML
    private TextField discountCodeFLD;

    @FXML
    private Label errorLBL;

    @FXML
    void backHandler(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/customerPage.fxml"));
        loader.load();
        CustomerPageController customerPageController = loader.getController();
        cartStage.setScene(new Scene((Parent) loader.getRoot()));
        cartStage.setTitle("Customer");
        cartStage.setResizable(false);
        customerPageController.initFunction(cartStage , customer);
        cartStage.show();

    }

    @FXML
    void checkoutHandler(ActionEvent event) {
        errorLBL.setText("");
        Order selectedOrder = orderTable.getSelectionModel().getSelectedItem();
        if(selectedOrder != null){
            if(!discountCodeFLD.getText().equals("")){
                if(sendRequestFile.requestIsExistForFriend(customer.getUsername())){
                    if(sendRequestFile.codeIsTrueForFriend(discountCodeFLD.getText())){
                        int price = Integer.parseInt(selectedOrder.getPrice());
                        price-= price * (0.2);
                        sendRequestFile.deleteFriendUsername(customer);
                        selectedOrder.setPrice(String.valueOf(price));
                        if(regex.addressRegex(addressFDL.getText())) {
                            Customer customer1 = customerFile.getCustomer(customer.getUsername());
                            if (Integer.parseInt(customer1.getWallet()) >= Integer.parseInt(selectedOrder.getPrice())) {
                                customerFile.decreaseWallet(customer1, selectedOrder.getPrice());
                                orderTrackingFile.addOrder(selectedOrder, addressFDL.getText());
                                cartCustomerFile.deleteOrder(selectedOrder);
                                try {
                                    backHandler(event);
                                } catch (IOException e) {
                                    System.out.println(e.getMessage());
                                }
                            } else
                                errorLBL.setText("Your account balance is not enough");
                        }
                    }
                    else
                        errorLBL.setText("Discount code is not true");
                }
                else if( sendRequestFile.requestIsExistForCustomer(customer.getUsername())){
                    if(sendRequestFile.codeIsTrueForCustomer(discountCodeFLD.getText())){
                        int price = Integer.parseInt(selectedOrder.getPrice());
                        price -= price * (0.15);
                        sendRequestFile.deleteCustomerUsername(customer);
                        selectedOrder.setPrice(String.valueOf(price));
                        if(regex.addressRegex(addressFDL.getText())) {
                            Customer customer1 = customerFile.getCustomer(customer.getUsername());
                            if (Integer.parseInt(customer1.getWallet()) >= Integer.parseInt(selectedOrder.getPrice())) {
                                customerFile.decreaseWallet(customer1, selectedOrder.getPrice());
                                orderTrackingFile.addOrder(selectedOrder, addressFDL.getText());
                                cartCustomerFile.deleteOrder(selectedOrder);
                                try {
                                    backHandler(event);
                                } catch (IOException e) {
                                    System.out.println(e.getMessage());
                                }
                            } else
                                errorLBL.setText("Your account balance is not enough");
                        }
                    }
                    else
                        errorLBL.setText("Discount code is not true");
                }
            }
            else{
                if(regex.addressRegex(addressFDL.getText())) {
                    Customer customer1 = customerFile.getCustomer(customer.getUsername());
                    if (Integer.parseInt(customer1.getWallet()) >= Integer.parseInt(selectedOrder.getPrice())) {
                        customerFile.decreaseWallet(customer1, selectedOrder.getPrice());
                        orderTrackingFile.addOrder(selectedOrder, addressFDL.getText());
                        cartCustomerFile.deleteOrder(selectedOrder);
                        try {
                            backHandler(event);
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    } else
                        errorLBL.setText("Your account balance is not enough");
                }
            }

        }
        else
            errorLBL.setText("Select a order");
    }

    @FXML
    void cancelHandler(ActionEvent event) {

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        itemColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}
