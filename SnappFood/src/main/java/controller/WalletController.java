package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Customer;
import model.CustomerFile;
import model.Regex;

import java.io.IOException;

public class WalletController {

    private Stage walletStage;
    private Customer customer;
    private CustomerFile customerFile;
    private Regex regex;

    public void initFunction(Stage walletStage , Customer customer){
        customerFile = new CustomerFile();
        this.walletStage=walletStage;
        this.customer = customer;
        regex = new Regex();
        inventoryLBL.setText(customerFile.getCustomer(customer.getUsername()).getWallet());

    }

    @FXML
    private Label inventoryLBL;

    @FXML
    private Label errorLBL;

    @FXML
    private TextField increaseFDL;

    @FXML
    void backHandler(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/customerPage.fxml"));
        loader.load();
        CustomerPageController customerPageController = loader.getController();
        walletStage.setScene(new Scene((Parent) loader.getRoot()));
        walletStage.setTitle("Customer");
        walletStage.setResizable(false);
        customerPageController.initFunction(walletStage , customer);
        walletStage.show();
    }

    @FXML
    void pressIncrease(ActionEvent event) {
        if(regex.priceRegex(increaseFDL.getText())){
            customerFile.increaseWallet(customer,increaseFDL.getText());
            try {
                backHandler(event);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        else
            errorLBL.setText("Not Valid");
    }
}
