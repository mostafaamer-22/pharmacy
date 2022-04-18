package com.example.pharmacy.Exception;

import java.sql.SQLException;

public  class Exception
{
    static public void printingSqlErrors(SQLException exception)
    {
        exception.fillInStackTrace();
    }

    static public void printingGeneralErrors(Exception exception)
    {
        System.out.println(exception.toString());
    }
}
