package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Customer;
import model.CustomerFile;
import model.DeliveryFile;
import model.Regex;

import java.io.IOException;

public class RegisterSecondPageController {

    private Stage registerPageStage;
    private String username;
    private String role;
    private CustomerFile customerFile;
    private DeliveryFile deliveryFile;
    private Regex regex;

    public void initFunction(Stage registerPageStage , String username){
        this.registerPageStage=registerPageStage;
        this.username=username;
        customerFile= new CustomerFile();
        deliveryFile=new DeliveryFile();
        regex=new Regex();
    }

    @FXML
    private Label errorLBL;

    @FXML
    private TextField familyFLD;

    @FXML
    private TextField nameFLD;

    @FXML
    private PasswordField passwordFLD;

    @FXML
    private TextField phoneNumberFLD;
    private Stage dialogStage;
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    @FXML
    void backHandler(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/registerFirstPage.fxml"));
        loader.load();
        RegisterFirstPageController registerPageController = loader.getController();
        registerPageStage.setScene(new Scene((Parent) loader.getRoot()));
        registerPageStage.setTitle("Register Page");
        registerPageStage.setResizable(false);
        registerPageController.initFunction(registerPageStage);
        registerPageStage.show();
    }

    @FXML
    void registerHandler(ActionEvent event) {
        //errorLBL.setText("");
        if(role != null){
            if(regex.nameRegex(nameFLD.getText()) && regex.nameRegex(familyFLD.getText()) &&
                    regex.passwordRegex(passwordFLD.getText()) && regex.phoneNumberRegex(phoneNumberFLD.getText())){
                if(role.equals("customer")){
                    customerFile.addCustomer(new Customer(nameFLD.getText(),familyFLD.getText(),username,passwordFLD.getText()
                    ,phoneNumberFLD.getText()));
//                    goToCustomerPage();
                }
                else if(role.equals("delivery")){
                    deliveryFile.addCustomer(new Customer(nameFLD.getText(),familyFLD.getText(),username,passwordFLD.getText()
                            ,phoneNumberFLD.getText()));
//                    goToDeliveryPage();
                }
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(dialogStage);
                alert.setTitle("Invalid Fields");
                alert.setHeaderText("Please correct invalid fields");
                alert.setContentText("Not Valid ");

                alert.showAndWait();
            }
               // errorLBL.setText("Not Valid");

        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText("Choose Your Role ");

            alert.showAndWait();
        }
           // errorLBL.setText("Choose Your Role");

    }

    @FXML
    void customerHandler(ActionEvent event) {
        this.role = "customer";
    }

    @FXML
    void deliveryHandler(ActionEvent event) {
        this.role = "delivery";
    }
}
