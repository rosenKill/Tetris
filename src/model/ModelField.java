package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 23.09.2015.
 */
public class ModelField {
    private int fieldMatrix[][];
    private int numberX;
    private int numberY;

    public ModelField(int numberX,int numberY){
        this.numberX=numberX;
        this.numberY=numberY;
        int fMatrix[][]=new int[numberX][numberY];
        for(int i=1;i<numberX-1;i++)
            for(int j=0;j<numberY-1;j++){
                fMatrix[i][j]=0;
            }
        for(int i=0;i<numberY;i++){
            fMatrix[0][i]=1;
        }
        for(int i=0;i<numberY;i++){
            fMatrix[numberX-1][i]=1;
        }
        for(int i=1;i<numberX-1;i++){
            fMatrix[i][numberY-1]=1;
        }
        fieldMatrix=fMatrix;
    }

    public int[][] getFieldMatrix(){
        return fieldMatrix;
    }
    public void setNewValue(int x,int y,int val ){
        fieldMatrix[x][y]=val;
    }
    public void setNewMatrix(int temp[][]){
        fieldMatrix=temp;
    }

    public void deleteNumber2fromMatrix() {
        for(int i=1;i<numberX-1;i++)
            for(int j=1;j<numberY-1;j++){
                if(fieldMatrix[i][j]==2) {
                    fieldMatrix[i][j] = 0;
                }
            }
    }

    public void convertationDynamicToStatic() {
        for(int i=1;i<numberX-1;i++)
            for(int j=1;j<numberY-1;j++){
                if(fieldMatrix[i][j]==2) {
                    fieldMatrix[i][j] = 1;
                }
            }
    }

    public int checkCurrentRowAndDeleteIt() {
        int numberRow=0;
        for(int i=1;i<numberY-1;i++) {
            boolean bl=true;
            for (int j = 1; j < numberX - 1; j++) {
                if(fieldMatrix[j][i]==0){bl=false;break;}
            }
            if(bl==true){
                numberRow++;
               for(int pointY=i;pointY>0;pointY--)
                   for(int pointX=1;pointX<numberX;pointX++){
                       fieldMatrix[pointX][pointY]=fieldMatrix[pointX][pointY-1];
                   }


            }
        }
        return numberRow;
    }
    public boolean checkLoseGame(){
        for(int i=1;i<numberX-1;i++){
          if(fieldMatrix[i][1]==1){
              return true;
          }
        }
        return false;
    }
}
