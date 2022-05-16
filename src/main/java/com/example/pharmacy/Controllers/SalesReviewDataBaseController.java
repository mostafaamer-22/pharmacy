package com.example.pharmacy.Controllers;
import com.example.pharmacy.ControllerUi.SalesReviewUi;
import com.example.pharmacy.Database.DataBaseManipulation;
import com.example.pharmacy.Exception.Exception;
import com.example.pharmacy.HandlerEvent;
import com.example.pharmacy.Models.SalesReviewModel;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SalesReviewDataBaseController extends SalesReviewUi implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        salesDataToShow = FXCollections.observableArrayList();
        SetDataInTable();
        getAllSalesData();
    }

    public void getSalesDataFromDataBaseByDate()
    {
        salesDataToShow.clear();
        String query = "select cure_code , cure_name , amount , taps_number , retail_price , total_price , ssn_employee , date from sales where date = '"+date.getText()+"'";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        ResultSet resultSet = dataBaseManipulation.executeStatementSelect();
        addDataToList(resultSet);
        if (!salesDataToShow.isEmpty())
            tableSales.setItems(salesDataToShow);
    }

    public void getAllSalesData()
    {
        String query = "select cure_code , cure_name , amount , taps_number , retail_price , total_price , ssn_employee , date from sales";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        ResultSet resultSet = dataBaseManipulation.executeStatementSelect();
        addDataToList(resultSet);
        if (!salesDataToShow.isEmpty())
            tableSales.setItems(salesDataToShow);
    }

    public void addDataToList(ResultSet resultSet)
    {
        try {
            while (resultSet.next()) {

                salesDataToShow.add(new SalesReviewModel(
                        resultSet.getInt("cure_code"),resultSet.getString("cure_name"),resultSet.getInt("amount"),
                        resultSet.getInt("taps_number"), resultSet.getString("date"), resultSet.getInt("retail_price"),
                        resultSet.getInt("total_price"),resultSet.getInt("ssn_employee"))
                );
            }
        }catch (SQLException sqlException)
        {
            Exception.printingSqlErrors(sqlException);
        }
    }

    public void SearchForSalesByData()
    {
        if (!date.getText().isEmpty())
        {
            SetDataInTable();
            salesDataToShow.clear();
            getSalesDataFromDataBaseByDate();
        }else {
            HandlerEvent.showAlertError();
        }
    }

    public void handleDeletingSales()
    {
        if (!date.getText().isEmpty())
        {
            SetDataInTable();
            salesDataToShow.clear();
            deleteSalesByDate();
        }else {
            HandlerEvent.showAlertError();
        }
    }

    public void deleteSalesByDate()
    {
        String query = "delete from sales where date = '"+date.getText()+"'";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        dataBaseManipulation.manipulateDataBase();
    }
}