package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Admin;
import model.Regex;

import java.io.IOException;

public class AddCafeController {

    private Stage addCafeStage;
    private Regex regex;
    private Admin admin;

    public void initFunction(Stage addCafeStage , Admin admin){
        this.addCafeStage=addCafeStage;
        this.admin=admin;
        regex=new Regex();
    }
    @FXML
    private TextField addressFLD;

    @FXML
    private Label errorLBL;

    @FXML
    private TextField nameFLD;

    @FXML
    void backHandler(ActionEvent event) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/adminPage.fxml"));
        loader.load();
        AdminPageController adminPageController= loader.getController();
        addCafeStage.setScene(new Scene((Parent) loader.getRoot()));
        addCafeStage.setTitle("Admin");
        addCafeStage.setResizable(false);
        adminPageController.initFunction(addCafeStage,admin);
        addCafeStage.show();
    }

    @FXML
    void pressAddList(ActionEvent event) throws IOException{
        if(regex.nameRegex(nameFLD.getText()) && regex.addressRegex(addressFLD.getText())){
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/addCafeList.fxml"));
            loader.load();
            AddCafeListController addCafeListFirstPageController= loader.getController();
            addCafeStage.setScene(new Scene((Parent) loader.getRoot()));
            addCafeStage.setTitle("Add List");
            addCafeStage.setResizable(false);
            addCafeListFirstPageController.initFunction(addCafeStage , nameFLD.getText() , addressFLD.getText() ,admin);
            addCafeStage.show();
        }
        else
            errorLBL.setText("Not Valid");
    }
}
