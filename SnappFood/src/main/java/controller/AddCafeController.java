package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Regex;

import java.io.IOException;

public class AddCafeController {

    private Stage addCafeStage;
    private Regex regex;

    public void initFunction(Stage addCafeStage){
        this.addCafeStage=addCafeStage;
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
        adminPageController.initFunction(addCafeStage);
        addCafeStage.show();
    }

    @FXML
    void pressAddList(ActionEvent event) throws IOException{
        if(regex.nameRegex(nameFLD.getText()) && regex.addressRegex(addressFLD.getText())){
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/addCafeList.fxml"));
            loader.load();
            AddCafeListController addCafeListController= loader.getController();
            addCafeStage.setScene(new Scene((Parent) loader.getRoot()));
            addCafeStage.setTitle("Add List");
            addCafeStage.setResizable(false);
            addCafeListController.initFunction(addCafeStage , nameFLD.getText() , addressFLD.getText());
            addCafeStage.show();
        }
        else
            errorLBL.setText("Not Valid");
    }
}
