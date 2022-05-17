package com.example.pharmacy.ControllerUi;
import com.example.pharmacy.Controllers.MainController;
import com.example.pharmacy.Models.Admin;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class RegisterUi   extends  MainController implements Initializable {

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

    ToggleGroup genderGroup;

    public String Gender;

    Admin admin = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        genderGroup = new ToggleGroup();
        female.setToggleGroup(genderGroup);
        male.setToggleGroup(genderGroup);
    }


    @FXML
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

    @FXML
    public void checkForSetGender()
    {
        if(Objects.equals(admin.getGender(), "male"))
        {
            male.setSelected(true);
        }else
        {
            female.setSelected(true);
        }
    }



}
