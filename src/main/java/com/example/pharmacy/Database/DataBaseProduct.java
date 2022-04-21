package com.example.pharmacy.Database;

import com.example.pharmacy.Models.Product;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.sql.ResultSet;

public class DataBaseProduct {

   static ResultSet resultSet;

   static public void  loadDataFromDatabase(ObservableList<Product> data , TableView<Product> table)
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





}
