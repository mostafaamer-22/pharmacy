package com.example.pharmacy.Controllers;
import com.example.pharmacy.Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    @FXML
    protected TextField SSN;

    @FXML
    protected TextField FName;

    @FXML
    protected TextField LName;

    @FXML
    protected TextField Password;

    @FXML
    protected DatePicker DataOfBirth;

    @FXML
    protected TextField Address;

    @FXML
    protected TextField Contact;

    @FXML
    protected RadioButton male;

    @FXML
    protected RadioButton female;

    @FXML
    protected RadioButton Employee;

    @FXML
    protected RadioButton Doctor;

    @FXML
    protected TextField Salary;

    @FXML
    protected TableView<User> table;

    @FXML
    protected TableColumn<?,?> columnSSN;

    @FXML
    protected TableColumn<?,?> columnFName;

    @FXML
    protected TableColumn<?,?> columnLName;

    @FXML
    protected TableColumn<?,?> columnDateOfBirth;

    @FXML
    protected TableColumn<?,?> columnAddress;

    @FXML
    protected TableColumn<?,?> columnContact;

    @FXML
    protected TableColumn<?,?> columnGender;

    @FXML
    protected TableColumn<?,?> columnPosition;

    @FXML
    protected TableColumn<?,?> columnSalary;

    public ObservableList<User> data;

    ToggleGroup genderGroup;

    ToggleGroup PositionGroup;

    String Gender;

    String Position;

    User user = null;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        genderGroup = new ToggleGroup();
        PositionGroup = new ToggleGroup();
        female.setToggleGroup(genderGroup);
        male.setToggleGroup(genderGroup);
        Employee.setToggleGroup(PositionGroup);
        Doctor.setToggleGroup(PositionGroup);
        data = FXCollections.observableArrayList();
        setUserInCellTable();
        ManipulationUserController.loadDataFromDatabase(data,table);

    }


    public void checkGenderRadioButton()
    {
        if(female.isSelected())
        {
            Gender = "female";
        }
        else{
            Gender = "male";
        }

    }

    public void checkPositionRadioButton()
    {
        if(Doctor.isSelected())
        {
            Position = "Doctor";
        }
        else{
            Position = "Employee";
        }

    }

    public void setUserInCellTable()
    {
        columnSSN.setCellValueFactory(new PropertyValueFactory<>("SSN"));
        columnFName.setCellValueFactory(new  PropertyValueFactory<>("FName"));
        columnLName.setCellValueFactory(new  PropertyValueFactory<>("LName"));
        columnDateOfBirth.setCellValueFactory(new  PropertyValueFactory<>("DateOfBirth"));
        columnAddress.setCellValueFactory(new  PropertyValueFactory<>("Address"));
        columnContact.setCellValueFactory(new  PropertyValueFactory<>("Contact"));
        columnGender.setCellValueFactory(new  PropertyValueFactory<>("Gender"));
        columnPosition.setCellValueFactory(new  PropertyValueFactory<>("Position"));
        columnSalary.setCellValueFactory(new  PropertyValueFactory<>("Salary"));
    }

    public void setManipulationUserInTextField() {
        SSN.setText(String.valueOf(user.getSSN()));
        FName.setText(user.getFName());
        LName.setText(user.getLName());
        DataOfBirth.getEditor().setText(user.getDateOfBirth());
        Address.setText(user.getAddress());
        checkForSetGender();
        checkForSetPosition();
        Contact.setText(user.getContact());
        Salary.setText(user.getSalary());

    }

   public void checkForSetGender()
    {
        if(Objects.equals(user.getGender(), "male"))
        {
            male.setSelected(true);
        }else
        {
            female.setSelected(true);
        }
    }

    public void checkForSetPosition()
    {
        if(Objects.equals(user.getPosition(), "Doctor"))
        {
            Doctor.setSelected(true);
        }else
        {
            Employee.setSelected(true);
        }
    }

    @FXML
    public void clearTextField()
    {
        SSN.clear();
        FName.clear();
        LName.clear();
        DataOfBirth.getEditor().clear();
        Address.clear();
        Contact.clear();
        Salary.clear();
        male.setSelected(false);
        female.setSelected(false);
        Doctor.setSelected(false);
        Employee.setSelected(false);
    }

}
