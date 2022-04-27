package com.example.pharmacy.Controllers;

import com.example.pharmacy.Database.DataBaseManipulation;
import com.example.pharmacy.Exception.Exception;
import com.example.pharmacy.Models.UserReviewModel;
import javafx.scene.control.Alert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class UserReviewController extends UserReviewUiController{

    static public void insertUser(int ssn)
    {
        if (getUserData(ssn) != null)
        {
           insertUserInUserReviewTable(ssn);
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.show();
        }
    }

    static public void insertUserInUserReviewTable(int ssn)
    {
        String qq = "un";
        String query = "insert into userreview values('"+ssn+"' , '"+getUserData(ssn)+"' , '"+qq+"' , '"+ LocalDate.now().toString()+"' , '"+ LocalTime.now()+"')";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        dataBaseManipulation.manipulateDataBase();
    }

    static  public String getUserData(int ssn)
    {
        String query = "select fname from user where '"+ssn+"'";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        return getFirstName(dataBaseManipulation.executeStatementSelect());
    }

    static public String getFirstName(ResultSet resultSet)
    {
        try {
            if (resultSet.next())
            {
                return resultSet.getString("fname");
            }
        }catch (SQLException sqlException)
        {
            Exception.printingSqlErrors(sqlException);
        }
        return null;
    }

    public void getUsers()
    {
        setTableCells();
        if (!date.getText().isEmpty())
            getUsersFromDataBaseByDate();
    }

    public void getUsersFromDataBse()
    {
        String query = "select * from userreview";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        addUsersToArrayList(dataBaseManipulation.executeStatementSelect());
    }

    public void addUsersToArrayList(ResultSet resultSet)
    {
        userDataToShow.clear();
        try {
            while (resultSet.next())
            {
                userDataToShow.add(new UserReviewModel(resultSet.getInt("ssn") , resultSet.getString("fname"),
                        resultSet.getString("status") , resultSet.getString("date") , resultSet.getString("time")));
            }
        }catch (SQLException sqlException)
        {
            Exception.printingSqlErrors(sqlException);
        }
        tableUsers.setItems(userDataToShow);
    }


    public void getUsersFromDataBaseByDate()
    {
        String query = "select * from userreview where date = '"+date.getText()+"'";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        addUsersToArrayList(dataBaseManipulation.executeStatementSelect());
    }

    public void handleDeletingUserReview()
    {
        if (!date.getText().isEmpty())
        {
            setTableCells();
            userDataToShow.clear();
            deleteSalesByDate();
        }else {
            System.out.println("error");
        }
    }

    public void deleteSalesByDate()
    {
        String query = "delete from userreview where date = '"+date.getText()+"'";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        dataBaseManipulation.manipulateDataBase();
    }

    static  public String getLastEmployeeSSNFromUserReview(int ssn)
    {
        System.out.println(ssn);
        String position = "mostafa";
        String query = "select position from user where ssn = '"+ssn+"'";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        System.out.println(position);
        ResultSet resultSet = dataBaseManipulation.executeStatementSelect();
        try {
            if (resultSet.next())
            {
                System.out.println(resultSet.getString("position"));
                position = resultSet.getString("position");
            }
        }catch (SQLException sqlException)
        {
            Exception.printingSqlErrors(sqlException);
        }
        return position;
    }

}
