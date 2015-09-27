package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;

import model.ModelField;
import model.Point;
import model.Shapes.Line;
import model.Shapes.Shape;
import view.FieldView;


import java.util.*;

/**
 * Created by User on 13.09.2015.
 */
public class Controller{
    private static final int NUMBER_X=8;   //HEAD GRaphics FIELD SIZE
    private static final int NUMBER_Y=15;   //ModelField =graphicsField+2(X)+2(Y)
    private  ModelField modelField;
    private Timer timer;
    @FXML private Button btn;
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





    private class NewGameItemHandler implements EventHandler<ActionEvent> {  //start move shape
        @Override
        public void handle(ActionEvent event) {
            timer=new Timer();
            TimerTask timerTaskMultiply=new TimerTask() {
                @Override
                public void run() {
                    moveShape("Down");
                   // System.out.println(new Date());
                }


            };
            TimerTask timerTaskSingle=new TimerTask() {
                @Override
                public void run() {
                   createNewShape();
                }
            };
            timer.schedule(timerTaskSingle,1000);
            timer.schedule(timerTaskMultiply,2000,400);
        }
    }

    private void createNewShape() {
        Line line=new Line(NUMBER_X,NUMBER_Y);
        shapeActiv=line;
        activState=states.get(0);
        updateField();
    }

    public void moveShape(String direction){
        switch(direction){
            case "Down":  if(isShapeMoveDown()){
                shapeActiv.moveDown();}
                else{
                colectionActionAfterStopShape();
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
        case UP: System.out.println("around");
           moveShape("Around");
            break;

    }
    }
    public void colectionActionAfterStopShape(){
       // System.out.println("colectionActionAfterStopShape");
       modelField.convertationDynamicToStatic();

        modelField.checkCurrentRowAndDeleteIt();  //uncomment it,when this function will be ready
        fieldView.updateColor(modelField.getFieldMatrix());
        createNewShape();

    }
    public void aroundShape(){
        int oldState=activState;
        if(activState!=states.size()-1) {
            activState = states.get(activState + 1);
        }else{
            activState=0;
        }
        if(isShapeMoveAround()){
            System.out.println("all zbc");
        }else{
            System.out.println("GOVNO");
        activState=oldState;
        }

    }

    private boolean isShapeMoveAround() {

      Line line=(Line) shapeActiv;
        ArrayList<Point> tempPoints= (ArrayList<Point>) line.getPointForAround(activState);
        int matrix[][]=modelField.getFieldMatrix();
        for(int i=0;i<tempPoints.size();i++){
            Point p=tempPoints.get(i);
            int posX=p.getPosX();
            int posY=p.getPosY();
            if(matrix[posX-1][posY]==1) return false;
        }
        shapeActiv.aroundShape(new ArrayList<>(tempPoints));
        return true;
    }
}
