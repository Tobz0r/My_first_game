package se.umu.dv3tes.myapplication.Model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import se.umu.dv3tes.myapplication.GameObjects.Player.Player;

/**
 * The hud is the healthbar used to show the
 * user how much health he has left
 * @author Tobias Estefors
 */
public class HUD {

    private Player player;
    private float health;
    private final int fullHealth=100;

    public HUD(Player player){
        this.player=player;
    }

    /**
     * Updates what health the player have
     */
    public void tick(){
        health=player.getHealth();
    }

    /**
     * Draw a healthbar on the board
     * @param canvas the board
     */
    public void draw(Canvas canvas){
        float greenValue=health*2;
        health= health>1 ? health : 5;
        Paint myPaint = new Paint();
        if(!player.isGotPower()) {
            myPaint.setColor(Color.BLACK);
            myPaint.setStyle(Paint.Style.STROKE);
            canvas.drawRect(15, 15, (fullHealth*3) + 2, 70, myPaint);
            myPaint.setColor(Color.rgb(75, (int)greenValue, 0));
            myPaint.setStyle(Paint.Style.FILL);
            canvas.drawRect(15, 15, health * 3, 68, myPaint);
        }else{
            myPaint.setStyle(Paint.Style.FILL);
            myPaint.setColor(Color.WHITE);
            canvas.drawRect(15, 15, health * 3, 68, myPaint);
        }
    }
}
