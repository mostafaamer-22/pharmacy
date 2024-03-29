package com.example.pharmacy.Controllers;
import com.example.pharmacy.ControllerUi.UserUi;
import com.example.pharmacy.Database.DataBaseManipulation;
import com.example.pharmacy.HandlerEvent;
import com.example.pharmacy.Interfaces.ResetDataTable;
import com.example.pharmacy.Models.ChangePasswordModel;
import com.example.pharmacy.Models.User;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManipulationUserController extends UserUi implements ResetDataTable {


  @FXML
  TextField Search;

 public static ResultSet resultSet;

 public  static  ResultSet UsersData;

    public static User CreateObjectFromUser(ResultSet resultSet)
    {
        try {
            user = new User(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9),
                    resultSet.getString(10)
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  user;
    }


    @FXML
  public void AddUser()
    {
        User user = new User(Integer.parseInt(SSN.getText()),FName.getText(),LName.getText(),"12345",DataOfBirth.getEditor().getText(),Address.getText(),Contact.getText(),Gender,Position,Salary.getText());
        String sql = "insert into user values('" + user.getSSN() + "' , '" + user.getFName() + "' , '" + user.getLName() + "' , '" + user.getPassword() + "' ,'" + user.getDateOfBirth() + "' , '" + user.getAddress() + "' , '" + user.getContact() + "', '" + user.getGender() + "' , '" + user.getPosition() + "', '" + user.getSalary() + "')";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(sql);
        dataBaseManipulation.manipulateDataBase();
        SetDataInTable();
        loadUsersToTable(data,table);

    }

   static  public  ResultSet loadUsersFromDataBase()
   {
       String sql ="Select * from user";
       DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(sql);
       UsersData = dataBaseManipulation.executeStatementSelect();
       return UsersData;
    }

    static public void loadUsersToTable(ObservableList<User> data, TableView<User> table)
    {
        data.clear();
        try {
              resultSet = loadUsersFromDataBase();
            while (resultSet.next())
            {
                data.add(CreateObjectFromUser(resultSet));
            }
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        table.setItems(data);

    }




    @FXML
    public void searchUserInDataBase() {
        data.clear();
        try {
            String sql = "select * from user where SSN='" + Search.getText() + "'";
            DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(sql);
            resultSet = dataBaseManipulation.executeStatementSelect();
            if (!resultSet.next()) {
                HandlerEvent.showAlertNotFound();
            } else {
                data.add(CreateObjectFromUser(resultSet));
                setManipulationUserInTextField();

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        table.setItems(data);

    }


    @FXML
    public void updateUser() {
        String sql = "update user set  fname='" + FName.getText() + "',lname='"+LName.getText()+"',dateofbirth='"+DataOfBirth.getEditor().getText()+"',address='" + Address.getText() + "',contact='" + Contact.getText() + "',gender='" + Gender + "',position='" + Position + "',salary='" + Salary.getText() + "'where ssn = '"+Search.getText()+"'";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(sql);
        dataBaseManipulation.manipulateDataBase();
        searchUserInDataBase();
    }

    @FXML
    public void  deleteUser()
    {
        String sql = "delete from user where ssn = '"+Search.getText()+"'";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(sql);
        dataBaseManipulation.manipulateDataBase();
        SetDataInTable();
        loadUsersToTable(data,table);
    }

    @FXML
    @Override
    public void ResetTable()
    {
        SetDataInTable();
        loadUsersToTable(data,table);
    }

    static public ChangePasswordModel getEmployeeData(ChangePasswordModel changePasswordModel)
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
            System.out.println(sqlException.getMessage());
        }
        return userInfo;
    }

    static public ArrayList<String> getNameAndPosition(int ssn)
    {
        String query = "select fname , position from user where ssn = '"+ssn+"'";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        return UserReviewController.appendFirstNameAndPosition(dataBaseManipulation.executeStatementSelect());
    }

}
