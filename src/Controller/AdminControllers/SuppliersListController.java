package Controller.AdminControllers;

import Model.Book;
import Model.DbManager;
import Model.Supplier;
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

public class SuppliersListController implements Initializable {
    @FXML
    private TableView<Supplier> suppliersTable;

    @FXML
    private TableColumn<Supplier,String> name;

    @FXML
    private TableColumn<Supplier,String> city;

    @FXML
    private TableColumn<Supplier,String> street;

    @FXML
    private TableColumn<Supplier,String> number;

    private ObservableList<Supplier> suppliers = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connection conn = DbManager.getInstance().getConn();

        try {
            ResultSet rs = conn.createStatement().executeQuery("select * from Supplier join SupplierAdres SA on Supplier.IdAdres = SA.Id");
            while (rs.next()){
                suppliers.add(new Supplier(rs.getString("Name"),rs.getString("City"),rs.getString("Street"),rs.getString("Number")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        city.setCellValueFactory(new PropertyValueFactory<>("City"));
        street.setCellValueFactory(new PropertyValueFactory<>("Street"));
        number.setCellValueFactory(new PropertyValueFactory<>("Number"));
        suppliersTable.setItems(suppliers);
    }
}
