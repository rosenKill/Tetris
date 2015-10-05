package model.Shapes;

import model.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 05.10.2015.
 */
public class ZRight extends Shape{
    int activState;
    public ZRight(int sizeX,int sizeY){
        states=new ArrayList<Integer>(4);
        states.add(0);
        states.add(1);
        states.add(2);
        states.add(3);
        points=new ArrayList<Point>(SIZE);
        activState=states.get(0);
        for(int i=0,shift=0;i<SIZE;i++,shift++){
            Point p=new Point();
            points.add(p);
            p.setPosX(sizeX / 2 + shift);
            p.setPosY(0);
            if(i>1){p.setPosY(1);
            p.setPosX(sizeX / 2 -(--shift));
            shift=0;}

        }


    }
    public List<Point> getPointForAround(int state){
        List<Point> newPoints=new ArrayList<Point>(); //create new temp list with coord for check on empty
        for(int i=0;i<points.size();i++){
            Point newP;
            Point oldP=points.get(i);
            int x=oldP.getPosX();
            int y=oldP.getPosY();
            switch (state){
                case 1:{
                    if(i==0){newP= new Point(x,y);}
                    else if(i==1){ newP= new Point(--x,++y);}
                    else if(i==2){y-=2; newP= new Point(x,y);}
                    else{ newP= new Point(--x,--y);}
                    newPoints.add(newP);
                }break;

                case 2:{if(i==0){newP= new Point(x,y);}
                else if(i==1){ newP= new Point(--x,--y);}
                else if(i==2){x+=2; newP= new Point(x,y);}
                else{ newP= new Point(++x,--y);}
                    newPoints.add(newP);}break;

                case 3:{if(i==0){newP= new Point(x,y);}
                else if(i==1){ newP= new Point(++x,--y);}
                else if(i==2){y+=2; newP= new Point(x,y);}
                else{ newP= new Point(++x,++y);}
                    newPoints.add(newP);}break;

                case 0:{if(i==0){newP= new Point(x,y);}
                else if(i==1){ newP= new Point(++x,++y);}
                else if(i==2){x-=2; newP= new Point(x,y);}
                else{ newP= new Point(--x,++y);}
                    newPoints.add(newP);}break;
            }
        }
        return newPoints;
    }
}
