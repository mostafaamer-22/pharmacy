package com.example.pharmacy.ControllerUi;


import com.example.pharmacy.Database.DataBaseManipulation;
import com.example.pharmacy.HandlerEvent;
import com.example.pharmacy.Models.LoginModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import javax.security.auth.spi.LoginModule;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginUi implements Initializable {


    @FXML
    public TextField employeeSSN;

    @FXML
    public TextField password;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //System.out.println(this.newProduct);
    }


    public LoginModel getDataFromUser()
    {
        if (!employeeSSN.getText().isEmpty() && !password.getText().isEmpty())
        {
            LoginModel loginModel = new LoginModel();
            loginModel.setEmployeeSSN(Integer.parseInt(employeeSSN.getText()));
            loginModel.setPassword(password.getText());
            return loginModel;
        }else {
            HandlerEvent.showAlertError();
            return null;
        }
    }


}
