package se.umu.dv3tes.myapplication.Model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import se.umu.dv3tes.myapplication.Activitys.EndActivity;
import se.umu.dv3tes.myapplication.Activitys.MainActivity;
import se.umu.dv3tes.myapplication.GameLogic.GameThread;
import se.umu.dv3tes.myapplication.GameLogic.Handler;
import se.umu.dv3tes.myapplication.GameObjects.Projectiles.BasicProjectile;
import se.umu.dv3tes.myapplication.GameObjects.Player.Player;
import se.umu.dv3tes.myapplication.R;
import se.umu.dv3tes.myapplication.GameLogic.Spawner;
import se.umu.dv3tes.myapplication.GameObjects.Player.Player;


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
    private HUD hud;
    private float xAxis = 0f;
    private float yAxis = 0f;

    private float lastXAxis = 0f;
    private float lastYAxis = 0f;


    public GamePanel(Context context){
        super(context);
        gameOver=false;
        this.context=context;
        handler=new Handler();
        getHolder().addCallback(this);
        gameThread=new GameThread(getHolder(),this);
        setFocusable(true);
        BasicProjectile.setPicture(BitmapFactory.decodeResource(getResources(), R.drawable.test));
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
        spawner=new Spawner(handler,getResources(),player,getWidth(),getHeight());
        hud=new HUD(player);


    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        try {
            gameThread.stopThread();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float fingerOneX=0,fingerOneY=0,fingerTwoX=0,fingerTwoY=0;
        int action = event.getAction() & MotionEvent.ACTION_MASK;
        int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
        int pointerId = event.getPointerId(pointerIndex);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                fingerOneX=event.getX();
                fingerOneY=event.getY();
                spawner.spawnProjectiles((int)fingerOneX,(int)fingerOneY);
            case MotionEvent.ACTION_POINTER_DOWN:

                if (pointerId == 0) {
                    fingerOneX = event.getX(pointerIndex);
                    fingerOneY = event.getY(pointerIndex);
                    spawner.spawnProjectiles((int)fingerOneX,(int)fingerOneY);
                }
                if (pointerId == 1) {
                    fingerTwoX = event.getX(pointerIndex);
                    fingerTwoY = event.getY(pointerIndex);
                    spawner.spawnProjectiles((int) fingerTwoX, (int) fingerTwoY);
                }
                break;
        }
        return true;

    }


    public void tick(){
        if(!player.isAlive() && !gameOver){
            gameOver=true;
            System.out.println("jebane");
            Intent i=new Intent(context,EndActivity.class);
            i.putExtra("Score", player.getScore());
            i.putExtra("Level", spawner.getLevel());
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getContext().startActivity(i);
            ((Activity)context).finish();
        }
        spawner.tick();
        handler.tick();
        hud.tick();

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
            hud.draw(canvas);
            canvas.restoreToCount(savedState);
            handler.draw(canvas);

        }
    }


}
