package Controller.UserControllers;

import Model.CurrentUser;
import Model.DbManager;
import Model.Rental;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerInfoController {


    @FXML
    Button changeinfoButton;

    @FXML
    Label loginText;

    @FXML
    TextField passwordtextfiled;

    @FXML
    TextField streetTextField;

    @FXML
    TextField numberTextField;

    @FXML
    TextField nameTextField;

    @FXML
    TextField surnameTextField;

    @FXML
    TextField cityTextField;

    @FXML
    Label userInfoMessage;
    private boolean firstLogin;

    @FXML
    void initialize() {
        Connection conn = DbManager.getInstance().getConn();
        userInfoMessage.setText("");
        try {
            PreparedStatement statement = null;
            String loginSelect = "select login,City,Street,Number,Name,Surname from Customer join CustomerInfo CI on Customer.CustomerInfoId = CI.Id join CustomerAdres CA on Customer.CustomerAdresId = CA.Id where Customer.Id =?";
            statement = conn.prepareStatement(loginSelect);
            statement.setInt(1,CurrentUser.Id);
            ResultSet rs = statement.executeQuery();
            if(rs.next())
            {
                loginText.setText(rs.getString(1));
                cityTextField.setText(rs.getString(2));
                streetTextField.setText(rs.getString(3));
                numberTextField.setText(rs.getString(4));
                nameTextField.setText(rs.getString(5));
                surnameTextField.setText(rs.getString(6));

            }
            else
            {
                userInfoMessage.setText("Dadaj dane!");
                firstLogin=true;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (firstLogin) {
            try {
                PreparedStatement statement = null;
                String loginSelect = "select login from Customer where Id = ?";
                statement = conn.prepareStatement(loginSelect);
                statement.setInt(1, CurrentUser.Id);
                ResultSet rs = statement.executeQuery();

                if (rs.next()) {
                    loginText.setText(rs.getString(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    public void changeinfo() {
        Connection conn = DbManager.getInstance().getConn();
        userInfoMessage.setText("");
        if ( passwordtextfiled.getText().isEmpty() || cityTextField.getText().isEmpty() || streetTextField.getText().isEmpty()
                || numberTextField.getText().isEmpty() || nameTextField.getText().isEmpty() || surnameTextField.getText().isEmpty())
            userInfoMessage.setText("Uzupełnij wszystkie dane");
        else {
            try {
                PreparedStatement statement = null;
                String update = "exec EditCustomerInfo ?,?,?,?,?,?,?";

                statement = conn.prepareStatement(update);
                statement.setString(1, passwordtextfiled.getText());
                statement.setString(2, cityTextField.getText());
                statement.setString(3, streetTextField.getText());
                statement.setString(4, numberTextField.getText());
                statement.setString(5, nameTextField.getText());
                statement.setString(6, surnameTextField.getText());
                statement.setInt(7, CurrentUser.Id);
                statement.execute();

                initialize();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
