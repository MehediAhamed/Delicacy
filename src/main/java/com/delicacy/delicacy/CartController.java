package com.delicacy.delicacy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Collections;
import java.util.ResourceBundle;

import static com.delicacy.delicacy.CustomerMenuController.vec;

public class CartController implements Initializable {


    @FXML
    private TableColumn<CartDTO, String> colITEM;

    @FXML
    private TableColumn<CartDTO, String> colname;

    @FXML
    private TableColumn<CartDTO, String> colprice;

    @FXML
    private TableColumn<CartDTO, Integer> colserial;

    @FXML
    private ImageView imgcod;

    @FXML
    private ImageView imgmb;

    @FXML
    private TableView<CartDTO> tblchk;

    @FXML
    private Label totalp;




    @FXML
    void Cartback(ActionEvent event) throws IOException {
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("view/CustomerMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
    int i = 0;
    private void loaditems() {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://ap-south.connect.psdb.cloud/delicacy?sslMode=VERIFY_IDENTITY",
                    "apike5c6fiy1rsrajmzv",
                    "pscale_pw_9QXgibEIlGrneTReXBvzn80n9OV8HW53gPMndCpo2mB");
            String sql = "select * from Food_Menu";
            Statement stmt = con.createStatement();
            ResultSet rst = stmt.executeQuery(sql);
            ObservableList<CartDTO> allItems = FXCollections.observableArrayList();
            FileWriter writer= new FileWriter("src/main/resources/com/delicacy/delicacy/view/order.txt");
            writer.write("");

            System.out.println(vec);
            Collections.sort(vec);
            int rs_idx=0;
            while(rst.next()) {
                for (; i < vec.size(); i++) {

                    int idx = vec.get(i);


                    while (rs_idx != idx && rs_idx<idx) {
                        rst.next();
                        rs_idx++;
                    }


                    allItems.add(new CartDTO(i+1, rst.getString(1), rst.getString(2), rst.getString(3)));
                    writer.append(rst.getString(2));
                    if(i!=vec.size()-1){
                        writer.append(" ,");
                    }


                }
            }
            writer.close();
            tblchk.setItems(allItems);
            tblchk.refresh();
        }catch (SQLException e) {
            throw new RuntimeException(e);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static Double PRICE=0.00;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        loaditems();

        colITEM.setCellValueFactory(new PropertyValueFactory<>("colITEM"));
        colname.setCellValueFactory(new PropertyValueFactory<>("colname"));
        colprice.setCellValueFactory(new PropertyValueFactory<>("colprice"));
        colserial.setCellValueFactory(new PropertyValueFactory<>("colserial"));

        if(totalp.getText()=="") {
            Double price = 0.00;
            for (int i = 0; i < vec.size(); i++) {
                price += Double.parseDouble(colprice.getCellData(i).toString());
            }
            PRICE=price;

            totalp.setText(price.toString());
        }
        tblchk.refresh();

    }

    public void mobilePayment(MouseEvent event) throws IOException {
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("view/menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();

    }
}
