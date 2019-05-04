package Controller.UserControllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class UserController {

    private String currentUserLogin;

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
    void showBooks()
    {
        FXMLLoader loader = new  FXMLLoader(this.getClass().getResource("/View/BooksList.fxml"));
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


    }

    @FXML
    void rentBook()
    {

    }

    @FXML
    void showRentals()
    {

    }

    public void setCurrentUserLogin(String name)
    {
        this.currentUserLogin= name;
    }


}
