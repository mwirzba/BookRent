package Controller;

import Model.DbManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.sql.*;

public class LoginController {

    private MainController mainController;

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

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }


    @FXML
    public void login(){
        wrongLogininfo.setText("");
        try {
            DbManager dbManager = DbManager.getInstance();
            Connection conn = dbManager.getConn();

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
            else
            {
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/View/adminPanel.fxml"));
                Pane pane = null;
                try {
                    pane = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mainController.setScreen(pane);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
