package com.example.pharmacy.Controllers;
import com.example.pharmacy.ControllerUi.UserReviewUi;
import com.example.pharmacy.Database.DataBaseManipulation;
import com.example.pharmacy.Exception.Exception;
import com.example.pharmacy.HandlerEvent;
import com.example.pharmacy.Models.UserReviewModel;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class UserReviewController extends UserReviewUi implements Initializable {


    static String position;

    static public void validateUserName(int ssn)
    {
        if (!getUserData(ssn).isEmpty())
        {
            insertUserInUserReviewTable(ssn);
        }else {
            HandlerEvent.showAlertError();
        }
    }

    static public void insertUserInUserReviewTable(int ssn)
    {
        String login = "login";
        ArrayList<String> info;
        info = getUserData(ssn);
        String query = "insert into userreview values('"+ssn+"' , '"+info.get(0)+"' , '"+login+"' , '"+ LocalDate.now() +"' , '"+ LocalTime.now()+"' , '"+info.get(1)+"')";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        dataBaseManipulation.manipulateDataBase();
    }

    static  public ArrayList<String> getUserData(int ssn)
    {
        return  ManipulationUserController.getNameAndPosition(ssn);
    }

    static public ArrayList<String> appendFirstNameAndPosition(ResultSet resultSet)
    {
        ArrayList<String> userInfo;
        userInfo = new ArrayList<>();
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
        SetDataInTable();
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
            SetDataInTable();
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
        position = null;
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

    static  public void insertUserAfterLoginOrLogout(ResultSet resultSet)
    {
        try {
            String query = "insert into userreview values('"+resultSet.getString("ssn")+"' , '"+resultSet.getString("fname")+"' , 'logout' , '"+ LocalDate.now()+"' , '"+ LocalTime.now()+"' , '"+resultSet.getString("position")+"')";
            DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
            dataBaseManipulation.manipulateDataBase();
        }catch (SQLException sqlException)
        {
            System.out.println(sqlException.getMessage());
        }
    }

    static public int getLastEmployeeSSN()
    {
        String query = "select ssn from userreview";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        ResultSet resultSet = dataBaseManipulation.executeStatementSelect();
        return extractEmployeeSSNFromResultSet(resultSet);
    }

    static public int extractEmployeeSSNFromResultSet(ResultSet resultSet)
    {
        try {
            while (resultSet.next())
            {
                if(resultSet.isLast())
                    return resultSet.getInt("ssn");
            }
        }catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        userDataToShow = FXCollections.observableArrayList();
        SetDataInTable();
        getAllUserReviewData();
    }

    public void getAllUserReviewData()
    {
        String query = "select * from userreview";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        ResultSet resultSet = dataBaseManipulation.executeStatementSelect();
        addUsersToArrayList(resultSet);
        if (!userDataToShow.isEmpty())
            tableUsers.setItems(userDataToShow);
    }

    static public ArrayList<String> getLastUser()
    {
        String query = "select * from userreview";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        return getLastRecordInUserReview(dataBaseManipulation.executeStatementSelect());
    }

    static public ArrayList<String> getLastRecordInUserReview(ResultSet resultSet)
    {
        ArrayList<String> informationAboutUser = new ArrayList<>();
        try {
            while (resultSet.next())
            {
                if (resultSet.isLast())
                {
                    informationAboutUser.add(resultSet.getString("status"));
                    informationAboutUser.add(resultSet.getString("position"));
                }
            }
        }catch (SQLException sqlException)
        {
            System.out.println(sqlException.getMessage());
        }
        return informationAboutUser;
    }
}