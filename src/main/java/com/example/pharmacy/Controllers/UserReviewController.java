package com.example.pharmacy.Controllers;
import com.example.pharmacy.ControllerUi.UserReviewUi;
import com.example.pharmacy.Database.DataBaseManipulation;
import com.example.pharmacy.Exception.Exception;
import com.example.pharmacy.Models.UserReviewModel;
import javafx.scene.control.Alert;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class UserReviewController extends UserReviewUi {

    static public void validateUserName(int ssn)
    {
        if (!getUserData(ssn).isEmpty())
        {
            insertUserInUserReviewTable(ssn);
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.show();
        }
    }

    static public void insertUserInUserReviewTable(int ssn)
    {
        String login = "login";
        ArrayList<String> info = new ArrayList<String>();
        info = getUserData(ssn);
        String query = "insert into userreview values('"+ssn+"' , '"+info.get(0)+"' , '"+login+"' , '"+ LocalDate.now().toString()+"' , '"+ LocalTime.now()+"' , '"+info.get(1)+"')";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        dataBaseManipulation.manipulateDataBase();
    }

    static  public ArrayList<String> getUserData(int ssn)
    {
        String query = "select fname , position from user where ssn = '"+ssn+"'";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        return appendFirstNameAndPosition(dataBaseManipulation.executeStatementSelect());
    }

    static public ArrayList<String> appendFirstNameAndPosition(ResultSet resultSet)
    {
        ArrayList<String> userInfo = new ArrayList<String>();
        try {
            if (resultSet.next())
            {
                userInfo.add(resultSet.getString("fname"));
                userInfo.add(resultSet.getString("position"));
            }
        }catch (SQLException sqlException)
        {
            Exception.printingSqlErrors(sqlException);
        }
        return userInfo;
    }

    public void getUsers()
    {
        setTableCells();
        if (!date.getText().isEmpty())
            getUsersFromDataBaseByDate();
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
            deleteUserByDate();
        }else {
            System.out.println("error");
        }
    }

    public void deleteUserByDate()
    {
        String query = "delete from userreview where date = '"+date.getText()+"'";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        dataBaseManipulation.manipulateDataBase();
    }

    static  public String getLastEmployeeSSNFromUserReview(int ssn)
    {
        String query = "select position from user where ssn = '"+ssn+"'";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        ResultSet resultSet = dataBaseManipulation.executeStatementSelect();
        return getPositionOfUser(resultSet);
    }

    public static String getPositionOfUser(ResultSet resultSet)
    {
        String position = null;
        try {
            if (resultSet.next())
            {
                position = resultSet.getString("position");
            }
        }catch (SQLException sqlException)
        {
            Exception.printingSqlErrors(sqlException);
        }
        return position;
    }

}