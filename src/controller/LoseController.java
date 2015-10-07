package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/**
 * Created by User on 07.10.2015.
 */
public class LoseController implements Initializable {
    private static Stage stage = null;

    @FXML
    private Button okButton;

    public synchronized void show() throws IOException {
        //source = message;

        Parent root = FXMLLoader.load(getClass().getResource("view/LoseMsgBox.fxml"));
        Scene scene = new Scene(root, 200, 150);
        stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Message");
        stage.initModality(Modality.NONE);
        stage.initOwner(Controller.getScene().getWindow());
        stage.showAndWait();
        stage.toFront();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
