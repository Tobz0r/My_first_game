package se.umu.dv3tes.myapplication.Model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import se.umu.dv3tes.myapplication.GameObjects.Player.Player;

/**
 * Created by Tobz0r on 2016-03-17.
 */
public class HUD {

    private Player player;
    private int health;
    private final int fullHealth=100;
    private int score;

    public HUD(Player player){
        this.player=player;
    }

    public void tick(){
        health=player.getHealth();
        score=player.getScore();

    }
    public void draw(Canvas canvas){
        health= health>0 ? health : 1;
        Paint myPaint = new Paint();
        if(!player.isGotPower()) {
            myPaint.setColor(Color.BLACK);
            myPaint.setStyle(Paint.Style.STROKE);
            canvas.drawRect(15, 15, (fullHealth*3) + 2, 70, myPaint);
            myPaint.setColor(Color.rgb(75, health * 2, 0));
            myPaint.setStyle(Paint.Style.FILL);
            canvas.drawRect(15, 15, health * 3, 68, myPaint);
        }else{
            myPaint.setStyle(Paint.Style.FILL);
            myPaint.setColor(Color.WHITE);
            canvas.drawRect(15, 15, health * 3, 68, myPaint);


        }
    }
}
