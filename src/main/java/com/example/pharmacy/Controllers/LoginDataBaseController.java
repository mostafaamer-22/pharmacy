package com.example.pharmacy.Controllers;

import com.example.pharmacy.Database.DataBaseManipulation;
import com.example.pharmacy.Exception.Exception;
import com.example.pharmacy.Models.LoginModel;


import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDataBaseController extends LoginController{

    public void getUsersFromDataBase(LoginModel loginModel)
    {
        String query = "select ssn , password from user where ssn = '"+loginModel.getEmployeeSSN()+"' and password = '"+loginModel.getPassword()+"'";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        ResultSet resultSet = dataBaseManipulation.executeStatementSelect();
        System.out.println("hello");
        try {
            if (resultSet.next())
            {
                int ssn = resultSet.getInt("ssn");
                UserReviewController.insertUser(ssn);
                String position = UserReviewController.getLastEmployeeSSNFromUserReview(ssn);
                System.out.println(position);
                MainController.instance.enableButtons(position);
            }else
            {
                System.out.println("error");
            }
        }catch (SQLException sqlException)
        {
            Exception.printingSqlErrors(sqlException);
        }
    }

    public void userLogin()
    {
        LoginModel loginModel = new LoginModel();
        loginModel = getDataFromUser();
        System.out.println(loginModel.getEmployeeSSN());
        System.out.println(loginModel.getPassword());
        getUsersFromDataBase(loginModel);
    }



}
