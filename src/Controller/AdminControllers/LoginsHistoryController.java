package Controller.AdminControllers;

import Model.Customer;
import Model.DbManager;
import Model.LoginsHistory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.text.TabableView;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginsHistoryController implements Initializable {

    @FXML
    private TableView<LoginsHistory> loginsHistoryTable;

    @FXML
    private TableColumn<LoginsHistory, String> LoggedDate;

    @FXML
    private TableColumn<LoginsHistory, String> login;


    private ObservableList<LoginsHistory> loginsHistories = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connection conn = DbManager.getInstance().getConn();

        try {
            ResultSet rs = conn.createStatement().executeQuery("select LoggedDate,Login from LoginsHistory");
            while (rs.next()) {
                loginsHistories.add(new LoginsHistory(rs.getString("login"), rs.getString("LoggedDate")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        LoggedDate.setCellValueFactory(new PropertyValueFactory<>("LoggedDate"));
        login.setCellValueFactory(new PropertyValueFactory<>("login"));
        loginsHistoryTable.setItems(loginsHistories);

    }
}