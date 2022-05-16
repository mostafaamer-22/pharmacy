package com.example.pharmacy.Controllers;

import com.example.pharmacy.Database.DataBaseManipulation;

public class SaleCodesController {

    static public  void changeSaleCodeIntoCodesTable()
    {
        int h = SalesDataBaseController.getLastSaleCode() + 1;
        String query = "insert into codes values('"+h+"')";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        dataBaseManipulation.manipulateDataBase();
    }

    static public  int getLastSaleCodeFromTable()
    {
        String query = "select sale_codes from codes";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        return  dataBaseManipulation.getLastSaleCodeFromDataBase();
    }
}


