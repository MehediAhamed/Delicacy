package com.delicacy.delicacy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javafx.application.Platform.exit;

public class AdminViewController {

    @FXML
    AnchorPane AV;
    Stage stage = new Stage();
    Parent root = null;

    @FXML
    public void MenuOnAction(ActionEvent actionEvent) {


        try {
            root = FXMLLoader.load(getClass().getResource("view/addItemForm.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(splashscreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
        AV.getScene().getWindow().hide();
    }


    @FXML
    public void OrderOnAction(ActionEvent actionEvent) {


        try {
            root = FXMLLoader.load(getClass().getResource("view/CurrentOrder.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(splashscreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
        AV.getScene().getWindow().hide();


    }

    @FXML
    public void ReviewOnAction(ActionEvent actionEvent) {


        try {
            root = FXMLLoader.load(getClass().getResource("view/ReviewNRating.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(splashscreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
        AV.getScene().getWindow().hide();


    }

    @FXML
    public void SalesOnAction(ActionEvent actionEvent) {


        try {
            root = FXMLLoader.load(getClass().getResource("view/SalesNPromotion.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(splashscreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UTILITY);
        stage.setScene(scene);

        stage.show();
        AV.getScene().getWindow().hide();


    }

    @FXML
    private void customer_login (MouseEvent event) throws ClassNotFoundException {
       dashboard d= new dashboard();
       AV.getScene().getWindow().hide();
       d.customer_login2(event);


    }
    @FXML
    private void Exit()
    {
        exit();
    }

    @FXML

    public void BacktoAdminView()
    {

        try {
            root = FXMLLoader.load(getClass().getResource("view/AdministratorView.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(splashscreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UTILITY);
        stage.setScene(scene);
        stage.setTitle(" ");
        stage.show();

    }
}