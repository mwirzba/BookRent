package Controller;

import Controller.AdminControllers.AdminController;
import Controller.UserControllers.UserController;
import Model.CurrentUser;
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
import java.text.DateFormat;
import java.time.LocalDate;

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
                String selectID = "select Id from Customer where login = ?";

                statement = conn.prepareStatement(selectID);
                statement.setString(1,login);

                int Idlogin;

                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next())
                {
                    Idlogin = resultSet.getInt(1);
                    String insertLogin = "insert  into LoginsHistory(LoggedDate,Login) values (CURRENT_TIMESTAMP ,?)";
                    CurrentUser.Id= Idlogin;
                    statement = conn.prepareStatement(insertLogin);
                    statement.setString(1,login);
                    statement.execute();

                }

                if(login.equals("admin")) {
                    loadPane("/View/adminPanel.fxml",login);
                }
                else
                {
                    loadPane("/View/userPanel.fxml",login);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadPane(String s,String login) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(s));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(s.equals("/View/userPanel.fxml"))
        {
            UserController userController = loader.getController();
            userController.setCurrentUserLogin(login);
            userController.setMainController(mainController);
        }
        else {
            AdminController adminController = loader.getController();
            adminController.setMainController(mainController);
        }
        mainController.setScreen(pane);
    }


}
