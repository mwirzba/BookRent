package Controller.AdminControllers;

import Model.DbManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EditCustomerController {

    private String userLogin;

    @FXML
    TextField loginTextField;

    @FXML
    TextField passwordTextField;

    @FXML
    Button saveButton;

    @FXML
    Label userMessage;

    @FXML
    void initialize()
    {
        Connection conn = DbManager.getInstance().getConn();
        try {

            PreparedStatement statement = null;
            statement = conn.prepareStatement( "select login from Customer where login = ?");
            statement.setString(1, userLogin);
            ResultSet rs = statement.executeQuery();
            if (rs.next())
            {
                loginTextField.setText(userLogin);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void EditCustomer()
    {
       Connection conn = DbManager.getInstance().getConn();
        try {

            PreparedStatement statement = null;
            if(passwordTextField.getText().isEmpty() && !loginTextField.getText().isEmpty())
            {
                statement = conn.prepareStatement( "UPDATE Customer SET login=? where login=?");
                statement.setString(1, loginTextField.getText());
                statement.setString(2, userLogin);
                statement.execute();
                userMessage.setText("Zmieniono dane użytkownika");

            }
            else if(!passwordTextField.getText().isEmpty() && !loginTextField.getText().isEmpty())
            {
                statement = conn.prepareStatement( "UPDATE Customer SET login=?,password = ? where login=?");
                statement.setString(1, loginTextField.getText());
                statement.setString(2, passwordTextField.getText());
                statement.setString(3, userLogin);
                statement.execute();
                userMessage.setText("Zmieniono dane użytkownika");
            }
            else
                userMessage.setText("Bład - podaj dane jeszcze raz.");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void DeleteCustomer()
    {

        Connection conn = DbManager.getInstance().getConn();
        try {
            PreparedStatement statement = null;
            statement = conn.prepareStatement( "select login from Customer where login = ?");
            statement.setString(1, loginTextField.getText());
            ResultSet rs = statement.executeQuery();
            if (!rs.next())
            {
                userMessage.setText("Nie znaleziono użytkownika");
            }
            else
            {
                statement = conn.prepareStatement( "delete from Customer where login=?");
                statement.setString(1, loginTextField.getText());
                statement.execute();
                userMessage.setText("Pomyślnie usunięto użytkownika");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void setUserLogin(String name)
    {
           this.userLogin= name;
    }

}
