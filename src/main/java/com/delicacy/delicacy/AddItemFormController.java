package com.delicacy.delicacy;
import javafx.beans.property.SimpleStringProperty;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;


import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddItemFormController implements Initializable{
    public TextField txtItemId;
    public TextField txtDescription;
    public TextField txtUnitPrice;
    public TextField txtfoodname;

    public TableView <ItemDTO> tblItem;
    public TableColumn<ItemDTO,String> colItemId;
    public TableColumn<ItemDTO,String> colName;
    public TableColumn <ItemDTO,String>colPrice;
    public TableColumn<ItemDTO,String> colImage;
    public TableColumn <ItemDTO,String>colDescription;

    private String itemCode;


    private String food;
    private FileInputStream imageFile;
    private String Description;

    private String unitPrice;


    private void loadAllItems() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://ap-south.connect.psdb.cloud/delicacy?sslMode=VERIFY_IDENTITY",
                    "apike5c6fiy1rsrajmzv",
                    "pscale_pw_9QXgibEIlGrneTReXBvzn80n9OV8HW53gPMndCpo2mB");
            String sql = "select * from Food_Menu";
            Statement stmt = con.createStatement();
            ResultSet rst =stmt.executeQuery(sql);
            ObservableList<ItemDTO> allItems = FXCollections.observableArrayList();

            while (rst.next()) {

                allItems.add(new ItemDTO(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(5),"df"));
            }
            tblItem.setItems(allItems);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }



       // tblItem.getItems().add(allItems);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colName.setCellValueFactory(new PropertyValueFactory<>("foodName"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        //colImage.setCellValueFactory(new PropertyValueFactory<>());
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));


        loadAllItems();
//
    }
    public void tblItemOnAction(MouseEvent mouseEvent) {
        loadAllItems();

    }
    @FXML
    void ChooseFile (ActionEvent actionEvent) throws ClassNotFoundException  {

        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterpng =
                new FileChooser.ExtensionFilter("*.png", "*.png");
        FileChooser.ExtensionFilter extFilterjpg =
                new FileChooser.ExtensionFilter("*.jpg", "*.jpg");
        FileChooser.ExtensionFilter extFilterJPG =
                new FileChooser.ExtensionFilter("*.JPG", "*.JPG");

        FileChooser.ExtensionFilter extFilterPNG =
                new FileChooser.ExtensionFilter("*.PNG", "*.PNG");

        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);

        //Show open file dialog
        try{
            File file = fileChooser.showOpenDialog(null);
            imageFile = new FileInputStream(file);
        }
        catch (Exception e)
        {
            System.out.println("File not found");
        };



    }

    boolean addItem() throws ClassNotFoundException, SQLException{
        int flag=0;
        try {
            itemCode= txtItemId.getText();
            food=txtfoodname.getText();
            Description=txtDescription.getText();
            unitPrice=txtUnitPrice.getText();


            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://ap-south.connect.psdb.cloud/delicacy?sslMode=VERIFY_IDENTITY",
                    "apike5c6fiy1rsrajmzv",
                    "pscale_pw_9QXgibEIlGrneTReXBvzn80n9OV8HW53gPMndCpo2mB");
            PreparedStatement pStmt = con.prepareStatement("insert into Food_Menu values(?, ?, ?, ?, ?)");
            pStmt.setString(1, itemCode);
            pStmt.setString(2, food);
            pStmt.setString(3, unitPrice);
            pStmt.setBinaryStream(4, imageFile,imageFile.available());
            pStmt.setString(5, Description);
            pStmt.executeUpdate();

            // closing connection
            con.close();
            flag=1;


        } catch(Exception e){
            System.out.println(e);

        }

        if(flag==1)
        {
            System.out.println("Added");
            return true;

        }
        else
        {
            System.out.println("Not Added");
            return false;
        }

    }




   @FXML
    public void addOnAction (ActionEvent actionEvent) {


        try {

            boolean isAdded = addItem();


            String tilte;
            String message;
            //tray.notification.TrayNotification tray = new tray.notification.TrayNotification();
            // AnimationType type = AnimationType.POPUP;
            //tray.setAnimationType(type);
            if (isAdded) {
                (new Alert(Alert.AlertType.CONFIRMATION, "Item Added Successfully", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Added Successful";
                message = "Item Is Added";
                //tray.setTitle(tilte);
                // tray.setMessage(message);
                // tray.setNotificationType(NotificationType.SUCCESS);
                //loadAllItems();
            } else {
                (new Alert(Alert.AlertType.ERROR, "Item Not Added ", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Added Un Successful";
                message = "Item Is Not Added";
                // tray.setTitle(tilte);
                // tray.setMessage(message);
                // tray.setNotificationType(NotificationType.ERROR);
            }
            // tray.showAndDismiss(Duration.millis(3000));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            String tilte = "Item Is Already On The Sever";
            String message = "Item Is Not Added";
            //  tray.notification.TrayNotification tray = new TrayNotification();
            //  AnimationType type = AnimationType.POPUP;
            // tray.setAnimationType(type);
            // tray.setTitle(tilte);
            // tray.setMessage(message);
            //  tray.setNotificationType(NotificationType.NOTICE);
            //  tray.showAndDismiss(Duration.millis(3000));
        }


    }

            boolean updateItem() throws ClassNotFoundException, SQLException{
                int flag=0;
                try {
                    itemCode= txtItemId.getText();
                    food=txtfoodname.getText();
                    Description=txtDescription.getText();
                    unitPrice=txtUnitPrice.getText();


                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(
                            "jdbc:mysql://ap-south.connect.psdb.cloud/delicacy?sslMode=VERIFY_IDENTITY",
                            "apike5c6fiy1rsrajmzv",
                            "pscale_pw_9QXgibEIlGrneTReXBvzn80n9OV8HW53gPMndCpo2mB");

                    String sql = "update Food_Menu set Name=?,Price=?,Image=?,Description=? where Item_Code=?";
                    PreparedStatement pStmt = con.prepareStatement(sql);

                    pStmt.setObject(1, food);
                    pStmt.setObject(2, unitPrice);
                    pStmt.setBlob(3, InputStream.nullInputStream());
                    pStmt.setObject(4, Description);
                    pStmt.setObject(5, itemCode);
                    pStmt.executeUpdate();


                    // closing connection
                    con.close();
                    flag=1;


                } catch(Exception e){
                    System.out.println(e);

                }

                if(flag==1)
                {
                    System.out.println("Updated");
                    return true;

                }
                else
                {
                    System.out.println("Not Updated");
                    return false;
                }

            }
        public void updateOnAction(ActionEvent actionEvent) {

        try {
             boolean updateItem = updateItem();
            String tilte;
            String message;

            if (updateItem) {
                (new Alert(Alert.AlertType.CONFIRMATION, "Item Update Successfully", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Update Successful";
                message = "Item Is Updated";


                //loadAllItems();
            } else {
                (new Alert(Alert.AlertType.ERROR, "Item Not Update", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Update Un Successful";
                message = "Item Is Not Updated";

//                tray.setTitle(tilte);
//                tray.setMessage(message);
//                tray.setNotificationType(NotificationType.ERROR);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        //Customer Update Is Over(With Notification)
    }


        boolean deleteItem() throws ClassNotFoundException, SQLException{
            int flag=0;
            try {
                itemCode= txtItemId.getText();


                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://ap-south.connect.psdb.cloud/delicacy?sslMode=VERIFY_IDENTITY",
                        "apike5c6fiy1rsrajmzv",
                        "pscale_pw_9QXgibEIlGrneTReXBvzn80n9OV8HW53gPMndCpo2mB");

                String sql = "DELETE FROM Food_Menu WHERE Item_Code= ?";
                PreparedStatement pStmt = con.prepareStatement(sql);
                pStmt.setObject(1,itemCode);


                pStmt.executeUpdate();


                // closing connection
                con.close();
                flag=1;


            } catch(Exception e){
                System.out.println(e);

            }

            if(flag==1)
            {
                System.out.println("Deleted");
                return true;

            }
            else
            {
                System.out.println("Not Deleted");
                return false;
            }


        }
        public void deleteOnAction(ActionEvent actionEvent) {

        try {
            boolean isDelete = deleteItem();
            String tilte;
            String message;

            if (isDelete) {
                (new Alert(Alert.AlertType.CONFIRMATION, "Item Delete Successfully", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Delete Success";
                message = "Item Is Deleted";
               //loadAllItems();
            } else {
                (new Alert(Alert.AlertType.ERROR, "Item Not Delete", new ButtonType[]{ButtonType.OK})).show();
                tilte = "Item Not Found";
                message = "Sorry";
            }
        } catch (SQLException | ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }
//

//
//        ItemDTO searchItem(String id) throws ClassNotFoundException, SQLException{
//
//        }
//
//        ObservableList<ItemDTO> getAllItem() throws ClassNotFoundException, SQLException{
//
//        }
//
//        int getRowCount()throws ClassNotFoundException,SQLException{
//
//        }




//

//
//    private void loadAllItems() {
//        try {
//            ObservableList<ItemDTO> allItem = itemBO.getAllItem();
//            tblItem.setItems(allItem);
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public void setTxtItemId() {
//        try {
//            int id = this.itemBO.getRowCount();
//            if (id == 0) {
//                this.txtItemId.setText("I001");
//            }
//
//            if (id > 0 && id <= 8) {
//                this.txtItemId.setText("I00" + (id + 1));
//            }
//
//            if (id >= 9 && id <= 98) {
//                this.txtItemId.setText("I0" + (id + 1));
//            }
//
//            if (id >= 99) {
//                this.txtItemId.setText("I" + (id + 1));
//            }
//        } catch (ClassNotFoundException | SQLException var2) {
//            var2.printStackTrace();
//        }
//        //customer Count Code
//    }
//
//    public void searchOnAction(ActionEvent actionEvent) {
//        try {
//            String itemID = txtItemId.getText();
//            ItemDTO searchItem = itemBO.searchItem(itemID);
//            if (searchItem != null) {
//                txtItemId.setText(searchItem.getItemCode());
//                txtPackSize.setText(searchItem.getPackSize());
//                TxtQty.setText(searchItem.getQtyOnHand());
//                txtDescription.setText(searchItem.getDescription());
//                txtUnitPrice.setText(searchItem.getUnitPrice());
//                txtSupID.setText(searchItem.getSuplayerID());
//                txtBatchId.setText(searchItem.getBatchID());
//                String tilte = "Item Searched ";
//                String message = "Item Is " + "" + txtItemId.getText() + "Found";
//                tray.notification.TrayNotification tray = new TrayNotification();
//                AnimationType type = AnimationType.POPUP;
//
//                tray.setAnimationType(type);
//                tray.setTitle(tilte);
//                tray.setMessage(message);
//                tray.setNotificationType(NotificationType.SUCCESS);
//                tray.showAndDismiss(Duration.millis(3000));
//
//
//            } else {
//                String tilte = "Searched Item Is Not Found";
//                String message = "Try Again";
//                tray.notification.TrayNotification tray = new TrayNotification();
//                AnimationType type = AnimationType.POPUP;
//
//                tray.setAnimationType(type);
//                tray.setTitle(tilte);
//                tray.setMessage(message);
//                tray.setNotificationType(NotificationType.ERROR);
//                tray.showAndDismiss(Duration.millis(3000));
//            }
//
//        } catch (SQLException | ClassNotFoundException throwables) {
//            throwables.printStackTrace();
//        }
//        //Item Search Is Over(With Notification)
//    }
//
//

//

//
//    public void tblItemOnAction(MouseEvent mouseEvent) {
//
//    }
}