package com.example.pharmacy;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;


public class HandlerEvent {

    @FXML
    Label billPrice;

   static public void showAlertError()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.show();
    }

    static public void showAlertSuccess()
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.show();
    }

    static public void showAlertNotFound()
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Not Found");
        alert.show();
    }

    public void showBillAlertAndPrice(int billValue)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(String.valueOf(billValue));
        alert.show();
        billPrice.setText(String.valueOf(billValue));
    }

}
