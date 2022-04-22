package com.example.pharmacy.Controllers;
import com.example.pharmacy.Models.SalesModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class SalesController extends MainController implements Initializable{

    @FXML
    public TextField cureCode;

    @FXML
     public TextField cureName;

    @FXML
     public TextField amount;

    @FXML
     public TextField tapsNumber;

    @FXML
     public TextField retailPrice;

    @FXML
     public TextField totalPrice;

    @FXML
   public TextField saleCode;

    @FXML
   public TableView<SalesModel> tableSales;

    @FXML
     public TableColumn<? , ?> cureCodeColumn;

    @FXML
    public TableColumn<? , ?> cureNameColumn;

    @FXML
    public TableColumn<? , ?> amountColumn;

    @FXML
   public TableColumn<? , ?> tapsNumberColumn;

    @FXML
   public TableColumn<? , ?> retailPriceColumn;

    @FXML
  public TableColumn<? , ?>  totalPriceColumn;

    public ObservableList<SalesModel> salesDataToShow;

    @FXML
    public Label billPrice;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("hello");
        salesDataToShow = FXCollections.observableArrayList();
        setTableCells();
    }


    public void clearTextFieldDataAndTableData()
    {
        cureCode.clear();
        cureName.clear();
        amount.clear();
        tapsNumber.clear();
        retailPrice.clear();
        totalPrice.clear();
        salesDataToShow.clear();
    }

    public  void setTableCells()
    {
        cureCodeColumn.setCellValueFactory(new PropertyValueFactory<>("cureCode"));
        cureNameColumn.setCellValueFactory(new PropertyValueFactory<>("cureName"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        tapsNumberColumn.setCellValueFactory(new PropertyValueFactory<>("tapsNumber"));
        retailPriceColumn.setCellValueFactory(new PropertyValueFactory<>("retailPrice"));
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
    }



     public  SalesModel getDataFromUser()
    {
        SalesModel saleInformation = new SalesModel();
        if (!cureCode.getText().isEmpty())
        {
            saleInformation.setCureCode(Integer.parseInt(cureCode.getText()));
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.show();
        }
        if (!amount.getText().isEmpty())
            saleInformation.setAmount(Integer.parseInt(amount.getText()));
        else saleInformation.setAmount(0);
        if (!tapsNumber.getText().isEmpty())
        {
            saleInformation.setTapsNumber(Integer.parseInt(tapsNumber.getText()));
        } else
            saleInformation.setTapsNumber(0);
        return saleInformation;
    }


    public  void putDataToUser(ArrayList<SalesModel> list)
    {
        String x = String.valueOf(list.get(0).getTotalPrice());
        String y = String.valueOf(list.get(0).getRetailPrice());
        totalPrice.setText(x);
        retailPrice.setText(y);
        cureName.setText(list.get(0).getCureName());
    }



}