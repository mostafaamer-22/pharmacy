package com.example.pharmacy.DataBaseManipulation;

import com.example.pharmacy.DatabaseConnection.DataBaseDriver;

import com.example.pharmacy.Exception.Exception;
import com.example.pharmacy.Sales.SalesModel;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DataBaseManipulation {

    private String queryStatement;
    private ResultSet resultSet;
    ObservableList<SalesModel> salesDataToShow;
    ArrayList listOfShownData = new ArrayList();

    public DataBaseManipulation (String query)
    {
        queryStatement = query;
    }


    public void manipulateDataBase() {

        try {
            if (queryStatement != null)
                DataBaseDriver.statement.execute(queryStatement);
        }catch (SQLException e)
        {
            Exception.printingSqlErrors(e);
        }

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
            salesModel.setCureName(resultSet.getString("curename"));
            salesModel.setTotalPrice(resultSet.getInt("totalprice"));
            salesModel.setRetailPrice(resultSet.getInt("retailprice"));
            salesModel.setCureCode(resultSet.getInt("curecode"));
            salesModel.setAmount(resultSet.getInt("amount"));
            salesModel.setTapsNumber(resultSet.getInt("tapsnumber"));
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

    public  ResultSet executeStatementSelect()
    {
        try {
            if (queryStatement != null)
                resultSet = DataBaseDriver.statement.executeQuery(queryStatement);
            else
                System.out.println("query Statement is Null");
        }catch (java.lang.Exception e)
        {
            System.out.println(e.toString());
        }

        return resultSet;
    }




    public int getLastSaleCodeFromDataBase()
    {
        try {
            int lastSaleCode =  0;
            resultSet = DataBaseDriver.statement.executeQuery(queryStatement);
            while (resultSet.next())
            {
                if(resultSet.getInt("salecodes") > 0 )
                {
                    lastSaleCode = resultSet.getInt("salecodes");
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