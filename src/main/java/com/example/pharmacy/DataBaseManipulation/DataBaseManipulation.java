package com.example.pharmacy.DataBaseManipulation;

import com.example.pharmacy.DatabaseConnection.DataBaseDriver;

import com.example.pharmacy.Exception.Exception;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseManipulation {

    private String queryStatement;
    public ResultSet resultSet;

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

}