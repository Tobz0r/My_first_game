package se.umu.dv3tes.myapplication.Database;

/**
 * Created by Tobz0r on 2016-03-18.
 */
public class PlayerUnit {

    private int score;
    private String name;

    public PlayerUnit(String name,int score){
        this.name=name;
        this.score=score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }




}
