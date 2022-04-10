package com.example.pharmacy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class MainController {

    @FXML
    private BorderPane mainPane;

   @FXML
   public void  handleLoadNewProductScreen(ActionEvent event){
       try {

          FxmlLoader loader = new FxmlLoader();
           Pane view = loader.getView("NewProductFxml.fxml");
           mainPane.setCenter(view);
           System.out.println("success");

       }catch (Exception e){
           System.out.println(e.toString());
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
            System.out.println(e.toString());
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
            System.out.println(e.toString());
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
            System.out.println(e.toString());
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
            System.out.println(e.toString());
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
            System.out.println(e.toString());
        }
    }

}