package com.example.pharmacy.ControllerUi;
import com.example.pharmacy.Controllers.ManipulationProduct;
import com.example.pharmacy.HandlerEvent;
import com.example.pharmacy.Interfaces.ClearTextField;
import com.example.pharmacy.Interfaces.SetData;
import com.example.pharmacy.Models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;

public class ProductUi implements Initializable , SetData , ClearTextField {

    @FXML
    protected TextField CureCode;

    @FXML
    protected TextField CureName;

    @FXML
    protected TextField CompanyName;

    @FXML
    protected TextField DistributorNumber;

    @FXML
    protected TextField DistributorName;

    @FXML
    protected TextField Amount;

    @FXML
    protected TextField TapsNumber;

    @FXML
    protected DatePicker ExpireDate;

    @FXML
    protected TextField RetailPrice;

    @FXML
    protected TextField TotalPrice;

    @FXML
    protected TableView<Product> table;

    @FXML
    protected TableColumn<?,?> columnCureCode;

    @FXML
    protected TableColumn<?,?> columnCureName;

    @FXML
    protected TableColumn<?,?> columnCompanyName;

    @FXML
    protected TableColumn<?,?> columnDistributorName;

    @FXML
    protected TableColumn<?,?> columnDistributorNumber;

    @FXML
    protected TableColumn<?,?> columnAmount;

    @FXML
    protected TableColumn<?,?> columnTapsNumber;

    @FXML
    protected TableColumn<?,?> columnExpireDate;

    @FXML
    protected TableColumn<?,?> columnRetailPrice;

    @FXML
    protected TableColumn<?,?> columnTotalPrice;

    @FXML
    protected TableColumn<?,?> columnTotalTapsNumber;

    public  ObservableList<Product> data;

    public static Product product = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

            data = FXCollections.observableArrayList();
            SetDataInTable();
            ManipulationProduct.loadProductsFromDatabase(data,table);
    }


    public void setManipulationProductInTextField() {
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
    public void clearManipulationProductTextField() {
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


    @Override
    public void SetDataInTable() {
        columnCureCode.setCellValueFactory(new PropertyValueFactory<>("CureCode"));
        columnCureName.setCellValueFactory(new  PropertyValueFactory<>("CureName"));
        columnCompanyName.setCellValueFactory(new  PropertyValueFactory<>("CompanyName"));
        columnDistributorName.setCellValueFactory(new  PropertyValueFactory<>("DistributorName"));
        columnDistributorNumber.setCellValueFactory(new  PropertyValueFactory<>("DistributorNumber"));
        columnAmount.setCellValueFactory(new  PropertyValueFactory<>("Amount"));
        columnTapsNumber.setCellValueFactory(new  PropertyValueFactory<>("TapsNumber"));
        columnExpireDate.setCellValueFactory(new  PropertyValueFactory<>("ExpireDate"));
        columnRetailPrice.setCellValueFactory(new  PropertyValueFactory<>("RetailPrice"));
        columnTotalPrice.setCellValueFactory(new  PropertyValueFactory<>("TotalPrice"));
        columnTotalTapsNumber.setCellValueFactory(new  PropertyValueFactory<>("totalTapsNumber"));
    }

    @Override
    public void ClearInTextField() {
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
        HandlerEvent.showAlertSuccess();
    }
}
