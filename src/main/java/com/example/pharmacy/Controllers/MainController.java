package com.example.pharmacy.Controllers;
import com.example.pharmacy.Database.DataBaseManipulation;
import com.example.pharmacy.FxmlLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class MainController implements Initializable{


    public static MainController instance;

    @FXML
    private BorderPane mainPane;

    @FXML
    public ToggleButton newProduct;

    @FXML
    public ToggleButton updateAndDelete;

    @FXML
    public ToggleButton salesProperty;

    @FXML
    public ToggleButton salesReview;

    @FXML
    public ToggleButton addEmployee;

    @FXML
    public ToggleButton userReview;

    @FXML
    private ToggleButton changePassword;


    @FXML
    private ToggleButton Register;


    @FXML
    public void  handleLoadNewProductScreen(){
        try {

            FxmlLoader loader = new FxmlLoader();
            Pane view = loader.getView("View/NewProductFxml.fxml");
            mainPane.setCenter(view);
            System.out.println("success");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void  handleLoadAddUserScreen(ActionEvent event){
        try {

            FxmlLoader loader = new FxmlLoader();
            Pane view = loader.getView("View/AddUser.fxml");
            mainPane.setCenter(view);
            System.out.println("success");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void  handleLoadUserReviewScreen(ActionEvent event){
        try {

            FxmlLoader loader = new FxmlLoader();
            Pane view = loader.getView("View/UserReview.fxml");
            mainPane.setCenter(view);
            System.out.println("success");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void  handleLoadUpdateDeleteScreen(ActionEvent event){
        try {

            FxmlLoader loader = new FxmlLoader();
            Pane view = loader.getView("View/UpdateDelete.fxml");
            mainPane.setCenter(view);
            System.out.println("success");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void  handleLoadHomeScreen(){
        try {

            FxmlLoader loader = new FxmlLoader();
            Pane view = loader.getView("View/Home.fxml");
            mainPane.setCenter(view);
            System.out.println("success");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void  handleLoadLoginScreen(){
        try {

            FxmlLoader loader = new FxmlLoader();
            Pane view = loader.getView("View/Login.fxml");
            mainPane.setCenter(view);
            System.out.println("success");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void  handleLoadSalesPropertyScreen(ActionEvent event){
        try {
            FxmlLoader loader = new FxmlLoader();
            Pane view = loader.getView("View/SalesPropertyFxml.fxml");
            mainPane.setCenter(view);
            System.out.println("success");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void  handleLoadSalesReviewScreen(ActionEvent event){
        try {
            FxmlLoader loader = new FxmlLoader();
            Pane view = loader.getView("View/SalesReview.fxml");
            mainPane.setCenter(view);
            System.out.println("success");

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @FXML
    public void  handleChangePasswordScreen(ActionEvent event){
        try {
            FxmlLoader loader = new FxmlLoader();
            Pane view = loader.getView("View/changepassword.fxml");
            mainPane.setCenter(view);
            System.out.println("success");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void  handleLogoutScreen(ActionEvent event){
        try {

            FxmlLoader loader = new FxmlLoader();
            Pane view = loader.getView("View/Logout.fxml");
            mainPane.setCenter(view);
            System.out.println("success");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void  handleRegisterScreen(ActionEvent event){
        try {

            FxmlLoader loader = new FxmlLoader();
            Pane view = loader.getView("View/Register.fxml");
            mainPane.setCenter(view);
            System.out.println("success");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void  handleCancelScreen(){
        try {

            FxmlLoader loader = new FxmlLoader();
            Pane view = loader.getView("View/Home.fxml");
            mainPane.setCenter(view);
            System.out.println("success");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void disableButtons()
    {
        addEmployee.setDisable(true);
        updateAndDelete.setDisable(true);
        newProduct.setDisable(true);
        salesReview.setDisable(true);
        salesProperty.setDisable(true);
        userReview.setDisable(true);
        changePassword.setDisable(true);

    }

    public void enableButtons(String position)
    {
        if(position.equals("Admin"))
        {
            this.addEmployee.setDisable(false);
            this.updateAndDelete.setDisable(false);
            this.newProduct.setDisable(false);
            this.salesReview.setDisable(false);
            this.salesProperty.setDisable(false);
            this.userReview.setDisable(false);
        }else {
            this.updateAndDelete.setDisable(false);
            this.newProduct.setDisable(false);
            this.salesProperty.setDisable(false);
        }
        this.Register.setDisable(true);
        this.changePassword.setDisable(false);
    }

    public ArrayList<String> getLastUser()
    {
        String query = "select * from userreview";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        return getLastRecord(dataBaseManipulation.executeStatementSelect());
    }

    public ArrayList<String> getLastRecord(ResultSet resultSet)
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        disableButtons();
        if (!getLastUser().isEmpty())
        {
            if (getLastUser().get(0).equals("login"))
            {
                enableButtons(getLastUser().get(1));
            }else {
                disableButtons();
            }
        } else
        {
            disableButtons();
        }
        instance = this;
    }
}
