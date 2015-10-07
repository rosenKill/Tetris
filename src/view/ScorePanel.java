package view;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Created by User on 05.10.2015.
 */
public class ScorePanel extends VBox {
    private Label scoreLabel;
    private Label scoreCurrent;
    public ScorePanel() {
        scoreLabel=new Label("SCORE");
        scoreCurrent=new Label("0");
        this.getChildren().add(scoreLabel);
        this.getChildren().add(scoreCurrent);

    }



    public void setScoreCurrent(int score) {
        String str=Integer.toString(score);
        scoreCurrent.setText("asd");
    }
}
