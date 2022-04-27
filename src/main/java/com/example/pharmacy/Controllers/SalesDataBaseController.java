package com.example.pharmacy.Controllers;
import com.example.pharmacy.Calculaion.CalculateBill;
import com.example.pharmacy.Database.DataBaseManipulation;
import com.example.pharmacy.Exception.Exception;
import com.example.pharmacy.Models.Product;
import com.example.pharmacy.Models.SalesModel;
import javafx.scene.control.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class SalesDataBaseController extends SalesController{


    public void getDataFromDataBase()
    {
        ArrayList<Product> listOfData = new ArrayList<>();
        SalesModel salesModel;
        salesModel = getDataFromUser();
        if (!cureCode.getText().isEmpty())
        {
            listOfData = getCureDataFromDataBase(salesModel.getCureCode());
            putDataToUser(listOfData);
            if(checkTheStore(salesModel , listOfData))
            {
                insertSales(listOfData.get(0) , getLastSaleCode() , salesModel);
                updateAmountOfCure(salesModel , listOfData);
                setTableCells();
                salesDataToShow.clear();
                getSalesDataFromDataBase(getLastSaleCode());
            }
            else {
                showAlertError();
            }
        }
    }

    public boolean checkTheStore(SalesModel salesModel , ArrayList<Product> salesModelArrayList)
    {
        int totalTapsNumberNeeded = salesModel.getAmount() * salesModelArrayList.get(0).getTapsNumber() + salesModel.getTapsNumber();
        if ((salesModel.getTapsNumber() < salesModelArrayList.get(0).getTapsNumber()) && (totalTapsNumberNeeded <= salesModelArrayList.get(0).getTotalTapsNumber()) && (salesModel.getTapsNumber() != 0 || salesModel.getAmount() != 0))
        {
            return true;
        }
        else {
            return false;
        }
    }

    public  void updateAmountOfCure(SalesModel salesModel,ArrayList<Product> productArrayList)
    {
        int newTotalTapsNumber = productArrayList.get(0).getTotalTapsNumber() - (salesModel.getAmount() * productArrayList.get(0).getTapsNumber() + salesModel.getTapsNumber());
        int newAmount = productArrayList.get(0).getAmount() - (((productArrayList.get(0).getTotalTapsNumber() - newTotalTapsNumber) / productArrayList.get(0).getTapsNumber()) + 1);
        if (newTotalTapsNumber % productArrayList.get(0).getTapsNumber() == 0)
            newAmount = newTotalTapsNumber / productArrayList.get(0).getTapsNumber();
        if(newAmount < 0)
        {
            newAmount = 0;
        }
        String query = "update product set amount = '"+newAmount+"' , total_taps_number = '"+newTotalTapsNumber+"' where cure_code = '"+productArrayList.get(0).getCureCode()+"'";
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
        String query = "select sale_codes from codes";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        return  dataBaseManipulation.getLastSaleCodeFromDataBase();
    }


    public  void showAlertError()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.show();
    }

    private  void insertSales(Product saleInformation, int saleCode, SalesModel salesModel)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println(salesModel.getTapsNumber());
        String query = "insert into sales values('"+saleInformation.getCureCode()+"' , '"+saleInformation.getCureName()+"' , '"+salesModel.getAmount()+"' , '"+salesModel.getTapsNumber()+"' ,'"+saleInformation.getRetailPrice()+"' , '"+saleInformation.getTotalPrice()+"' , '"+ LocalDate.now().toString() +
                "' , 1 , '"+saleCode+"')";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        dataBaseManipulation.manipulateDataBase();
    }

    static public  ArrayList<Product> getCureDataFromDataBase(int condition)
    {
        String query = "select * from product where cure_code = '"+condition+"'";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        ArrayList<Product> listOfData = dataBaseManipulation.showData();
        if (listOfData != null)
        {
            return listOfData;
        }else {
            //showAlertError();
            return null;
        }
    }

    public  void getSalesDataFromDataBase(int condition)
    {
        String query = "select cure_code , cure_name , amount , taps_number , retail_price , total_price from sales where sale_code = '"+condition+"'";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        ResultSet resultSet = dataBaseManipulation.executeStatementSelect();
        salesDataToShow.clear();
        try {
            while (resultSet.next())
            {
                salesDataToShow.add(SalesModel.makeObject(resultSet.getInt("cure_code") ,
                        resultSet.getString("cure_name") , resultSet.getInt("amount") , resultSet.getInt("taps_number") ,
                        resultSet.getInt("retail_price"),resultSet.getInt("total_price")));
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
        String query = "select total_price , taps_number , cure_code ,amount from sales where sale_code = '"+condition+"'";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        ResultSet resultSet = dataBaseManipulation.executeStatementSelect();
        try {
            resultSet.next();
            ArrayList<Product> productArrayList = SalesDataBaseController.getCureDataFromDataBase(resultSet.getInt("cure_code"));
            int tapsNumberPerCure = productArrayList.get(0).getTapsNumber();
            resultSet = dataBaseManipulation.executeStatementSelect();
            showBillAlertAndPrice(CalculateBill.calculateBill(resultSet  , tapsNumberPerCure));
        }catch (SQLException sqlException)
        {
            Exception.printingSqlErrors(sqlException);
        }
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
            ArrayList<Product> listOfCureData = new ArrayList<>();
            salesModel.setCureCode(Integer.parseInt(cureCode.getText()));
            listOfCureData = getCureDataFromDataBase(salesModel.getCureCode());
            if(listOfCureData.get(0).getAmount() >= Integer.parseInt(amount.getText()))
                updateDataOfBill();
        }else {
            showAlertError();
        }
    }

    public  void updateDataOfBill()
    {
        String query = "update sales set amount = '"+Integer.parseInt(amount.getText())+"' where sale_code = '"+Integer.parseInt(saleCode.getText())+"' and cure_code = '"+Integer.parseInt(cureCode.getText())+"'";
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
        String query = "select sale_code from sales where sale_code = '"+Integer.parseInt(saleCode.getText())+"'";
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
        String query = "delete from codes where sale_codes = '"+Integer.parseInt(saleCode.getText())+"'";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        dataBaseManipulation.manipulateDataBase();
    }

    public  void DeleteDataOfBill()
    {
        String query = "delete from sales where sale_code = '"+Integer.parseInt(saleCode.getText())+"' and cure_code = '"+Integer.parseInt(cureCode.getText())+"'";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        dataBaseManipulation.manipulateDataBase();
        getSalesDataFromDataBase(getLastSaleCode());
    }

}