package com.delicacy.delicacy;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.delicacy.delicacy.CartController.PRICE;

public class rocketController implements Initializable {
    @FXML
    private Button submitBtn;
    @FXML
    private Label amountField;

    @FXML
    private Label amountLabel;

    @FXML
    private TextField numberField;

    @FXML
    private PasswordField pinField;

    @FXML
    private Label pinLabel;

    @FXML
    private Label rocketLabel;

    private Notifications noti = Notifications.create();

    @FXML
    public void onSubmit(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning!");
        alert.setHeaderText("You're about to pay via Nagad ");
        alert.setContentText("Do you want to confirm? ");

        if (alert.showAndWait().get() == ButtonType.OK) {


            noti.text("Payment Processed Successfully!")
                    .hideAfter(Duration.seconds(2))
                    .position(Pos.TOP_CENTER);
            noti.darkStyle();
            noti.show();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        amountField.setText(PRICE.toString());
    }
}
