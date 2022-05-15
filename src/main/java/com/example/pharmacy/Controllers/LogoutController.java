package com.example.pharmacy.Controllers;
import com.example.pharmacy.Database.DataBaseManipulation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class LogoutController {

    public void handleCancelScreen()
    {
        MainController.instance.handleCancelScreen();
    }


    public void userLogout()
    {
        String query = "select * from userreview";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        deleteUserFromUserReview(dataBaseManipulation.executeStatementSelect());
        MainController.instance.disableButtons();
    }

    public void deleteUserFromUserReview(ResultSet resultSet)
    {
        try {
            while (resultSet.next())
            {
                if (resultSet.isLast())
                {
                    String query = "insert into userreview values('"+resultSet.getString("ssn")+"' , '"+resultSet.getString("fname")+"' , 'logout' , '"+ LocalDate.now()+"' , '"+ LocalTime.now()+"' , '"+resultSet.getString("position")+"')";
                    DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
                    dataBaseManipulation.manipulateDataBase();
                }
            }
        }catch (SQLException sqlException)
        {
            System.out.println(sqlException.getMessage());
        }
    }

}