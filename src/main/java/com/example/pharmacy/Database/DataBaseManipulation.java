package com.example.pharmacy.Database;
import com.example.pharmacy.Exception.Exception;
import com.example.pharmacy.HandlerEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseManipulation {

    final String queryStatement;
    public ResultSet resultSet;

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

}