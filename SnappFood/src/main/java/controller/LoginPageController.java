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
import model.*;

import java.io.IOException;

public class LoginPageController {

    private Stage loginPageStage;
    private AdminFile adminFile;
    private CustomerFile customerFile;
    private DeliveryFile deliveryFile;

    public void initFunction(Stage loginPageStage){
        this.loginPageStage=loginPageStage;
        this.adminFile=new AdminFile();
        this.customerFile=new CustomerFile();
        this.deliveryFile=new DeliveryFile();
    }
    private Stage dialogStage;
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    @FXML
    private Label errorLBL;

    @FXML
    private PasswordField passwordFLD;

    @FXML
    private TextField usernameFLD;

    @FXML
    void exitHandler(ActionEvent event) {
        this.loginPageStage.close();
    }

    @FXML
    void loginHandler(ActionEvent event) {
        if(usernameFLD.getText().equals(adminFile.getAdmin().getUsername())){
            if(passwordFLD.getText().equals(adminFile.getAdmin().getPassword())){
                try {
                    goToAdminPage();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(dialogStage);
                alert.setTitle("Invalid Fields");
                alert.setHeaderText("Please correct invalid fields");
                alert.setContentText("Try Again ");

                alert.showAndWait();
            }
                //errorLBL.setText("Try Again");
        }
        else if( customerFile.isExist(usernameFLD.getText())){
            Customer customer = customerFile.getCustomer(usernameFLD.getText());
            if(customer.getPassword().equals(passwordFLD.getText())){
                try {
                    goToCustomerPage(customer);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            else

            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(dialogStage);
                alert.setTitle("Invalid Fields");
                alert.setHeaderText("Please correct invalid fields");
                alert.setContentText("Try Again ");

                alert.showAndWait();
            }
              //  errorLBL.setText("Try Again");
        }
        else if(deliveryFile.isExist(usernameFLD.getText())){
            Delivery delivery = deliveryFile.getDelivery(usernameFLD.getText());
            if(delivery.getPassword().equals(passwordFLD.getText())){
                try {
                    goToDeliveryPage(delivery);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(dialogStage);
                alert.setTitle("Invalid Fields");
                alert.setHeaderText("Please correct invalid fields");
                alert.setContentText("Try Again ");

                alert.showAndWait();
            }
                //errorLBL.setText("Try Again");
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText("Try Again ");

            alert.showAndWait();
        }
           // errorLBL.setText("Try Again");
    }

    public void goToDeliveryPage(Delivery delivery) throws IOException{
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/deliveryPage.fxml"));
        loader.load();
        DeliveryPageController deliveryPageController= loader.getController();
        loginPageStage.setScene(new Scene((Parent) loader.getRoot()));
        loginPageStage.setTitle("Delivery");
        loginPageStage.setResizable(false);
        deliveryPageController.initFunction(loginPageStage , delivery);
        loginPageStage.show();
    }

    public void goToCustomerPage(Customer customer) throws IOException{
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/customerPage.fxml"));
        loader.load();
        CustomerPageController customerPageController = loader.getController();
        loginPageStage.setScene(new Scene((Parent) loader.getRoot()));
        loginPageStage.setTitle("Customer");
        loginPageStage.setResizable(false);
        customerPageController.initFunction(loginPageStage , customer);
        loginPageStage.show();
    }

    public void goToAdminPage() throws IOException{
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/adminPage.fxml"));
        loader.load();
        AdminPageController adminPageController = loader.getController();
        loginPageStage.setScene(new Scene((Parent) loader.getRoot()));
        loginPageStage.setTitle("admin");
        loginPageStage.setResizable(false);
        adminPageController.initFunction(loginPageStage);
        loginPageStage.show();
    }

    @FXML
    void registerHandler(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/registerFirstPage.fxml"));
        loader.load();
        RegisterFirstPageController registerPageController = loader.getController();
        loginPageStage.setScene(new Scene((Parent) loader.getRoot()));
        loginPageStage.setTitle("Register Page");
        loginPageStage.setResizable(false);
        registerPageController.initFunction(loginPageStage);
        loginPageStage.show();
    }

}
