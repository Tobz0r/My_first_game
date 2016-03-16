package se.umu.dv3tes.myapplication;

/**
 * Created by Tobz0r on 2016-03-16.
 */
public class Position {

    int x,y;

    public Position(int x, int y){
        this.x=x;
        this.y=y;
    }

    public Position(Long x, Long y){
        this.x=x.intValue();
        this.y=y.intValue();
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setX(int x){
        this.x=x;
    }
    public void setY(int y){
        this.y=y;
    }

}
