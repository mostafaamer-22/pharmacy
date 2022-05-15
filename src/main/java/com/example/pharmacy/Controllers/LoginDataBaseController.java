package com.example.pharmacy.Controllers;

import com.example.pharmacy.ControllerUi.LoginUi;
import com.example.pharmacy.Database.DataBaseManipulation;
import com.example.pharmacy.Exception.Exception;
import com.example.pharmacy.HandlerEvent;
import com.example.pharmacy.Models.LoginModel;


import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDataBaseController extends LoginUi {

    public void getUsersFromDataBase(LoginModel loginModel)
    {
        String query = "select ssn , password from user where ssn = '"+loginModel.getEmployeeSSN()+"' and password = '"+loginModel.getPassword()+"'";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        ResultSet resultSet = dataBaseManipulation.executeStatementSelect();
        checkExistenceOfUser(resultSet);
    }

    public void checkExistenceOfUser(ResultSet resultSet)
    {
        try {
            if (resultSet.next())
            {
                int ssn = resultSet.getInt("ssn");
                UserReviewController.validateUserName(ssn);
                String position = UserReviewController.getLastEmployeeSSNFromUserReview(ssn);
                MainController.instance.enableButtons(position);
            }else
            {
                HandlerEvent.showAlertNotFound();
            }
        }catch (SQLException sqlException)
        {
            Exception.printingSqlErrors(sqlException);
        }
    }

    public void userLogin()
    {
        getUsersFromDataBase(getDataFromUser());
    }

}