package Controller.UserControllers;

import Model.Book;
import Model.CurrentUser;
import Model.DbManager;
import Model.Rental;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.naming.spi.InitialContextFactory;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RentalsListController implements Initializable {
    @FXML
    private TableView<Rental> rentalsListTable;

    @FXML
    private TableColumn<Rental,String> title;

    @FXML
    private TableColumn<Rental,String> dateRented;


    @FXML
    private TableColumn<Rental,String> dateReturned;

    private ObservableList<Rental> rentals = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connection conn = DbManager.getInstance().getConn();

        try {
            PreparedStatement statement = null;
            statement = conn.prepareStatement( "select Rental.DateRented,Rental.DateReturned,B.Title from Rental join Book B on Rental.BookId = B.Id join Customer C on Rental.CustomerId = C.Id where C.Id = ?");
            statement.setInt(1, CurrentUser.Id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                rentals.add(new Rental(rs.getString("DateRented"),rs.getString("DateReturned"),rs.getString("Title")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        dateRented.setCellValueFactory(new PropertyValueFactory<>("DateRented"));
        dateReturned.setCellValueFactory(new PropertyValueFactory<>("DateReturned"));
        title.setCellValueFactory(new PropertyValueFactory<>("Title"));

        rentalsListTable.setItems(rentals);
    }
}
