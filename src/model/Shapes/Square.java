package model.Shapes;

import model.Point;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by User on 28.09.2015.
 */
    public class Square extends Shape{
    int activState;
    public Square(){};
    public Square(int sizeX,int sizeY){
        points=new ArrayList<Point>(SIZE);
        for(int i=0,shift=0;i<SIZE;i++,shift++){
            Point p=new Point();
            points.add(p);
            if(i<2) {
                p.setPosX(sizeX/2);
                p.setPosY(shift);
            }
            else{
                if(i==2){
                p.setPosX(sizeX / 2 + 1);
                p.setPosY(0);
                }
                else{
                    p.setPosX(sizeX/2+1);
                    p.setPosY(1);
                }

            }

        }


    }
}
