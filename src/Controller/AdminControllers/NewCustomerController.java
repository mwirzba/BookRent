package Controller.AdminControllers;


import Controller.MainController;
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

    private AdminController adminController;

    @FXML
    Button addButton;

    @FXML
    TextField passwordTextField;

    @FXML
    TextField loginTextField;

    @FXML
    Label customerExistsMessage;


    public void setAdminController(AdminController adminController) {
        this.adminController = adminController;
    }


    @FXML
    void addNewCustomer()
    {
        Connection conn = DbManager.getInstance().getConn();
        customerExistsMessage.setText("");

            if(loginTextField.getText().isEmpty() ||  passwordTextField.getText().isEmpty()){customerExistsMessage.setText("PODAJ DANE");}
            else {
                try {
                    PreparedStatement statement = null;
                    statement = conn.prepareStatement("select login from Customer where login = ?");
                    statement.setString(1, loginTextField.getText());
                    ResultSet rs = statement.executeQuery();
                    if (rs.next()) {
                        customerExistsMessage.setText("Uzytkownik istnieje");
                    } else {


                        String loginSelect = "insert  into Customer(login,password) values(?,?)";
                        statement = conn.prepareStatement(loginSelect);
                        statement.setString(1, loginTextField.getText());
                        statement.setString(2, passwordTextField.getText());
                        statement.execute();
                        customerExistsMessage.setText("Pomyślnie dodano użytkownika");
                        adminController.lastUser.setText(loginTextField.getText());
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
    }

}
