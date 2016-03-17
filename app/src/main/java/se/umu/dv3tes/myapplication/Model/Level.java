package se.umu.dv3tes.myapplication.Model;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import se.umu.dv3tes.myapplication.Model.GamePanel;

/**
 * Created by Tobz0r on 2016-03-15.
 */
public class Level {

    private Bitmap image;
    private int x,y,dx,dy;
    private int width,height;

    public Level(Bitmap image){
        this.image=image;
        width=image.getWidth();
        height=image.getHeight();
    }

    public void tick(){


    }
    public void draw(Canvas canvas){
        canvas.drawBitmap(image,x,y,null);
        if(x < 0){
            canvas.drawBitmap(image,x+width,y,null);
        }
    }
    public static float clamp(float x, float min, float max){
        if(x >= max)
            return x= max;
        else if (x<= min)
            return x=min;
        else
            return x;
    }




}
