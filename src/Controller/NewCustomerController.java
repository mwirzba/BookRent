package Controller;


import Model.DbManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NewCustomerController {

    @FXML
    Button addButton;

    @FXML
    TextField passwordTextField;

    @FXML
    TextField loginTextField;

    @FXML
    Label customerExistsMessage;

    @FXML
    void initialize()
    {

    }

    @FXML
    void addNewCustomer()
    {
        Connection conn = DbManager.getInstance().getConn();
        customerExistsMessage.setText("");
        try {

            PreparedStatement statement = null;
            statement = conn.prepareStatement( "select login from Customer where login = ?");
            statement.setString(1,loginTextField.getText());
            ResultSet rs = statement.executeQuery();
            if (rs.next())
            {
                customerExistsMessage.setText("Uzytkownik istnieje");
            }
            else {


                String loginSelect = "insert  into Customer values(?,?)";
                statement = conn.prepareStatement(loginSelect);
                statement.setString(1, loginTextField.getText());
                statement.setString(2, passwordTextField.getText());
                statement.execute();
                customerExistsMessage.setText("Pomyślnie dodano użytkownika");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
