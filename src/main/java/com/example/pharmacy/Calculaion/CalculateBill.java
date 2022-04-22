package com.example.pharmacy.Calculaion;

import com.example.pharmacy.Exception.Exception;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CalculateBill {

    static public int calculateBill(ResultSet resultSet)
    {
        int billValue = 0;
        try {
                while (resultSet.next())
                {
                    if (resultSet.getInt("amount") != 0 && resultSet.getInt("tapsnumber") != 0)
                    {
                        billValue += calculateBillForAmountAndTapsNumber(resultSet);
                    }
                    else billValue += calculateBillForAmountOrTapsNumber(resultSet);
                }
        }catch (SQLException sqlException)
        {
            System.out.println("here");
            Exception.printingSqlErrors(sqlException);
            return 0;
        }
        return billValue;
    }

    static private int calculateBillForAmountAndTapsNumber(ResultSet resultSet)
    {
        int billValue = 0;
        try {
                billValue += resultSet.getInt("totalprice") * resultSet.getInt("amount");
                billValue += resultSet.getInt("totalprice") / resultSet.getInt("tapsNumberPerCure");
        }catch (SQLException sqlException)
        {
            Exception.printingSqlErrors(sqlException);
        }
        return billValue;
    }

    static private int calculateBillForAmountOrTapsNumber(ResultSet resultSet)
    {
        try {
            if (resultSet.getInt("amount") != 0)
                return calculateBillAmount(resultSet);
            else return calculateBillTapNumber(resultSet);

        }catch (SQLException sqlException)
        {
            Exception.printingSqlErrors(sqlException);
            return 0;
        }
    }

    static private int calculateBillAmount(ResultSet resultSet)
    {
        int billValue = 0;
        try {

                billValue += resultSet.getInt("totalprice") * resultSet.getInt("amount");
        }catch (SQLException sqlException)
        {
            Exception.printingSqlErrors(sqlException);
        }
        return billValue;
    }

    static private int calculateBillTapNumber(ResultSet resultSet)
    {
        int billValue = 0;
        try {

                billValue += resultSet.getInt("totalprice") / resultSet.getInt("tapsNumberPerCure");
        }catch (SQLException sqlException)
        {
            Exception.printingSqlErrors(sqlException);
        }
        return billValue;
    }
}
