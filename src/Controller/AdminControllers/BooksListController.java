package Controller.AdminControllers;

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

public class BooksListController  implements Initializable {

    @FXML
    private TableView<Book> booksTable;

    @FXML
    private TableColumn<Book,String> Title;

    @FXML
    private TableColumn<Book,String> Author;

    private ObservableList<Book> books = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connection conn = DbManager.getInstance().getConn();

        try {
            ResultSet rs = conn.createStatement().executeQuery("select * from BookWIthAuthor");
            while (rs.next()){
                books.add(new Book(rs.getString("Title"),rs.getString("FullName")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Author.setCellValueFactory(new PropertyValueFactory<>("Author"));
        Title.setCellValueFactory(new PropertyValueFactory<>("Title"));

        booksTable.setItems(books);
    }
}


