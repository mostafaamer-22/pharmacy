package com.example.pharmacy;

import javafx.scene.control.Alert;

public class HandlerEvent {

   static public void showAlertError()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.show();
    }


}
