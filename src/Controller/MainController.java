package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;


public class MainController {

    @FXML
    private StackPane mainView;


    @FXML
    void initialize(){
        loadMenuScreen();
    }

    public void loadMenuScreen() {
        FXMLLoader loader =  new FXMLLoader();
        String path =  "/View/login.fxml";
        loader.setLocation(this.getClass().getResource(path));
        Pane pane =  null;
        try {
            pane = loader.load();
        }
        catch (IOException e) {

            e.printStackTrace();
        }
        LoginController loginController = loader.getController();
        loginController.setMainController(this);
        setScreen(pane);
    }

    public void setScreen(Pane pane) {
        mainView.getChildren().clear();
        mainView.getChildren().add(pane);
    }

}
