package com.example.pharmacy.Controllers;
import com.example.pharmacy.Database.DataBaseManipulation;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LogoutController {

    public void handleCancelScreen()
    {
        MainController.instance.handleCancelScreen();
    }


    public void userLogout()
    {
        String query = "select * from userreview";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        insertUserIntoUserReview(dataBaseManipulation.executeStatementSelect());
        MainController.instance.disableButtons();
        MainController.instance.handleLoadLoginScreen();
    }

    public void insertUserIntoUserReview(ResultSet resultSet)
    {
        try {
            while (resultSet.next())
            {
                if (resultSet.isLast())
                {
                    UserReviewController.insertUserAfterLoginOrLogout(resultSet);
                }
            }
        }catch (SQLException sqlException)
        {
            System.out.println(sqlException.getMessage());
        }
    }

}