package controller;

import model.Shapes.Line;
import model.Shapes.Shape;
import model.Shapes.Square;
import model.Shapes.ZRight;

import java.util.ArrayList;

/**
 * Created by User on 13.09.2015.

1
    00
     0
     0
 2
     00
     0
     0
 3
     0000
 4
      00
     00

 */
public class ListFigures {
    private ArrayList<Shape> shapeArrayList;
    public ListFigures(int x,int y){
        shapeArrayList=new ArrayList<>();
        shapeArrayList.add(new Square(x,y));
         shapeArrayList.add(new Line(x,y));
        shapeArrayList.add(new ZRight(x,y));

    }
    public Shape getRandomShape(){
        int rand= (int) (Math.random()*shapeArrayList.size());
      return shapeArrayList.get(rand);
    }
}
