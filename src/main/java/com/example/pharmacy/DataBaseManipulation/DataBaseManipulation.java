package com.example.pharmacy.DataBaseManipulation;

import com.example.pharmacy.DatabaseConnection.DataBaseDriver;

import com.example.pharmacy.Exception.Exception;

import java.sql.SQLException;

public class DataBaseManipulation {

    private String queryStatement;

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

}