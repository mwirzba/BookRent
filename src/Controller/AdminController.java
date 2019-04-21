package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;


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
        FXMLLoader loader = new  FXMLLoader(this.getClass().getResource("/View/Customers.fxml"));
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

}
