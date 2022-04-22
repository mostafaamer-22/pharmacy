package com.example.pharmacy.Controllers;

import com.example.pharmacy.DataBaseManipulation.DataBaseManipulation;
import com.example.pharmacy.Exception.Exception;
import com.example.pharmacy.Sales.SalesReviewModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SalesReviewDataBaseController extends SalesReviewController{

     public void getSalesDataFromDataBase()
    {
        String query = "select curecode , curename , amount , tapsnumber , retailprice , totalprice , ssnemployee , date from sales where date = '"+date.getText()+"'";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        ResultSet resultSet = dataBaseManipulation.executeStatementSelect();
        try {
            while (resultSet.next()) {

                salesDataToShow.add(new SalesReviewModel(
                        resultSet.getInt("curecode"),resultSet.getString("curename"),resultSet.getInt("amount"),
                        resultSet.getInt("tapsnumber"), resultSet.getString("date"), resultSet.getInt("retailprice"),
                        resultSet.getInt("totalprice"),resultSet.getInt("ssnemployee"))
                );
            }
        }catch (SQLException sqlException)
        {
            Exception.printingSqlErrors(sqlException);
        }
        if (!salesDataToShow.isEmpty())
            tableSales.setItems(salesDataToShow);
    }

    public void SearchForSalesByData()
    {
        if (!date.getText().isEmpty())
        {
            setTableCells();
            salesDataToShow.clear();
            getSalesDataFromDataBase();
        }else {
            showAlert();
        }
    }
}
