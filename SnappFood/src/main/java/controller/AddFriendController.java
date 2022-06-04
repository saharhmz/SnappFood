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
import model.SendRequestFile;

import java.io.IOException;

public class AddFriendController {

    private Stage addFriendStage;
    private Regex regex;
    private Customer customer;
    private SendRequestFile sendRequestFile;
    private CustomerFile customerFile;

    public void initFunction(Stage addFriendStage , Customer customer){
        this.addFriendStage=addFriendStage;
        this.customer=customer;
        regex = new Regex();
        customerFile=new CustomerFile();
        sendRequestFile=new SendRequestFile();
    }
    @FXML
    private Label errorLBL;

    @FXML
    private TextField friendEmailFLD;

    @FXML
    void backHandler(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/customerPage.fxml"));
        loader.load();
        CustomerPageController customerPageController = loader.getController();
        addFriendStage.setScene(new Scene((Parent) loader.getRoot()));
        addFriendStage.setTitle("Customer");
        addFriendStage.setResizable(false);
        customerPageController.initFunction(addFriendStage , customer);
        addFriendStage.show();
    }

    @FXML
    void pressSend(ActionEvent event) {
        if(regex.emailRegex(friendEmailFLD.getText())){
            if(!sendRequestFile.requestIsExist(friendEmailFLD.getText()) && customerFile.emailNotExist
                    (friendEmailFLD.getText())){
                sendRequestFile.addRequest(customer.getUsername(),friendEmailFLD.getText());
                try {
                    backHandler(event);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            else
                errorLBL.setText("The account is exist with the entered email");
        }
        else
            errorLBL.setText("Not Valid");
    }
}
