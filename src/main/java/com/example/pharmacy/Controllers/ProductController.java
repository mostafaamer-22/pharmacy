package com.example.pharmacy.Controllers;
import com.example.pharmacy.DataBaseManipulation.DataBaseManipulation;
import com.example.pharmacy.HandlerEvent;
import com.example.pharmacy.Models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class ProductController implements Initializable {

    @FXML
    private TextField CureCode;

    @FXML
    private TextField CureName;

    @FXML
    private TextField CompanyName;

    @FXML
    private TextField DistributorNumber;

    @FXML
    private TextField DistributorName;

    @FXML
    private TextField Amount;

    @FXML
    private TextField TapsNumber;

    @FXML
    private DatePicker ExpireDate;

    @FXML
    private TextField RetailPrice;

    @FXML
    private TextField TotalPrice;

    @FXML
    private TableView<Product> table;

    @FXML
    private TableColumn<?,?> columnCureCode;

    @FXML
    private TableColumn<?,?> columnCureName;

    @FXML
    private TableColumn<?,?> columnCompanyName;

    @FXML
    private TableColumn<?,?> columnDistributorName;

    @FXML
    private TableColumn<?,?> columnDistributorNumber;

    @FXML
    private TableColumn<?,?> columnAmount;

    @FXML
    private TableColumn<?,?> columnTapsNumber;

    @FXML
    private TableColumn<?,?> columnExpireDate;

    @FXML
    private TableColumn<?,?> columnRetailPrice;

    @FXML
    private TableColumn<?,?> columnTotalPrice;

    ResultSet resultSet;

    private ObservableList<Product> data;

    Alert alert = new Alert(Alert.AlertType.NONE);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

            data = FXCollections.observableArrayList();
            setProductInCellTable();
            loadDataFromDatabase();
    }


    @FXML
    public void AddProduct() {
        Product product = new Product(Integer.parseInt(CureCode.getText()), CureName.getText(), CompanyName.getText(), DistributorName.getText(), Integer.parseInt(DistributorNumber.getText()), Integer.parseInt(Amount.getText()), "2026", Integer.parseInt(TapsNumber.getText()), Integer.parseInt(RetailPrice.getText()), Integer.parseInt(TotalPrice.getText()));
        try {
            String sql = "insert into product values('" + product.getCureCode() + "' , '" + product.getCureName() + "' , '" + product.getCompanyName() + "' , '" + product.getDistributorName() + "' ,'" + product.getDistributorNumber() + "' , '" + product.getAmount() + "' , '" + product.getTapsNumber() + "', '" + product.getExpireDate() + "' , '" + product.getRetailPrice() + "', '" + product.getTotalPrice() + "')";
            DataBaseManipulation dataBaseManipulation = new DataBaseManipulation(sql);
            dataBaseManipulation.manipulateDataBase();

        } catch (Exception e) {
            HandlerEvent.showAlertError();
            System.out.println(e.toString());
        }finally {
            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.showAndWait();
            setProductInCellTable();
            loadDataFromDatabase();

        }


    }


    @FXML
    public  void  clearTextFieldProduct()
    {
        CureCode.clear();
        CureName.clear();
        CompanyName.clear();
        DistributorName.clear();
        DistributorNumber.clear();
        TapsNumber.clear();
        TotalPrice.clear();
        RetailPrice.clear();
        Amount.clear();
        ExpireDate.getEditor().clear();
    }

    @FXML
    public void setProductInCellTable()
    {
        columnCureCode.setCellValueFactory(new PropertyValueFactory<>("CureCode"));
        columnCureName.setCellValueFactory(new  PropertyValueFactory<>("CureName"));
        columnCompanyName.setCellValueFactory(new  PropertyValueFactory<>("CompanyName"));
        columnDistributorName.setCellValueFactory(new  PropertyValueFactory<>("DistributorName"));
        columnDistributorNumber.setCellValueFactory(new  PropertyValueFactory<>("DistributorNumber"));
        columnAmount.setCellValueFactory(new  PropertyValueFactory<>("Amount"));
        columnExpireDate.setCellValueFactory(new  PropertyValueFactory<>("TapsNumber"));
        columnTapsNumber.setCellValueFactory(new  PropertyValueFactory<>("ExpireDate"));
        columnRetailPrice.setCellValueFactory(new  PropertyValueFactory<>("RetailPrice"));
        columnTotalPrice.setCellValueFactory(new  PropertyValueFactory<>("TotalPrice"));
    }

    private void  loadDataFromDatabase()
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
            System.out.println(e.toString());
        }
        table.setItems(data);

    }


}
