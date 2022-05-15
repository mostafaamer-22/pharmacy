package com.example.pharmacy.Controllers;
import com.example.pharmacy.Database.DataBaseManipulation;
import com.example.pharmacy.HandlerEvent;
import com.example.pharmacy.Models.Product;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.sql.ResultSet;

public class ManipulationProductController extends ProductController {


    @FXML
    private TextField Search;

    static ResultSet resultSet;


    @FXML
    public void AddProduct() {
        Product product = new Product(Integer.parseInt(CureCode.getText()), CureName.getText(), CompanyName.getText(), DistributorName.getText(), Integer.parseInt(DistributorNumber.getText()), Integer.parseInt(Amount.getText()), "2024", Integer.parseInt(TapsNumber.getText()), Integer.parseInt(RetailPrice.getText()), Integer.parseInt(TotalPrice.getText()));
        String sql = "insert into product values('" + product.getCureCode() + "' , '" + product.getCureName() + "' , '" + product.getCompanyName() + "' , '" + product.getDistributorName() + "' ,'" + product.getDistributorNumber() + "' , '" + product.getAmount() + "' , '" + product.getTapsNumber() + "', '" + product.getExpireDate() + "' , '" + product.getRetailPrice() + "', '" + product.getTotalPrice() + "')";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(sql);
        dataBaseManipulation.manipulateDataBase();
        setProductInCellTable();
        loadDataFromDatabase(data,table);
    }

    @FXML
    public void searchInDataBase() {
        data.clear();
        try {
            String sql = "select * from product where cure_code='" + Search.getText() + "'";
            DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(sql);
            resultSet = dataBaseManipulation.executeStatementSelect();
            if (!resultSet.next()) {
                HandlerEvent.showAlertNotFound();
            } else {
                product = new Product(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        resultSet.getInt(6),
                        resultSet.getString(8),
                        resultSet.getInt(7),
                        resultSet.getInt(9),
                        resultSet.getInt(10));
                data.add(product);
                setManipulationProductInTextField();

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        table.setItems(data);

    }



    @FXML
    public void updateProduct() {
        String sql = "update product set cure_name='" + CureName.getText() + "', company_name='" + CompanyName.getText() + "',distributor_name='"+DistributorName.getText()+"',distributor_number='"+DistributorNumber.getText()+"',amount='" + Amount.getText() + "',taps_number='" + TapsNumber.getText() + "',expire_date='" + ExpireDate.getEditor().getText() + "',retail_price='" + RetailPrice.getText() + "',total_price='" + TotalPrice.getText() + "'where cure_code = '"+Search.getText()+"'";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(sql);
         dataBaseManipulation.manipulateDataBase();
         searchInDataBase();
    }


    @FXML
    public void  deleteProduct()
    {
        String sql = "delete from product where cure_code = '"+Search.getText()+"'";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(sql);
        dataBaseManipulation.manipulateDataBase();
        setProductInCellTable();
        loadDataFromDatabase(data,table);
    }


    static public void  loadDataFromDatabase( ObservableList<Product> data,TableView<Product> table)
    {
        data.clear();
        try {
            String sql ="Select * from product";
            DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(sql);
            resultSet = dataBaseManipulation.executeStatementSelect();
            while (resultSet.next())
            {
                data.add(new Product(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        resultSet.getInt(6),
                        resultSet.getString(7),
                        resultSet.getInt(8),
                        resultSet.getInt(9),
                        resultSet.getInt(10)
                ));
            }
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        table.setItems(data);

    }

    @FXML
    public void Reset()
    {
        setProductInCellTable();
        loadDataFromDatabase(data,table);
    }
}
