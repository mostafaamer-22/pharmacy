package com.example.pharmacy.ControllerUi;

import com.example.pharmacy.Models.UserReviewModel;
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

public class UserReviewUi implements Initializable {


    @FXML
    public TableView<UserReviewModel> tableUsers;

    @FXML
    public TableColumn<? , ?> ssnColumn;

    @FXML
    public TableColumn<? , ?> nameColumn;

    @FXML
    public TableColumn<? , ?> statusColumn;

    @FXML
    public TableColumn<? , ?> dateColumn;

    @FXML
    public TableColumn<? , ?> timeColumn;

    @FXML
    public TextField date;

    public ObservableList<UserReviewModel> userDataToShow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userDataToShow = FXCollections.observableArrayList();
        setTableCells();
    }


    public  void setTableCells()
    {
        ssnColumn.setCellValueFactory(new PropertyValueFactory<>("ssn"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("f_name"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
    }
}
