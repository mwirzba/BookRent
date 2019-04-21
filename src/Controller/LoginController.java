package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.sql.*;

public class LoginController {

    @FXML
    private Pane loginView;
    @FXML
    private Button loginButton;

    @FXML
    private TextField loginBox;

    @FXML
    private TextField passwordBox;

    @FXML
    private  Label wrongLogininfo;

    @FXML
    public void login(){

        wrongLogininfo.setText("");
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection("jdbc:sqlserver://" +
                    "DESKTOP-QCG1H25\\SQLEXPRESS;database=BookRentDatabase","user","user");

            PreparedStatement statement = null;
            String loginSelect = "select login,password from Customer where login = ? and password = ?";
            statement = conn.prepareStatement(loginSelect);
            statement.setString(1,loginBox.getText());
            statement.setString(2,passwordBox.getText());
            ResultSet rs = statement.executeQuery();

            String login=null;
            String password = null;
            if(rs.next())
            {
                login = rs.getString(1);
                password =  rs.getString(2);
            }

            if(login==null || password == null)
            {
                wrongLogininfo.setText("Wrong login or password");
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
