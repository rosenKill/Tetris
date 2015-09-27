package model;

/**
 * Created by User on 23.09.2015.
 */
public class Point {
    private int posX;
    private int posY;
    public Point(int x,int y){
        posX=x;
        posY=y;

    }
    public  Point(){

    }
    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
