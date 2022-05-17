package com.example.pharmacy.Calculaion;
import com.example.pharmacy.Exception.Exception;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CalculateBill {

    static public int calculateBill(ResultSet resultSet , int tapsNumberPerCure)
    {
        int billValue = 0;
        try {
                while (resultSet.next())
                {
                    if (resultSet.getInt("amount") != 0 && resultSet.getInt("taps_number") != 0)
                    {

                        billValue += calculateBillForAmountAndTapsNumber(resultSet , tapsNumberPerCure);
                    }
                    else {
                        billValue += calculateBillForAmountOrTapsNumber(resultSet , tapsNumberPerCure);
                    }
                }
        }catch (SQLException sqlException)
        {
            System.out.println("here");
            Exception.printingSqlErrors(sqlException);
            return 0;
        }
        return billValue;
    }

    static private int calculateBillForAmountAndTapsNumber(ResultSet resultSet , int tapsNumberPerCure)
    {
        int billValue = 0;
        try {
                billValue += resultSet.getInt("total_price") * resultSet.getInt("amount");
                billValue += resultSet.getInt("total_price") / tapsNumberPerCure;
        }catch (SQLException sqlException)
        {
            Exception.printingSqlErrors(sqlException);
        }
        return billValue;
    }

    static private int calculateBillForAmountOrTapsNumber(ResultSet resultSet , int tapsNumberPerCure)
    {
        try {
            if (resultSet.getInt("amount") != 0)
                return calculateBillAmount(resultSet);
            else return calculateBillTapNumber(resultSet , tapsNumberPerCure);

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
                billValue += resultSet.getInt("total_price") * resultSet.getInt("amount");
        }catch (SQLException sqlException)
        {
            Exception.printingSqlErrors(sqlException);
        }
        return billValue;
    }

    static private int calculateBillTapNumber(ResultSet resultSet , int tapsNumberPerCure)
    {
        int billValue = 0;
        try {
                billValue += resultSet.getInt("total_price") / tapsNumberPerCure;
        }catch (SQLException sqlException)
        {
            Exception.printingSqlErrors(sqlException);
        }
        return billValue;
    }

}
