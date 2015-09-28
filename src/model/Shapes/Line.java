package model.Shapes;

import model.Point;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by User on 23.09.2015.
 */
public class Line extends Shape{
    int activState;
    public Line(int sizeX,int sizeY){
        states=new ArrayList<Integer>(4);
        states.add(0);
        states.add(1);
        states.add(2);
        states.add(3);
        points=new ArrayList<Point>(SIZE);
        activState=states.get(0);
            for(int i=0,shift=-1;i<SIZE;i++,shift++){
                Point p=new Point();
                points.add(p);
                p.setPosX(sizeX/2+shift);
                p.setPosY(1);
            }


    }

    public Line() {

    }

    //    public void changeStateToNext(){
//        int oldState=activState;
//        if(activState!=states.size()-1) {
//            activState = states.get(activState + 1);
//        }else{
//            activState=0;
//        }
//
//
//    }
    public List<Point> getPointForAround(int state){
        List<Point> newPoints=new ArrayList<Point>(); //create new temp list with coord for check on empty
        switch (state) {
            case 1: {
                int posX = points.get(1).getPosX();
                for (int i = 0, delty = -1; i < points.size(); i++, delty++) {
                    Point p = points.get(i);
                    int posY = p.getPosY() + delty;
                    Point newPoint = new Point(posX, posY);
                    newPoints.add(newPoint);
                }
            }break;
            case 2: {
                int posY = points.get(1).getPosY();
                for (int i = 0, deltx = 1; i < points.size(); i++, deltx--) {
                    Point p = points.get(i);
                    int posX = p.getPosX() + deltx;
                    Point newPoint = new Point(posX, posY);
                    newPoints.add(newPoint);
                }
            }break;
            case 3: {
                int posX = points.get(1).getPosX();
                for (int i = 0, delty = 1; i < points.size(); i++, delty--) {
                    Point p = points.get(i);
                    int posY = p.getPosY() + delty;
                    Point newPoint = new Point(posX, posY);
                    newPoints.add(newPoint);
                }
            }break;
            case 0: {
                int posY = points.get(1).getPosY();
                for (int i = 0, deltx = -1; i < points.size(); i++, deltx++) {
                    Point p = points.get(i);
                    int posX = p.getPosX() + deltx;
                    Point newPoint = new Point(posX, posY);
                    newPoints.add(newPoint);
                }
            }break;
        }
    return newPoints;
    }
}
