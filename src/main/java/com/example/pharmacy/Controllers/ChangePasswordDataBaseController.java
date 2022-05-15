package com.example.pharmacy.Controllers;
import com.example.pharmacy.ControllerUi.ChangePasswordUi;
import com.example.pharmacy.Database.DataBaseManipulation;
import com.example.pharmacy.Exception.Exception;
import com.example.pharmacy.Models.ChangePasswordModel;
import javafx.scene.control.Alert;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        ChangePasswordModel userInfo = new ChangePasswordModel();
        String query = "select * from user where ssn = '"+changePasswordModel.getEmployeeSSN()+"'";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        ResultSet resultSet = dataBaseManipulation.executeStatementSelect();
        try {
            if (resultSet.next())
            {
                userInfo.setPassword(resultSet.getString("password"));
            }
        }catch (SQLException sqlException)
        {
            Exception.printingSqlErrors(sqlException);
        }
        return userInfo;
    }

    public void modifyUserPassword(ChangePasswordModel changePasswordModel)
    {
        String query = "update user set password = '"+changePasswordModel.getNewPassword()+"' where ssn = '"+changePasswordModel.getEmployeeSSN()+"'";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        dataBaseManipulation.manipulateDataBase();
    }

}
