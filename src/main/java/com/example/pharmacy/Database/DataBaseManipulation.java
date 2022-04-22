package com.example.pharmacy.Database;
import com.example.pharmacy.Exception.Exception;
import com.example.pharmacy.HandlerEvent;
import com.example.pharmacy.Models.SalesModel;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataBaseManipulation {

    final String queryStatement;
    public ResultSet resultSet;
    ObservableList<SalesModel> salesDataToShow;
    ArrayList listOfShownData = new ArrayList();
    public DataBaseManipulation (String query)
    {
        queryStatement = query;
    }

    public void manipulateDataBase() {

        try {
            if (queryStatement != null){
                DataBaseDriver.statement.execute(queryStatement);
                HandlerEvent.showAlertSuccess();
            }
        }catch (SQLException e)
        {
            HandlerEvent.showAlertError();
            Exception.printingSqlErrors(e);
        }

    }

    public  ResultSet executeStatementSelect()
    {
        try {
            if (queryStatement != null)
               resultSet = DataBaseDriver.statement.executeQuery(queryStatement);
            else
                System.out.println("query Statement is Null");
        }catch (java.lang.Exception e)
        {
            System.out.println(e.getMessage());
        }

        return resultSet;
    }

    public ArrayList showData()
    {
        getDataFromDataBase();
        addItemToArrayList(makeObjectFromSalesModel());
        return listOfShownData;
    }

    public ArrayList addItemToArrayList(SalesModel item)
    {
        listOfShownData.add(item);
        return listOfShownData;
    }

    public SalesModel makeObjectFromSalesModel()
    {
        SalesModel salesModel = new SalesModel();
        try {
            resultSet.next();
            salesModel.setCureName(resultSet.getString("cure_name"));
            salesModel.setTotalPrice(resultSet.getInt("total_price"));
            salesModel.setRetailPrice(resultSet.getInt("retail_price"));
            salesModel.setCureCode(resultSet.getInt("cure_code"));
            salesModel.setAmount(resultSet.getInt("amount"));
            salesModel.setTapsNumber(resultSet.getInt("taps_number"));
            return salesModel;
        }catch (SQLException sqlException)
        {
            Exception.printingSqlErrors(sqlException);
        }
        return null;
    }

    public void getDataFromDataBase()
    {
        try {
            resultSet = DataBaseDriver.statement.executeQuery(queryStatement);
        }catch (SQLException sqlException)
        {
            Exception.printingSqlErrors(sqlException);
        }
    }



    public int getLastSaleCodeFromDataBase()
    {
        try {
            int lastSaleCode =  0;
            resultSet = DataBaseDriver.statement.executeQuery(queryStatement);
            while (resultSet.next())
            {
                if(resultSet.getInt("sale_codes") > 0 )
                {
                    lastSaleCode = resultSet.getInt("sale_codes");
                }
            }
            return lastSaleCode;
        }catch (SQLException sqlException)
        {
            Exception.printingSqlErrors(sqlException);
        }
        return 1;
    }

}