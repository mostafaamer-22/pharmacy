package com.example.pharmacy.ControllerUi;
import com.example.pharmacy.Controllers.MainController;
import com.example.pharmacy.Interfaces.SetData;
import com.example.pharmacy.Models.SalesReviewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;

public class SalesReviewUi extends MainController implements Initializable , SetData {

    @FXML
    public TextField date;

    @FXML
    public TableView<SalesReviewModel> tableSales;

    @FXML
    public TableColumn<? , ?> cureCodeColumn;

    @FXML
    public TableColumn<? , ?> cureNameColumn;

    @FXML
    public TableColumn<? , ?> amountColumn;

    @FXML
    public TableColumn<? , ?> tapsNumberColumn;

    @FXML
    public TableColumn<? , ?> retailPriceColumn;

    @FXML
    public TableColumn<? , ?>  totalPriceColumn;

    @FXML
    public TableColumn<? , ?>  employeeSSNColumn;

    @FXML
    public TableColumn<? , ?>  dateColumn;

    public ObservableList<SalesReviewModel> salesDataToShow;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        salesDataToShow = FXCollections.observableArrayList();
        SetDataInTable();
    }



    @Override
    public void SetDataInTable() {
        cureCodeColumn.setCellValueFactory(new PropertyValueFactory<>("cureCode"));
        cureNameColumn.setCellValueFactory(new PropertyValueFactory<>("cureName"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        tapsNumberColumn.setCellValueFactory(new PropertyValueFactory<>("tapsNumber"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        retailPriceColumn.setCellValueFactory(new PropertyValueFactory<>("retailPrice"));
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        employeeSSNColumn.setCellValueFactory(new PropertyValueFactory<>("employeeSSN"));
    }
}
