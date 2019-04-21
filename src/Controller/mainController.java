package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;


public class mainController {

    @FXML
    private StackPane mainView;


    @FXML
    void initialize(){
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

        mainView.getChildren().add(pane);
    }

}
