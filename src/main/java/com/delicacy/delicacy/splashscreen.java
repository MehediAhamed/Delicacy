package com.delicacy.delicacy;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class splashscreen implements Initializable {


    //This function controls splashscreen
    @FXML
    AnchorPane ap;

    class ShowSplashScreen extends Thread{
        @Override
        public void run(){
            try {
                Thread.sleep(3000);

                Platform.runLater(() -> {
                    Stage stage = new Stage();
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("view/dashboard.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(splashscreen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    ap.getScene().getWindow().hide();
                });
            } catch (InterruptedException ex) {
                Logger.getLogger(splashscreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new ShowSplashScreen().start();
    }
}