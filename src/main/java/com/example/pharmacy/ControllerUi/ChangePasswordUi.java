package com.example.pharmacy.ControllerUi;
import com.example.pharmacy.HandlerEvent;
import com.example.pharmacy.Models.ChangePasswordModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;


public class ChangePasswordUi {

    @FXML
    public TextField employeeSSN;

    @FXML
    public TextField password;

    @FXML
    public TextField newPassword;

    public ChangePasswordModel getDataFromUser()
    {
        if (!employeeSSN.getText().isEmpty() || !password.getText().isEmpty() || !newPassword.getText().isEmpty())
        {
            ChangePasswordModel changePasswordModel = new ChangePasswordModel();
            changePasswordModel.setEmployeeSSN(Integer.parseInt(employeeSSN.getText()));
            changePasswordModel.setPassword(password.getText());
            changePasswordModel.setNewPassword(newPassword.getText());
            return changePasswordModel;
        }else {
            HandlerEvent.showAlertError();
            return null;
        }
    }
}
