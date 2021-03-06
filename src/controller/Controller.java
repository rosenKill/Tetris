package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import model.ModelField;
import model.Point;
import model.Shapes.Line;
import model.Shapes.Shape;
import model.Shapes.Square;
import model.Shapes.ZRight;
import view.FieldView;
import view.LoseMsgBox;
import view.ScorePanel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by User on 13.09.2015.
 */
public class Controller{
    private static final int NUMBER_X=8;   //HEAD GRaphics FIELD SIZE
    private static final int NUMBER_Y=15;   //ModelField =graphicsField+2(X)+2(Y)
    private static Scene scene;
    private  ModelField modelField;
    private Timer timer;
    private int score=0;
    private int time=200;
    @FXML private ScorePanel scorePanel;
    @FXML private FieldView fieldView;
    @FXML private MenuItem newGameItem;
    @FXML private MenuItem exitGameItem;
    private Shape shapeActiv;
    List<Integer> states=new ArrayList<Integer>();
    private int activState;
    public Controller(){
        states.add(0);
        states.add(1);
        states.add(2);
        states.add(3);

    }
    public void createField(){ //initialize
        newGameItem.setOnAction(new NewGameItemHandler());

        modelField=new ModelField(NUMBER_X+2,NUMBER_Y+2);
       fieldView.intialializeField(this);
    }
    public void updateField(){
        modelField.deleteNumber2fromMatrix();
        this.updateNumberField();  //add in matrix value from shape
        fieldView.updateColor(modelField.getFieldMatrix());

        //modelField.setNewMatrix(tempMatrix);
    }

    private void updateNumberField() {
        ArrayList<Point> list= (ArrayList<Point>) shapeActiv.getListPoints();
        for(int i=0;i<list.size();i++){
            Point point=list.get(i);
            int posX=point.getPosX();
            int posY=point.getPosY();
            modelField.setNewValue(posX,posY,2);
        }
    }

    public int getX(){return NUMBER_X;};
    public int getY(){return NUMBER_Y;};

    public void setScene(Scene scene) {
        this.scene = scene;
    }
    public static Scene getScene(){
        return scene;
    }

    private class NewGameItemHandler implements EventHandler<ActionEvent> {  //start move shape
        @Override
        public void handle(ActionEvent event) {
            timer=new Timer();
            TimerTask timerTaskMultiply=new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(
                            new Runnable() {
                                @Override
                                public void run() {
                                    moveShape("Down");
                                }
                            }
                    );

                }
            };
            createNewShape();
            timer.schedule(timerTaskMultiply,1000,time);
        }
    }


    public void createNewShape(){
        ListFigures listFigures=new ListFigures(NUMBER_X,NUMBER_Y);
        shapeActiv=listFigures.getRandomShape();
//        ZRight zRight=new ZRight(NUMBER_X,NUMBER_Y);
//        shapeActiv=zRight;
        activState=states.get(0);
        updateField();
    }

    public void moveShape(String direction){
        switch(direction){
            case "Down":  if(isShapeMoveDown()){
                shapeActiv.moveDown();}
                else{
                colectionActionAfterStopShape();
               // scorePanel.setScoreCurrent(score);

            }break;
            case "Left":
                if(isShapeMoveLeft())
                    shapeActiv.moveLeft();
                break;
            case "Right":
                if(isShapeMoveRight())
                    shapeActiv.moveRight();
                break;
            case "Around":
                aroundShape();
                break;

        }
    updateField();
    }

    private boolean isShapeMoveLeft() {
        ArrayList<Point> list= (ArrayList<Point>) shapeActiv.getListPoints();
        int matrix[][]=modelField.getFieldMatrix();
        for(int i=0;i<list.size();i++){
            Point p=list.get(i);
            int posX=p.getPosX();
            int posY=p.getPosY();
            if(matrix[posX-1][posY]==1) return false;

        }
        return true;
    }

    private boolean isShapeMoveRight() {
        ArrayList<Point> list= (ArrayList<Point>) shapeActiv.getListPoints();
        int matrix[][]=modelField.getFieldMatrix();
        for(int i=0;i<list.size();i++){
            Point p=list.get(i);
            int posX=p.getPosX();
            int posY=p.getPosY();
            if(matrix[posX+1][posY]==1) return false;

        }
        return true;
    }

    public boolean isShapeMoveDown(){
        ArrayList<Point> list= (ArrayList<Point>) shapeActiv.getListPoints();
        int matrix[][]=modelField.getFieldMatrix();
        for(int i=0;i<list.size();i++){
            Point p=list.get(i);
            int posX=p.getPosX();
            int posY=p.getPosY();
            if(matrix[posX][posY+1]==1) return false;

        }
        return true;
    }
    public void processingKeyBoard(KeyCode code) {

    switch(code){
        case LEFT: moveShape("Left");
            break;
        case RIGHT:moveShape("Right");
            break;
        case UP:
            moveShape("Around");
            break;


    }
    }


    public void colectionActionAfterStopShape(){
       modelField.convertationDynamicToStatic();
        if( modelField.checkLoseGame() ){
            try {
                cancelGame();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        score+=modelField.checkCurrentRowAndDeleteIt();  //uncomment it,when this function will be ready
        fieldView.updateColor(modelField.getFieldMatrix());
        scorePanel.setScoreCurrent(score);
        createNewShape();

    }

    private void cancelGame() throws IOException {

        timer.cancel();
        LoseMsgBox loseMsgBox=new LoseMsgBox(Alert.AlertType.CONFIRMATION);
        loseMsgBox.show();
    }

    public void aroundShape(){

        int oldState=activState;
        if(activState!=states.size()-1) {
            activState = states.get(activState + 1);
        }else{
            activState=0;
        }
        if(!isShapeMoveAround()){
            activState=oldState;
        }

    }

    private boolean isShapeMoveAround() {
        ArrayList<Point> tempPoints=new ArrayList<>();
        if(shapeActiv instanceof Line){
            Line line=(Line) shapeActiv;
            tempPoints= (ArrayList<Point>) line.getPointForAround(activState);
        }else{
            if(shapeActiv instanceof Square){ return false; }
            else{
                if(shapeActiv instanceof ZRight){
                    ZRight line=(ZRight) shapeActiv;
                    tempPoints= (ArrayList<Point>) line.getPointForAround(activState);
                }

            }
        }
        int matrix[][]=modelField.getFieldMatrix();
        for(int i=0;i<tempPoints.size();i++){
            Point p=tempPoints.get(i);
            int posX=p.getPosX();
            int posY=p.getPosY();
            if(matrix[posX][posY]==1) return false;
        }
        shapeActiv.setNewPointsAfterAround(new ArrayList<>(tempPoints));
        return true;
    }

}
