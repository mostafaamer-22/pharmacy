package com.example.pharmacy.Controllers;
import com.example.pharmacy.ControllerUi.ChangePasswordUi;
import com.example.pharmacy.Database.DataBaseManipulation;
import com.example.pharmacy.Models.ChangePasswordModel;
import javafx.scene.control.Alert;

public class ChangePasswordDataBaseController extends ChangePasswordUi {

    public void changePassword()
    {
        ChangePasswordModel userInfoFromUser = getDataFromUser();
        ChangePasswordModel userInfoInDataBase = getUserData(userInfoFromUser);
        if (userInfoInDataBase.getPassword().equals(userInfoFromUser.getPassword()))
        {
            modifyUserPassword(userInfoFromUser);
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.show();
        }
    }

    public ChangePasswordModel getUserData(ChangePasswordModel changePasswordModel)
    {
        return  ManipulationUserController.getEmployeeData(changePasswordModel);
    }

    public void modifyUserPassword(ChangePasswordModel changePasswordModel)
    {
        String query = "update user set password = '"+changePasswordModel.getNewPassword()+"' where ssn = '"+changePasswordModel.getEmployeeSSN()+"'";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        dataBaseManipulation.manipulateDataBase();
    }

}