package com.example.pharmacy.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ProductController extends  MainController{

    @FXML
    private TextField CureCode;

   public void  PrintCureCode()
    {
        System.out.println(CureCode.getText());
    }

}
