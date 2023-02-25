package com.delicacy.delicacy;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javafx.application.Platform.exit;


public class dashboard {

    @FXML private TextField a_s_firstName;
    @FXML private TextField a_s_lastName;
    @FXML private TextField a_s_email;

    @FXML private PasswordField a_s_password;
    @FXML private PasswordField a_s_confirmPassword;


    @FXML private TextField a_l_email;

    @FXML private PasswordField a_l_password;



    @FXML private TextField c_s_firstName;
    @FXML private TextField c_s_lastName;
    @FXML private TextField c_s_email;

    @FXML private PasswordField c_s_password;
    @FXML private PasswordField c_s_confirmPassword;

    @FXML private TextField c_l_email;

    @FXML private PasswordField c_l_password;

    @FXML
    AnchorPane db;
    @FXML
    BorderPane as;
    @FXML
    BorderPane al;
    @FXML
    BorderPane cs;
    @FXML
    BorderPane cl;

    Stage stage = new Stage();
    Parent root = null;
    @FXML
    private void admin_signup_submit (ActionEvent event) throws ClassNotFoundException {
       //This function will execute when admin_signup submit button is pressed

        String name_f = a_s_firstName.getText();
        String name_l = a_s_lastName.getText();
        String em = a_s_email.getText();
        String pass = a_s_password.getText();
        String c_pass = a_s_confirmPassword.getText();

        //If password and confirm password don't match again signup page will load
        if (!pass.equals(c_pass)) {

            try {
                root = FXMLLoader.load(getClass().getResource("view/adminSignup.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(splashscreen.class.getName()).log(Level.SEVERE, null, ex);
            }
            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);
            stage.setScene(scene);
            stage.setTitle("Confirm Password Did Not Match");
            stage.show();
            as.getScene().getWindow().hide();
        }


        //If name and email field is empty again signup page will load

        else if (name_f.length()==0 || name_l.length()==0 || em.length()==0 ||em.contains("@")==false) {

            try {
                root = FXMLLoader.load(getClass().getResource("view/adminSignup.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(splashscreen.class.getName()).log(Level.SEVERE, null, ex);
            }
            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);
            stage.setScene(scene);
            stage.setTitle("Name or Email is Empty");
            stage.show();
            as.getScene().getWindow().hide();
        }
        //If everything is ok then data will be inserted in database
        else {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://ap-south.connect.psdb.cloud/delicacy?sslMode=VERIFY_IDENTITY",
                        "apike5c6fiy1rsrajmzv",
                        "pscale_pw_9QXgibEIlGrneTReXBvzn80n9OV8HW53gPMndCpo2mB");

                String sql = "select * from Admin_Details where Email=? and Password=SHA1(?)";

                PreparedStatement pStmt = con.prepareStatement(sql);
                boolean flag = false;


                pStmt.setString(1, em);
                pStmt.setString(2, pass);

                ResultSet rs = pStmt.executeQuery();
                flag=rs.next();


                if (flag == true) {

                    try {
                        root = FXMLLoader.load(getClass().getResource("view/adminSignup.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(splashscreen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Scene scene = new Scene(root);
                    stage.setTitle("Email Exists, Try Different Email");
                    stage.setResizable(false);
                    stage.initStyle(StageStyle.UTILITY);
                    stage.setScene(scene);
                    stage.show();
                    as.getScene().getWindow().hide();
                }
                //If everything is ok then data will be inserted in database
                else {
                    PreparedStatement pStmt2 = con.prepareStatement("insert into Admin_Details values(?, ?, ?, SHA1(?))");
                    pStmt2.setString(1, name_f);
                    pStmt2.setString(2, name_l);
                    pStmt2.setString(3, em);
                    pStmt2.setString(4, pass);
                    pStmt2.executeUpdate();

                    // closing connection
                    con.close();

                    try {
                        root = FXMLLoader.load(getClass().getResource("view/AdministratorView.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(splashscreen.class.getName()).log(Level.SEVERE, null, ex);
                    }


                    Scene scene = new Scene(root);
                    stage.setResizable(false);
                    stage.initStyle(StageStyle.UTILITY);

                    stage.setScene(scene);
                    stage.show();
                    as.getScene().getWindow().hide();

                }


                } catch(Exception e){
                    System.out.println(e);
                }

        }
    }
    @FXML
    private void admin_login_submit (ActionEvent event) throws ClassNotFoundException {

        String em = a_l_email.getText();
        String pass = a_l_password.getText();

        //If email and password field is empty again login page will load


        if (em.length()==0 || pass.length()==0) {

            try {
                root = FXMLLoader.load(getClass().getResource("view/adminLogin.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(splashscreen.class.getName()).log(Level.SEVERE, null, ex);
            }
            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);
            stage.setScene(scene);
            stage.setTitle("Email or Password Field Is Empty");
            stage.show();
            al.getScene().getWindow().hide();
        }
       //If everything is ok then data will be loaded from database to see if given email and password matches
        else {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://ap-south.connect.psdb.cloud/delicacy?sslMode=VERIFY_IDENTITY",
                        "apike5c6fiy1rsrajmzv",
                        "pscale_pw_9QXgibEIlGrneTReXBvzn80n9OV8HW53gPMndCpo2mB");

                String sql = "select * from Admin_Details where Email=? and Password=SHA1(?)";

                PreparedStatement pStmt = con.prepareStatement(sql);
                boolean flag = false;


                pStmt.setString(1, em);
                pStmt.setString(2, pass);

                ResultSet rs = pStmt.executeQuery();
                flag=rs.next();

                //Given email and password don't match with database then flag= 0 else flag =1

                if (flag == false) {

                    try {
                        root = FXMLLoader.load(getClass().getResource("view/adminLogin.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(splashscreen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Scene scene = new Scene(root);
                    stage.setResizable(false);
                    stage.initStyle(StageStyle.UTILITY);
                    stage.setTitle("Email or Password Is Wrong");
                    stage.setScene(scene);
                    stage.show();
                    al.getScene().getWindow().hide();
                }
                else {

                    con.close();

                    try {
                        root = FXMLLoader.load(getClass().getResource("view/AdministratorView.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(splashscreen.class.getName()).log(Level.SEVERE, null, ex);
                    }


                    Scene scene = new Scene(root);
                    stage.setResizable(false);
                    stage.initStyle(StageStyle.UTILITY);
                    stage.setScene(scene);
                    stage.show();
                    al.getScene().getWindow().hide();

                }


            } catch(Exception e){
                System.out.println(e);
            }

        }
    }
    @FXML
    private void customer_signup_submit (ActionEvent event) throws ClassNotFoundException {
        //This function will execute when admin_signup submit button is pressed

        String name_f = c_s_firstName.getText();
        String name_l = c_s_lastName.getText();
        String em = c_s_email.getText();
        String pass = c_s_password.getText();
        String c_pass = c_s_confirmPassword.getText();

        //If password and confirm password don't match login signup page will load

        if (!pass.equals(c_pass)) {

            try {
                root = FXMLLoader.load(getClass().getResource("view/signupCustomer.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(splashscreen.class.getName()).log(Level.SEVERE, null, ex);
            }
            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);
            stage.setScene(scene);
            stage.setTitle("Confirm Password Did Not Match");
            stage.show();
            cs.getScene().getWindow().hide();
        }
        //If name and email field is empty again signup page will load

        else if (name_f.length()==0 || name_l.length()==0 || em.length()==0||em.contains("@")==false) {

            try {
                root = FXMLLoader.load(getClass().getResource("view/signupCustomer.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(splashscreen.class.getName()).log(Level.SEVERE, null, ex);
            }
            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);
            stage.setScene(scene);
            stage.setTitle("Name or Email Is Empty");
            stage.show();
            cs.getScene().getWindow().hide();
        }

        else {
            try {


                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://ap-south.connect.psdb.cloud/delicacy?sslMode=VERIFY_IDENTITY",
                        "apike5c6fiy1rsrajmzv",
                        "pscale_pw_9QXgibEIlGrneTReXBvzn80n9OV8HW53gPMndCpo2mB");

                String sql = "select * from Customer_Details where Email=? and Password=SHA1(?)";

                PreparedStatement pStmt = con.prepareStatement(sql);
                boolean flag = false;


                pStmt.setString(1, em);
                pStmt.setString(2, pass);

                ResultSet rs = pStmt.executeQuery();
                flag=rs.next();
                // System.out.println(flag);

                if (flag == true) {
                    Stage stage = new Stage();
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("view/signupCustomer.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(splashscreen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Scene scene = new Scene(root);
                    stage.setResizable(false);
                    stage.initStyle(StageStyle.UTILITY);
                    stage.setTitle("Email Exists, Try Different Email");
                    stage.setScene(scene);
                    stage.show();
                    cs.getScene().getWindow().hide();
                }

                //If everything is ok then data will be inserted in database
                else {
                    PreparedStatement pStmt2 = con.prepareStatement("insert into Customer_Details values(?, ?, ?, SHA1(?))");
                    pStmt2.setString(1, name_f);
                    pStmt2.setString(2, name_l);
                    pStmt2.setString(3, em);
                    pStmt2.setString(4, pass);
                    pStmt2.executeUpdate();

                    // closing connection
                    con.close();
                    Stage stage = new Stage();
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("view/AdministratorView.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(splashscreen.class.getName()).log(Level.SEVERE, null, ex);
                    }


                    Scene scene = new Scene(root);
                    stage.setResizable(false);
                    stage.initStyle(StageStyle.UTILITY);
                    stage.setScene(scene);
                    stage.show();
                    cs.getScene().getWindow().hide();

                }


            } catch(Exception e){
                System.out.println(e);
            }

        }
    }

    @FXML
    private void customer_login_submit (ActionEvent event) throws ClassNotFoundException {
        String em = c_l_email.getText();
        String pass = c_l_password.getText();
       //If email and password is empty again login page will be loaded
        if (em.length()==0 || pass.length()==0) {

            try {
                root = FXMLLoader.load(getClass().getResource("view/addItemForm.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(splashscreen.class.getName()).log(Level.SEVERE, null, ex);
            }
            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UTILITY);
            stage.setScene(scene);
            stage.setTitle("Email or Password Field Is Empty");
            stage.show();
            cl.getScene().getWindow().hide();
        }
      //If everything is ok connect the database
        else {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://ap-south.connect.psdb.cloud/delicacy?sslMode=VERIFY_IDENTITY",
                        "apike5c6fiy1rsrajmzv",
                        "pscale_pw_9QXgibEIlGrneTReXBvzn80n9OV8HW53gPMndCpo2mB");

                String sql = "select * from Customer_Details where Email=? and Password=SHA1(?)";

                PreparedStatement pStmt = con.prepareStatement(sql);
                boolean flag = false;


                pStmt.setString(1, em);
                pStmt.setString(2, pass);

                ResultSet rs = pStmt.executeQuery();
                flag=rs.next();
                // System.out.println(flag);

                //If given email and password don't match with database then again login page will be loaded
                if (flag == false) {

                    try {
                        root = FXMLLoader.load(getClass().getResource("view/loginCustomer.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(splashscreen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Scene scene = new Scene(root);
                    stage.setResizable(false);
                    stage.initStyle(StageStyle.UTILITY);
                    stage.setTitle("Email or Password Is Wrong");
                    stage.setScene(scene);
                    stage.show();
                    cl.getScene().getWindow().hide();
                }

                //load next page
                else {

                    con.close();

                    try {
                        root = FXMLLoader.load(getClass().getResource("view/AdministratorView.fxml"));
                    } catch (IOException ex) {
                        Logger.getLogger(splashscreen.class.getName()).log(Level.SEVERE, null, ex);
                    }


                    Scene scene = new Scene(root);
                    stage.setResizable(false);
                    stage.initStyle(StageStyle.UTILITY);
                    stage.setScene(scene);
                    stage.show();
                    cl.getScene().getWindow().hide();

                }


            } catch(Exception e){
                System.out.println(e);
            }

        }
    }

    @FXML
    private void admin_signup (ActionEvent event) throws ClassNotFoundException {

        //After clicking signup option in dashboard this function will occur

        try {
            root = FXMLLoader.load(getClass().getResource("view/adminSignup.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(splashscreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UTILITY);
        stage.setScene(scene);
        stage.show();
        db.getScene().getWindow().hide();
    }

    @FXML
    private void admin_login (ActionEvent event) throws ClassNotFoundException {

        //After clicking login option in dashboard this function will occur

        try {
            root = FXMLLoader.load(getClass().getResource("view/adminLogin.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(splashscreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UTILITY);
        stage.setScene(scene);
        stage.show();
        db.getScene().getWindow().hide();

    }
    @FXML
    private void admin_login2 (MouseEvent event) throws ClassNotFoundException {

        //After clicking login option in adminSignup.fxml as user already has account, this function will occur

        try {
            root = FXMLLoader.load(getClass().getResource("view/adminLogin.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(splashscreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UTILITY);
        stage.setScene(scene);
        stage.show();
        as.getScene().getWindow().hide();

    }

    @FXML
    private void customer_signup (ActionEvent event) throws ClassNotFoundException {
        //After clicking signup option in dashboard this function will occur

        try {
            root = FXMLLoader.load(getClass().getResource("view/signupCustomer.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(splashscreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UTILITY);
        stage.setScene(scene);
        stage.show();
        db.getScene().getWindow().hide();
    }
    @FXML
    public void customer_login (ActionEvent event) throws ClassNotFoundException {
        //After clicking login option in dashboard this function will occur

        try {
            root = FXMLLoader.load(getClass().getResource("view/loginCustomer.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(splashscreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UTILITY);
        stage.setScene(scene);
        stage.show();
        as.getScene().getWindow().hide();
    }
    @FXML
    public void customer_login2 (MouseEvent event) throws ClassNotFoundException {
        //After clicking login option in adminSignup.fxml as user already has account, this function will occur

        try {
            root = FXMLLoader.load(getClass().getResource("view/loginCustomer.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(splashscreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UTILITY);
        stage.setScene(scene);
        stage.show();
        cs.getScene().getWindow().hide();
    }
    @FXML
    private void Exit()
    {
        exit();
    }

}
