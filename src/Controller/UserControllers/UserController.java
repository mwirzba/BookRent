package Controller.UserControllers;


import Controller.MainController;
import Model.CurrentUser;
import Model.DbManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.*;

public class UserController {

    private String currentUserLogin;



    private MainController mainController;

    @FXML
    HBox userPanel;

    @FXML
    Pane userViewPane;

    @FXML
    Pane buttonPane;

    @FXML
    Button showBooksButton;

    @FXML
    Button showRentalsButton;
    @FXML
    Button rentBookButton;

    @FXML
    TextField rentBookTextField;

    @FXML
    Button accountInfoButton;

    @FXML
    Label userMessage;

    @FXML
    Label hello;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    void logOut()
    {
        mainController.loadMenuScreen();
    }

    @FXML
    public  void initialize()
    {
        Connection conn = DbManager.getInstance().getConn();
        try {
            PreparedStatement statement = null;
            statement = conn.prepareStatement("select login from Customer where Id=?");
            statement.setInt(1, CurrentUser.Id);
            ResultSet rs  = statement.executeQuery();
            if (rs.next()) {
                hello.setText("Witaj "+rs.getString(1));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showBooks()
    {
        FXMLLoader loader = new  FXMLLoader(this.getClass().getResource("/View/booksList.fxml"));
        Pane books = null;
        try {
            books = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        userViewPane.getChildren().clear();
        userViewPane.getChildren().add(books);
        userPanel.getChildren().clear();
        userPanel.getChildren().addAll(buttonPane,userViewPane);
    }

    @FXML
    void openAccountInfo()
    {
        FXMLLoader loader = new  FXMLLoader(this.getClass().getResource("/View/userInfoPanel.fxml"));
        Pane userInfoPanel = null;
        try {
            userInfoPanel = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        userViewPane.getChildren().clear();
        userViewPane.getChildren().add(userInfoPanel);
        userPanel.getChildren().clear();
        userPanel.getChildren().addAll(buttonPane,userViewPane);

    }

    @FXML
    void rentBook()
    {
        Connection conn = DbManager.getInstance().getConn();

            String title = rentBookTextField.getText();
            int idBook;
            if (title.isEmpty()) {
                userMessage.setText("Pole jest puste");
            }
            else {
                try {

                    userMessage.setText("");
                    PreparedStatement statement = null;
                    statement = conn.prepareStatement("select Id from Book where Book.Title = ?");
                    statement.setString(1, title);


                    ResultSet rs = statement.executeQuery();
                    if (rs.next()) {
                        idBook = rs.getInt("Id");

                        statement = conn.prepareStatement("insert into Rental(CustomerId, BookId, DateRented, DateReturned) values (?,?,CURRENT_TIMESTAMP,DATEADD(WEEK ,2,CURRENT_TIMESTAMP))");
                        statement.setInt(1, CurrentUser.Id);
                        statement.setInt(2, idBook);
                        statement.execute();
                    }
                    else {
                        userMessage.setText("Nie znaleziono takiej ksiązki!");
                    }

            }
                catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    void showRentals()
    {

        FXMLLoader loader = new  FXMLLoader(this.getClass().getResource("/View/rentalsList.fxml"));
        Pane userInfoPanel = null;
        try {
            userInfoPanel = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        userViewPane.getChildren().clear();
        userViewPane.getChildren().add(userInfoPanel);
        userPanel.getChildren().clear();
        userPanel.getChildren().addAll(buttonPane,userViewPane);
    }


    public void setCurrentUserLogin(String name)
    {
        this.currentUserLogin= name;
    }


}
