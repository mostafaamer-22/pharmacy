package com.example.pharmacy.Controllers;
import com.example.pharmacy.FxmlLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class MainController {


    @FXML
    private BorderPane mainPane;

    @FXML
    private BorderPane logPane;



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
    public void  handleLoadHomeScreen(ActionEvent event){
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
    public void  handleCancelScreen(ActionEvent event){
        try {

            FxmlLoader loader = new FxmlLoader();
            Pane view = loader.getView("Home.fxml");
            logPane.setCenter(view);
            System.out.println("success");

        }catch (Exception e){
            e.printStackTrace();
        }
    }



}


