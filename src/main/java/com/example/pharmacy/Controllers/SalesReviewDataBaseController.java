package com.example.pharmacy.Controllers;
import com.example.pharmacy.Database.DataBaseManipulation;
import com.example.pharmacy.Exception.Exception;
import com.example.pharmacy.Models.SalesReviewModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SalesReviewDataBaseController extends SalesReviewController{

     public void getSalesDataFromDataBase()
    {
        String query = "select cure_code , cure_name , amount , taps_number , retail_price , total_price , ssn_employee , date from sales where date = '"+date.getText()+"'";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        ResultSet resultSet = dataBaseManipulation.executeStatementSelect();
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
