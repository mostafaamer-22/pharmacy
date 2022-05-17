package com.example.pharmacy.Controllers;
import com.example.pharmacy.ControllerUi.ChangePasswordUi;
import com.example.pharmacy.Database.DataBaseManipulation;
import com.example.pharmacy.HandlerEvent;
import com.example.pharmacy.Models.ChangePasswordModel;

public class ChangePasswordDataBaseController extends ChangePasswordUi {

    public void changePassword()
    {
        ChangePasswordModel userInfoFromUser = getDataFromUser();
        ChangePasswordModel userInfoInDataBase = getUserData(userInfoFromUser);
        if (userInfoInDataBase.getPassword().equals(userInfoFromUser.getPassword()))
        {
            modifyUserPassword(userInfoFromUser);
        }else {
            HandlerEvent.showAlertError();
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