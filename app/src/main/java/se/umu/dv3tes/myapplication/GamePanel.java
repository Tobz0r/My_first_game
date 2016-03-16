package se.umu.dv3tes.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tobz0r on 2016-03-15.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    public static  int WIDTH;
    public static  int HEIGHT;
    private GameThread gameThread;
    private Spawner spawner;
    private Level level;
    private Handler handler;
    private Player player;
    private boolean gameOver;
    private Context context;


    public GamePanel(Context context){
        super(context);
        gameOver=false;
        this.context=context;
        handler=new Handler();
        spawner=new Spawner(handler);
        getHolder().addCallback(this);
        gameThread=new GameThread(getHolder(),this);
        setFocusable(true);


    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Bitmap levelImage=BitmapFactory.decodeResource(getResources(),R.drawable.background);
        level=new Level(levelImage);
        WIDTH=levelImage.getWidth();
        HEIGHT=levelImage.getHeight();
        player=new Player(BitmapFactory.decodeResource(getResources(),R.drawable.tower),100,getHeight(),5);
        gameThread.setGameRunning(true);
        gameThread.start();
        handler.addObject(player);

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        handler.addObject(new Projectile(player, BitmapFactory.decodeResource(getResources(), R.drawable.test), (int) event.getX(), (int) event.getY(), handler));
        handler.addObject(new BasicEnemy(player, BitmapFactory.decodeResource(getResources(), R.drawable.flying1), getWidth(), getHeight(), handler,5,getResources()));
        return super.onTouchEvent(event);
    }

    public void tick(){
        if(!player.isAlive() && !gameOver){
            gameOver=true;
            System.out.println("jebane");
            Intent i=new Intent(context,EndActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getContext().startActivity(i);
            ((Activity)context).finish();
        }
        level.tick();
        spawner.tick();
        handler.tick();

    }
    @Override
    public void draw(Canvas canvas){
        float scaleX= getWidth()/(WIDTH*1.f);
        float scaleY = getHeight()/(HEIGHT*1.f);
        scaleX = scaleX < 1 ? scaleX : 1;
        scaleY = scaleY < 1 ? scaleY : 1;
        if(canvas!=null) {
            final int savedState=canvas.save();
            canvas.scale(scaleX, scaleY);
            level.draw(canvas);
            canvas.restoreToCount(savedState);
            handler.draw(canvas);

        }
    }


}
