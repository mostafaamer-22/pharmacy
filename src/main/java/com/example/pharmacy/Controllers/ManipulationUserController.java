package com.example.pharmacy.Controllers;
import com.example.pharmacy.Database.DataBaseManipulation;
import com.example.pharmacy.HandlerEvent;
import com.example.pharmacy.Models.Product;
import com.example.pharmacy.Models.User;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.sql.ResultSet;

public class ManipulationUserController extends  UserController{

  @FXML
  TextField Search;

  static ResultSet resultSet;

  @FXML
  public void AddUser()
    {
        User user = new User(Integer.parseInt(SSN.getText()),FName.getText(),LName.getText(),SSN.getText(),DataOfBirth.getEditor().getText(),Address.getText(),Contact.getText(),Gender,Position,Salary.getText());
        String sql = "insert into user values('" + user.getSSN() + "' , '" + user.getFName() + "' , '" + user.getLName() + "' , '" + user.getPassword() + "' ,'" + user.getDateOfBirth() + "' , '" + user.getAddress() + "' , '" + user.getContact() + "', '" + user.getGender() + "' , '" + user.getPosition() + "', '" + user.getSalary() + "')";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(sql);
        dataBaseManipulation.manipulateDataBase();
        setUserInCellTable();
        loadDataFromDatabase(data,table);

    }


    static public void  loadDataFromDatabase(ObservableList<User> data, TableView<User> table)
    {
        data.clear();
        try {
            String sql ="Select * from user";
            DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(sql);
            resultSet = dataBaseManipulation.executeStatementSelect();
            while (resultSet.next())
            {
                data.add(new User(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9),
                        resultSet.getString(10)
                ));
            }
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        table.setItems(data);

    }

    @FXML
    public void searchInDataBase() {
        data.clear();
        try {
            String sql = "select * from user where SSN='" + Search.getText() + "'";
            DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(sql);
            resultSet = dataBaseManipulation.executeStatementSelect();
            if (!resultSet.next()) {
                HandlerEvent.showAlertNotFound();
            } else {
                user = new User(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9),
                        resultSet.getString(10));
                data.add(user);
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
        searchInDataBase();
    }

    @FXML
    public void  deleteUser()
    {
        String sql = "delete from user where ssn = '"+Search.getText()+"'";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(sql);
        dataBaseManipulation.manipulateDataBase();
        setUserInCellTable();
        loadDataFromDatabase(data,table);
    }

    @FXML
    public void Reset()
    {
        setUserInCellTable();
        loadDataFromDatabase(data,table);
    }

}
