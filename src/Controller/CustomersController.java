package Controller;

import Model.Customer;
import Model.DbManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomersController implements Initializable {

    @FXML
    private TableView<Customer> customersTable;

    @FXML
    private TableColumn<Customer,String> Id;

    @FXML
    private TableColumn<Customer,String> login;

    private ObservableList<Customer> customers = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connection conn = DbManager.getInstance().getConn();

        try {
            ResultSet rs = conn.createStatement().executeQuery("select * from Customer");
            while (rs.next()){
                customers.add(new Customer(rs.getString("Id"),rs.getString("login")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        Id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        login.setCellValueFactory(new PropertyValueFactory<>("login"));

        customersTable.setItems(customers);
    }
}