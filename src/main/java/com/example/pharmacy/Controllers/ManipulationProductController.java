package com.example.pharmacy.Controllers;

import com.example.pharmacy.Database.DataBaseManipulation;
import com.example.pharmacy.Database.DataBaseProduct;
import com.example.pharmacy.HandlerEvent;
import com.example.pharmacy.Models.Product;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.ResultSet;

public class ManipulationProductController extends ProductController {


    @FXML
    private TextField Search;

    ResultSet resultSet;

    Product product = null;

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
                setProductInTextField();

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        table.setItems(data);

    }

    public void setProductInTextField() {
        CureName.setText(product.getCureName());
        CompanyName.setText(product.getCompanyName());
        DistributorName.setText(product.getDistributorName());
        DistributorNumber.setText(String.valueOf(product.getDistributorNumber()));
        Amount.setText(String.valueOf(product.getAmount()));
        TapsNumber.setText(String.valueOf(product.getTapsNumber()));
        ExpireDate.getEditor().setText(product.getExpireDate());
        RetailPrice.setText(String.valueOf(product.getRetailPrice()));
        TotalPrice.setText(String.valueOf(product.getTotalPrice()));

    }

    @FXML
    public void updateProduct() {
        String sql = "update product set cure_name='" + CureName.getText() + "', company_name='" + CompanyName.getText() + "',distributor_name='"+DistributorName.getText()+"',distributor_number='"+DistributorNumber.getText()+"',amount='" + Amount.getText() + "',taps_number='" + TapsNumber.getText() + "',expire_date='" + ExpireDate.getEditor().getText() + "',retail_price='" + RetailPrice.getText() + "',total_price='" + TotalPrice.getText() + "'where cure_code = '"+Search.getText()+"'";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(sql);
         dataBaseManipulation.manipulateDataBase();
         searchInDataBase();
    }

    @Override
    public void clearTextFieldProduct() {
        CureName.clear();
        CompanyName.clear();
        DistributorName.clear();
        DistributorNumber.clear();
        TapsNumber.clear();
        TotalPrice.clear();
        RetailPrice.clear();
        Amount.clear();
        ExpireDate.getEditor().clear();
        HandlerEvent.showAlertSuccess();
    }

    @FXML
    public void  deleteProduct()
    {
        String sql = "delete from product where cure_code = '"+Search.getText()+"'";
        DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(sql);
        dataBaseManipulation.manipulateDataBase();
        setProductInCellTable();
        DataBaseProduct.loadDataFromDatabase(data,table);
    }
}
