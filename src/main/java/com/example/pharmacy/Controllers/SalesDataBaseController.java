package com.example.pharmacy.Controllers;

import com.example.pharmacy.Calculaion.CalculateBill;
import com.example.pharmacy.DataBaseManipulation.DataBaseManipulation;
import com.example.pharmacy.Exception.Exception;
import com.example.pharmacy.Sales.SalesModel;


import javafx.scene.control.*;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class SalesDataBaseController extends SalesController{


    public void getDataFromDataBase()
    {
        ArrayList<SalesModel> listOfData = new ArrayList<>();
        SalesModel salesModel;
        salesModel = getDataFromUser();
        if (!cureCode.getText().isEmpty())
        {
            listOfData = getCureDataFromDataBase(salesModel);
            putDataToUser(listOfData);
            if((salesModel.getAmount() <= listOfData.get(0).getAmount() && salesModel.getAmount() != 0) || (salesModel.getTapsNumber() <= listOfData.get(0).getTapsNumber() && salesModel.getTapsNumber() != 0))
            {
                insertSales(listOfData.get(0) , getLastSaleCode() , salesModel);
                updateAmountOfCure(listOfData.get(0).getAmount() - salesModel.getAmount() , listOfData.get(0).getCureCode());
                setTableCells();
                salesDataToShow.clear();
                getSalesDataFromDataBase(getLastSaleCode());
            }
            else {
                showAlertError();
            }
        }
    }

    public  void updateAmountOfCure(int amount, int cureCode)
    {
        String query = "update product set amount = '"+amount+"' where curecode = '"+cureCode+"'";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        dataBaseManipulation.manipulateDataBase();
    }

    public  void changeSaleCode()
    {
        int h = getLastSaleCode() + 1;
        String query = "insert into codes values('"+h+"')";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        dataBaseManipulation.manipulateDataBase();
    }

    public  int getLastSaleCode()
    {
        String query = "select salecodes from codes";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        return  dataBaseManipulation.getLastSaleCodeFromDataBase();
    }


    public  void showAlertError()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.show();
    }

    private  void insertSales(SalesModel saleInformation, int saleCode, SalesModel salesModel)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println(salesModel.getTapsNumber());
        String query = "insert into sales values('"+saleInformation.getCureCode()+"' , '"+saleInformation.getCureName()+"' , '"+salesModel.getAmount()+"' , '"+salesModel.getTapsNumber()+"' ,'"+saleInformation.getRetailPrice()+"' , '"+saleInformation.getTotalPrice()+"' , '"+ LocalDate.now().toString() +
        "' , 1 , '"+saleCode+"' , '"+saleInformation.getTapsNumber()+"')";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        dataBaseManipulation.manipulateDataBase();
    }

    public  ArrayList getCureDataFromDataBase(SalesModel salesModel)
    {
        String query = "select * from product where curecode = '"+salesModel.getCureCode()+"'";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        ArrayList listOfData = dataBaseManipulation.showData();
        if (listOfData != null)
        {
            return listOfData;
        }else {
            showAlertError();
            return null;
        }
    }

    public  void getSalesDataFromDataBase(int condition)
    {
        String query = "select curecode , curename , amount , tapsnumber , retailprice , totalprice from sales where salecode = '"+condition+"'";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        ResultSet resultSet = dataBaseManipulation.executeStatementSelect();
        salesDataToShow.clear();
        try {
            while (resultSet.next())
            {
                salesDataToShow.add(SalesModel.makeObject(resultSet.getInt("curecode") ,
                        resultSet.getString("curename") , resultSet.getInt("amount") , resultSet.getInt("tapsnumber") ,
                        resultSet.getInt("retailprice"),resultSet.getInt("totalprice")));
            }
        }catch (SQLException sqlException)
        {
            Exception.printingSqlErrors(sqlException);
        }
        tableSales.setItems(salesDataToShow);
    }


    public  void calculateBillToUser()
    {
        calculateBill(getLastSaleCode());
    }

    public  void calculateBill(int condition)
    {
        String query = "select totalprice , tapsnumber , amount , tapsNumberPerCure from sales where salecode = '"+condition+"'";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        ResultSet resultSet = dataBaseManipulation.executeStatementSelect();
        showBillAlertAndPrice(CalculateBill.calculateBill(resultSet));
    }

    public  void showBillAlertAndPrice(int billValue)
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(String.valueOf(billValue));
        alert.show();
        billPrice.setText(String.valueOf(billValue));
    }

    public  void handleUpdateDataOfBill()
    {
        if (amount.getText() != null && cureCode.getText() != null && saleCode.getText() != null)
        {
            SalesModel salesModel  = new SalesModel();
            ArrayList<SalesModel> listOfCureData = new ArrayList<>();
            salesModel.setCureCode(Integer.parseInt(cureCode.getText()));
            listOfCureData = getCureDataFromDataBase(salesModel);
            if(listOfCureData.get(0).getAmount() >= Integer.parseInt(amount.getText()))
                updateDataOfBill();
        }else {
            showAlertError();
        }
    }

    public  void updateDataOfBill()
    {
        String query = "update sales set amount = '"+Integer.parseInt(amount.getText())+"' where salecode = '"+Integer.parseInt(saleCode.getText())+"' and curecode = '"+Integer.parseInt(cureCode.getText())+"'";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        dataBaseManipulation.manipulateDataBase();
        getSalesDataFromDataBase(getLastSaleCode());
    }

    public  void handleDeleteDataOfBill()
    {
        if (cureCode.getText() != null && saleCode.getText() != null)
        {
            DeleteDataOfBill();
            updateSaleCode();
        }
    }

    public  void updateSaleCode()
    {
        String query = "select salecode from sales where salecode = '"+Integer.parseInt(saleCode.getText())+"'";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        ResultSet resultSet = dataBaseManipulation.executeStatementSelect();
        try {
            if(!resultSet.next())
            {
                deleteSaleCode();
            }
        }catch (SQLException sqlException){
            Exception.printingSqlErrors(sqlException);
        }
    }

    public  void deleteSaleCode()
    {
        String query = "delete from codes where salecodes = '"+Integer.parseInt(saleCode.getText())+"'";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        dataBaseManipulation.manipulateDataBase();
    }

    public  void DeleteDataOfBill()
    {
        String query = "delete from sales where salecode = '"+Integer.parseInt(saleCode.getText())+"' and curecode = '"+Integer.parseInt(cureCode.getText())+"'";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        dataBaseManipulation.manipulateDataBase();
        getSalesDataFromDataBase(getLastSaleCode());
    }

}
