package view;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 * Created by User on 13.09.2015.
 */
public class FieldView extends GridPane{
    private int numberGUIX;//GUI size
    private int numberGUIY;
    private int numberY;
    private int numberX;

    private Rectangle[][] matrixShape;
    private int matrixNumber[][];
    private Controller controller;
    public FieldView() {
    }
    public void intialializeField(Controller controll){
        controller=controll;
        numberGUIX=controller.getX();
        numberGUIY=controller.getY();
        numberY=numberGUIY+2;
        numberX=numberGUIX+2;
        this.setPadding(new Insets(20, 20, 20, 20));
        matrixShape=new Rectangle[numberGUIX][numberGUIY];
        matrixNumber=new int[numberX][numberY];

        for(int i=0;i<numberX;i++)
            for (int j = 0; j < numberY; j++) {
                matrixNumber[i][j]=0;
            }
        for(int i=0;i<numberGUIX;i++)
            for(int j=0;j<numberGUIY;j++){
                Rectangle rect=new Rectangle(30,30);
                rect.setFill(Color.RED);
                matrixShape[i][j]=rect;
                this.add(rect, i, j);
            }
    }

    public int[][] updateColor(int fieldMatrix[][]) {
        for(int i=1;i<numberX-1;i++)
            for(int j=1;j<numberY-1;j++){   //matrixNumber is old matrix,fieldMatrix's new matrix
                if(matrixNumber[i][j]!=fieldMatrix[i][j]){
                    matrixNumber[i][j]=fieldMatrix[i][j];
                    Color color=getColor(matrixNumber[i][j]);
                    matrixShape[i-1][j-1].setFill(color);
                }
            }
    return matrixNumber;
    }

    private Color getColor(int number) {
        switch(number){
            case 0: return Color.RED;
            case 1: return Color.BLACK;
            case 2: return Color.AQUA;
        }
        return Color.WHITE;
    }
}
