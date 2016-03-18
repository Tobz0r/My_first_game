package se.umu.dv3tes.myapplication.GameObjects;

/**
 * Created by Tobz0r on 2016-03-16.
 */
public class Position {

    int x,y;

    public Position(int x, int y){
        this.x=x;
        this.y=y;
    }
    public Position(){
        x=0;
        y=0;
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

    @Override
    public int hashCode() {
        int hash = 17;
        hash = ((hash + x) << 5) - (hash + x);
        hash = ((hash + y) << 5) - (hash + y);
        return hash;
    }
    @Override
    public boolean equals(Object o) {
        if(this==o)
            return true;
        if (!(o instanceof Position))
            return false;
        Position temp = (Position) o;
        return((temp.getX()==getX() )&& temp.getY()==getY());

    }

}
