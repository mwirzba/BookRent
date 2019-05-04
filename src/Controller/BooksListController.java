package Controller;

import Model.Book;
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

public class BooksListController implements Initializable {

    @FXML
    private TableView<Book> bookTable;

    @FXML
    private TableColumn<Book,String> Id;

    @FXML
    private TableColumn<Book,String> Author;

    @FXML
    private TableColumn<Book,String> Title;

    private ObservableList<Book> books = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connection conn = DbManager.getInstance().getConn();

        try {
            ResultSet rs = conn.createStatement().executeQuery("select * from Book");
            while (rs.next()){
                books.add(new Book(rs.getString("Id"),rs.getString("Title"),rs.getString("Author")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        Id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        Author.setCellValueFactory(new PropertyValueFactory<>("Author"));
        Title.setCellValueFactory(new PropertyValueFactory<>("Title"));

        bookTable.setItems(books);
    }
}