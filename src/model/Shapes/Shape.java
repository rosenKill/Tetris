package model.Shapes;

import model.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 23.09.2015.
 */
public class Shape {
    protected final static int SIZE=4;
    protected List<Point> points;
    protected List<Integer> states;
    public void moveDown(){ //than we wiil call function with update FIELD from controller
        for(Point p:points){
            int y=p.getPosY();
            //int x=p.getPosX();
            p.setPosY(++y);
        }

    }
    public List<Point> getListPoints(){
      return new ArrayList(points);
    }

    public void moveRight() {
        for(Point p:points){
            int x=p.getPosX();
            p.setPosX(++x);
        }
    }
    public void moveLeft() {
        for(Point p:points){
            int x=p.getPosX();
            p.setPosX(--x);
        }
    }

    public void aroundShape(ArrayList<Point> points){
        this.points=points;

    }

}
