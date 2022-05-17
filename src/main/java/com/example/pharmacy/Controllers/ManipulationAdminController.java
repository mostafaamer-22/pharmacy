package com.example.pharmacy.Controllers;
import com.example.pharmacy.ControllerUi.RegisterUi;
import com.example.pharmacy.Database.DataBaseManipulation;
import com.example.pharmacy.HandlerEvent;
import com.example.pharmacy.Models.Admin;
import javafx.fxml.FXML;
public class ManipulationAdminController extends RegisterUi {


    @FXML
    public void AddAdmin()
    {
        try {
            Admin admin = Admin.getInstance(Integer.parseInt(SSN.getText()),FName.getText(),LName.getText(),Password.getText(),DataOfBirth.getEditor().getText(),Address.getText(),Contact.getText(),Gender,"Admin");
            String sql = "insert into user values('" + admin.getSSN() + "' , '" + admin.getFName() + "' , '" + admin.getLName() + "' , '" + admin.getPassword() + "' ,'" + admin.getDateOfBirth() + "' , '" + admin.getAddress() + "' , '" + admin.getContact() + "', '" + admin.getGender() + "' , '" + admin.getPosition() + "', '" + "" + "')";
            DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(sql);
            dataBaseManipulation.manipulateDataBase();
        }catch (Exception e){
            HandlerEvent.showAlertError();
            System.out.println(e.getMessage());
        }

    }
}
