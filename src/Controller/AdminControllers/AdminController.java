package Controller.AdminControllers;

import Model.DbManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AdminController {

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
   void openAddPanel()
   {
       FXMLLoader loader = new  FXMLLoader(this.getClass().getResource("/View/newCustomer.fxml"));
       Pane newCustomerPanel = null;
       try {
           newCustomerPanel = loader.load();
       } catch (IOException e) {
           e.printStackTrace();
       }
       adminViewPane.getChildren().clear();
       adminViewPane.getChildren().add(newCustomerPanel);
       adminPanel.getChildren().clear();
       adminPanel.getChildren().addAll(buttonPane,adminViewPane);

   }

   @FXML
    void showCustomers()
    {
        FXMLLoader loader = new  FXMLLoader(this.getClass().getResource("/View/CustomersList.fxml"));
        Pane customers = null;
        try {
            customers = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        adminViewPane.getChildren().clear();
        adminViewPane.getChildren().add(customers);
        adminPanel.getChildren().clear();
        adminPanel.getChildren().addAll(buttonPane,adminViewPane);

    }

    @FXML
    void findCustomer()
    {
        FXMLLoader loader = new  FXMLLoader(this.getClass().getResource("/View/EditCustomer.fxml"));
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
