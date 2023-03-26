package com.delicacy.delicacy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.delicacy.delicacy.CustomerMenuController.vec;
import static javafx.application.Platform.exit;

public class CustomerViewController {

    @FXML
    AnchorPane AV;
    Stage stage = new Stage();
    Parent root = null;

    @FXML
    public void MenuOnAction(ActionEvent actionEvent) {

        vec.clear();


        try {
            root = FXMLLoader.load(getClass().getResource("view/CustomerMenu.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(splashscreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.DECORATED);
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
        stage.initStyle(StageStyle.DECORATED);
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
        stage.initStyle(StageStyle.DECORATED);
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
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(scene);

        stage.show();
        AV.getScene().getWindow().hide();


    }


    @FXML
    public void admin_login (MouseEvent event) throws ClassNotFoundException {

        //After clicking login option in adminSignup.fxml as user already has account, this function will occur

        try {
            root = FXMLLoader.load(getClass().getResource("view/adminLogin.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(splashscreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.show();
        AV.getScene().getWindow().hide();

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
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.setTitle(" ");
        stage.show();

    }

    public void BacktoCustomerView()
    {

        try {
            root = FXMLLoader.load(getClass().getResource("view/CustomerView.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(splashscreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.setTitle(" ");
        stage.show();

    }

    @FXML
    private void profileOnAction(ActionEvent event) throws IOException {
//        Stage myStage= (Stage)((Node)event.getSource()).getScene().getWindow();
//        FXMLLoader loader= new FXMLLoader(main.class.getResource("view/CustomerProfile.fxml"));
//        Scene scene=new Scene(loader.load());
//        myStage.setScene(scene);
//        myStage.show();

        ProfileController p =new ProfileController();
        p.ProfileStage(event);
    }
}