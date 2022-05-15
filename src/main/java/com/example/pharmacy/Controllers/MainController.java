package com.example.pharmacy.Controllers;
import com.example.pharmacy.FxmlLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.nio.Buffer;
import java.util.ResourceBundle;

public class MainController implements Initializable{


    public static MainController instance;

    @FXML
    private BorderPane mainPane;

    @FXML
    private BorderPane logPane;

    @FXML
    private ToggleButton newProduct;

    @FXML
    private ToggleButton updateAndDelete;

    @FXML
    private ToggleButton salesProperty;

    @FXML
    private ToggleButton salesReview;

    @FXML
    private ToggleButton addEmployee;

    @FXML
    private ToggleButton userReview;

    @FXML
    private ToggleButton changePassword;


   @FXML
   public void  handleLoadNewProductScreen(ActionEvent event){
       try {

          FxmlLoader loader = new FxmlLoader();
           Pane view = loader.getView("NewProductFxml.fxml");
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
            Pane view = loader.getView("AddUser.fxml");
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
            Pane view = loader.getView("UserReview.fxml");
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
            Pane view = loader.getView("UpdateDelete.fxml");
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
            Pane view = loader.getView("Home.fxml");
            mainPane.setCenter(view);
            System.out.println("success");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void  handleLoadLoginScreen(ActionEvent event){
        try {

            FxmlLoader loader = new FxmlLoader();
            Pane view = loader.getView("Login.fxml");
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
            Pane view = loader.getView("SalesPropertyFxml.fxml");
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
            Pane view = loader.getView("SalesReview.fxml");
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
            Pane view = loader.getView("changepassword.fxml");
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
            Pane view = loader.getView("Logout.fxml");
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
            Pane view = loader.getView("Home.fxml");
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
        System.out.println("hello");
        if (position.equals("admin"))
        {
            this.addEmployee.setDisable(false);
            this.updateAndDelete.setDisable(false);
            this.newProduct.setDisable(false);
            this.salesReview.setDisable(false);
            this.salesProperty.setDisable(false);
            this.userReview.setDisable(false);
            this.changePassword.setDisable(false);
        }else {
            this.updateAndDelete.setDisable(false);
            this.newProduct.setDisable(false);
            this.salesProperty.setDisable(false);
            this.changePassword.setDisable(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        disableButtons();
        instance = this;
    }
}


