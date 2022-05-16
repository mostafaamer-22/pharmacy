package com.example.pharmacy.Controllers;
import com.example.pharmacy.Database.DataBaseManipulation;
import com.example.pharmacy.HandlerEvent;
import com.example.pharmacy.Interfaces.ResetDataTable;
import com.example.pharmacy.Models.Product;
import com.example.pharmacy.ControllerUi.ProductUi;
import com.example.pharmacy.Models.SalesModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManipulationProduct extends ProductUi  implements ResetDataTable{

    public static int newAmount;

    @FXML
    private TextField Search;

    static ResultSet resultSet;

    public static Product CreateObjectFromProduct(ResultSet resultSet)
    {
        try {
            product = new Product(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getInt(6),
                    resultSet.getInt(7),
                    resultSet.getString(8),
                    resultSet.getInt(9),
                    resultSet.getInt(10),
                    resultSet.getInt(11));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  product;
    }


    @FXML
    public void AddProduct() {
        Product product = new Product(Integer.parseInt(CureCode.getText()), CureName.getText(), CompanyName.getText(), DistributorName.getText(), DistributorNumber.getText(), Integer.parseInt(Amount.getText()), Integer.parseInt(TapsNumber.getText()), ExpireDate.getEditor().getText(), Integer.parseInt(RetailPrice.getText()), Integer.parseInt(TotalPrice.getText()) , 1001);
        String sql = "insert into product values('" + product.getCureCode() + "' , '" + product.getCureName() + "' , '" + product.getCompanyName() + "' , '" + product.getDistributorName() + "' ,'" + product.getDistributorNumber() + "' , '" + product.getAmount() + "' , '" + product.getTapsNumber() + "', '" + product.getExpireDate() + "' , '" + product.getRetailPrice() + "', '" + product.getTotalPrice() + "' , '" + product.getAmount() * product.getTapsNumber() + "' )";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(sql);
        dataBaseManipulation.manipulateDataBase();
        SetDataInTable();
        loadProductsFromDatabase(data,table);
    }

    @FXML
    public void searchProductInDataBase() {
        data.clear();
        try {
            String sql = "select * from product where cure_code='" + Search.getText() + "'";
            DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(sql);
            resultSet = dataBaseManipulation.executeStatementSelect();
            if (!resultSet.next()) {
                HandlerEvent.showAlertNotFound();
            } else {
                data.add(CreateObjectFromProduct(resultSet));
                setManipulationProductInTextField();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        table.setItems(data);

    }



    @FXML
    public void updateProduct() {
        String sql = "update product set cure_name='" + CureName.getText() + "', company_name='" + CompanyName.getText() + "',distributor_name='"+DistributorName.getText()+"',distributor_number='"+DistributorNumber.getText()+"',amount='" + Amount.getText() + "',taps_number='" + TapsNumber.getText() + "',expire_date='" + ExpireDate.getEditor().getText() + "',retail_price='" + RetailPrice.getText() + "',total_price='" + TotalPrice.getText() + "' ,total_taps_number='" + Integer.parseInt(Amount.getText()) * Integer.parseInt(TapsNumber.getText())  + "' where cure_code = '"+Search.getText()+"'";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(sql);
        dataBaseManipulation.manipulateDataBase();
        searchProductInDataBase();
    }


    @FXML
    public void  deleteProduct()
    {
        String sql = "delete from product where cure_code = '"+Search.getText()+"'";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(sql);
        dataBaseManipulation.manipulateDataBase();
        SetDataInTable();
        loadProductsFromDatabase(data,table);
    }


    static public void loadProductsFromDatabase(ObservableList<Product> data, TableView<Product> table)
    {
        data.clear();
        try {
            String sql ="Select * from product";
            DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(sql);
            resultSet = dataBaseManipulation.executeStatementSelect();
            while (resultSet.next())
            {
                data.add(CreateObjectFromProduct(resultSet));
            }
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        table.setItems(data);

    }

    @FXML
    @Override
    public void ResetTable()
    {
        SetDataInTable();
        loadProductsFromDatabase(data,table);
    }

    static public void updateAmountOfCureAfterSale(SalesModel salesModel, ArrayList<Product> productArrayList)
    {
        int newTotalTapsNumber = productArrayList.get(0).getTotalTapsNumber() - (salesModel.getAmount() * productArrayList.get(0).getTapsNumber() + salesModel.getTapsNumber());
         newAmount = productArrayList.get(0).getAmount() - (((productArrayList.get(0).getTotalTapsNumber() - newTotalTapsNumber) / productArrayList.get(0).getTapsNumber()) + 1);
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

    static public  ArrayList<Product> getCureDataFromDataBase(int condition)
    {
        String query = "select * from product where cure_code = '"+condition+"'";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(query);
        ArrayList<Product> listOfData = dataBaseManipulation.showData();
        if (listOfData != null)
        {
            return listOfData;
        }else {
            HandlerEvent.showAlertError();
            return null;
        }
    }
}
