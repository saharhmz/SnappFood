package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.CustomerFile;
import model.DeliveryFile;
import model.Regex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class RegisterFirstPageController {

    private Stage registerStage;
    private SendEmailController sendEmailController;
    private String code;
    private CustomerFile customerFile;
    private DeliveryFile deliveryFile;
    private String username;
    private Regex regex;

    public void initFunction(Stage registerStage){
        this.registerStage=registerStage;
        regex=new Regex();
        customerFile=new CustomerFile();
        deliveryFile=new DeliveryFile();
    }

    @FXML
    private TextField codeFLD;

    @FXML
    private TextField emailFLD;

    @FXML
    private Label errorLBL;

    @FXML
    void backHandler(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/loginPage.fxml"));
        loader.load();
        LoginPageController loginPageController = loader.getController();
        registerStage.setScene(new Scene((Parent) loader.getRoot()));
        registerStage.setTitle("Login Page");
        registerStage.setResizable(false);
        loginPageController.initFunction(registerStage);
        registerStage.show();
    }

    @FXML
    void nextHandler(ActionEvent event) throws IOException{
        if(code.equals(codeFLD.getText())){
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/registerSecondPage.fxml"));
            loader.load();
            RegisterSecondPageController registerSecondPageController = loader.getController();
            registerStage.setScene(new Scene((Parent) loader.getRoot()));
            registerStage.setTitle("Register");
            registerStage.setResizable(false);
            registerSecondPageController.initFunction(registerStage , username);
            registerStage.show();
        }

    }

    @FXML
    void sendCodeHandler(ActionEvent event) {
        errorLBL.setText("");
        errorLBL.setTextFill(Color.RED);
        if(regex.emailRegex(emailFLD.getText())){
            if( deliveryFile.emailNotExist(emailFLD.getText()) && customerFile.emailNotExist(emailFLD.getText())  ){
                this.code=randomNumber();
                username=emailFLD.getText();
                sendEmailController=new SendEmailController(code , emailFLD.getText(), errorLBL);
            }
            else
                errorLBL.setText("The account is exist with the entered email");

        }else
            errorLBL.setText("Entered email is not valid");
    }

    String randomNumber(){
        Random random = new Random();
        ArrayList<Integer> numbers = new ArrayList<>();
        int randomNumber = 0;
        for (int i = 0; i < 4; i++) {
            numbers.add(random.nextInt(9) + 1);
        }
        for(int c=0 ; c < 4 ; c++){
            randomNumber += numbers.get(c) * Math.pow(10,c);
        }
        return String.valueOf(randomNumber);
    }
}
