package view;

import controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


public class EntryPoint extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("View.fxml").openStream());
        primaryStage.setTitle("TetrisFX");
        Controller controller=loader.getController();
        controller.createField();
        Scene scene=new Scene(root, 600, 700);
        primaryStage.setScene(scene);
        controller.setScene(scene);
        scene.addEventHandler(KeyEvent.KEY_PRESSED,(key)->controller.processingKeyBoard(key.getCode()));
        primaryStage.show();
    }


    public static void main(String[] args) {


        launch(args);
    }
}
