package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Customer;

import java.io.IOException;

public class CustomerPageController {

    private Stage customerStage;
    private Customer customer;
    public void initFunction(Stage customerStage , Customer customer){
        this.customerStage=customerStage;
        this.customer=customer;
    }
    @FXML
    void backHandler(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/loginPage.fxml"));
        loader.load();
        LoginPageController loginPageController= loader.getController();
        customerStage.setScene(new Scene((Parent) loader.getRoot()));
        customerStage.setTitle("Login");
        customerStage.setResizable(false);
        loginPageController.initFunction(customerStage);
        customerStage.show();
    }

    @FXML
    void pressSearch(ActionEvent event) {
           FXMLLoader loader= new FXMLLoader(getClass().getResource("/SearchCustumer.fxml"));
        loader.load();
        SearchController searchController= loader.getController();
        customerStage.setScene(new Scene((Parent) loader.getRoot()));
        customerStage.setTitle("Login");
        customerStage.setResizable(false);
        searchController.initFunction(customerStage,customer);
        customerStage.show();

    }

    @FXML
    void pressWallet(ActionEvent event) throws IOException{
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/wallet.fxml"));
        loader.load();
        WalletController walletController= loader.getController();
        customerStage.setScene(new Scene((Parent) loader.getRoot()));
        customerStage.setTitle("Send Request");
        customerStage.setResizable(false);
        walletController.initFunction(customerStage, customer);
        customerStage.show();
    }

    @FXML
    void pressAddFriend(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/addFriend.fxml"));
        loader.load();
        AddFriendController addFriendController= loader.getController();
        customerStage.setScene(new Scene((Parent) loader.getRoot()));
        customerStage.setTitle("Send Request");
        customerStage.setResizable(false);
        addFriendController.initFunction(customerStage, customer);
        customerStage.show();
    }

}
