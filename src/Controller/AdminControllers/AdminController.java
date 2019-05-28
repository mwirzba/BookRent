package Controller.AdminControllers;

import Controller.LoginController;
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
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.*;


public class AdminController {



    private MainController mainController;

    @FXML
    Button addChoice;

    @FXML
    HBox adminPanel;

    @FXML
    Pane adminViewPane;

    @FXML
    Pane buttonPane;

    @FXML
    Button showCustomersButton;

    @FXML
    Button findCustomerButton;

    @FXML
    TextField findCustomerTextField;

    @FXML
    Label userNotFoundMessege;

    @FXML
    Label userMessage;


    @FXML
    Button loginsHistoryButton;

    @FXML
    Button showBooksButton;

    @FXML
    Button showSuppliersButton;

    @FXML
    Label lastUser;

    @FXML
    Label hello;



    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void initialize()
    {
        Connection conn = DbManager.getInstance().getConn();
        try {
            PreparedStatement statement = null;
            statement = conn.prepareStatement("select login,DateAdded from LastAddedCustomer");
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                Date date =rs.getDate(2);
               lastUser.setText(rs.getString(1)+" "+date);
            }
            statement = conn.prepareStatement("select login from Customer where Id=?");
            statement.setInt(1, CurrentUser.Id);
            rs = statement.executeQuery();
            if (rs.next()) {
                hello.setText("Witaj "+rs.getString(1));
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void logout()
    {
        mainController.loadMenuScreen();
    }

    @FXML
    void showSuppliers()
    {
        loadPane("/View/suppliersList.fxml");
    }

    @FXML
   void openAddPanel()
   {
       loadPane("/View/newCustomer.fxml");

   }

   @FXML
    void showCustomers()
    {
        loadPane("/View/customersList.fxml");

    }

    @FXML
    void showLoginsHistory()
    {
        loadPane("/View/loginsHistory.fxml");
    }

    private void loadPane(String s) {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(s));
        Pane logins = null;
        try {
            logins = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        adminViewPane.getChildren().clear();
        adminViewPane.getChildren().add(logins);
        adminPanel.getChildren().clear();
        adminPanel.getChildren().addAll(buttonPane, adminViewPane);
        if(s.equals("/View/newCustomer.fxml")) {
            NewCustomerController newCustomerController = loader.getController();
            newCustomerController.setAdminController(this);
        }
    }



    @FXML
    void showBooks()
    {
        loadPane("/View/booksList.fxml");
    }

    @FXML
    void findCustomer()
    {
        FXMLLoader loader = new  FXMLLoader(this.getClass().getResource("/View/editCustomer.fxml"));
        Pane editcustomerpanel = null;
        try {
            editcustomerpanel = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Connection conn = DbManager.getInstance().getConn();
        try {

            PreparedStatement statement = null;
            statement = conn.prepareStatement( "select login from Customer where login = ?");
            statement.setString(1, findCustomerTextField.getText());
            ResultSet rs = statement.executeQuery();
            if (!rs.next())
            {
                userMessage.setText("Nie znaleziono uzytkownika");
            }
            else
            {
                userMessage.setText("");
                EditCustomerController editCustomerController = loader.getController();
                editCustomerController.setUserLogin(findCustomerTextField.getText());

                adminViewPane.getChildren().clear();
                adminViewPane.getChildren().add(editcustomerpanel);
                adminPanel.getChildren().clear();
                adminPanel.getChildren().addAll(buttonPane,adminViewPane);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }




}
