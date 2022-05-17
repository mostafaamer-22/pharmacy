package com.example.pharmacy.Exception;

import java.sql.SQLException;

public  class Exception extends Throwable {
    static public void printingSqlErrors(SQLException exception)
    {
        System.out.println(exception.toString());
    }

}
