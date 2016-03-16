package se.umu.dv3tes.myapplication;

import android.content.res.Resources;
import android.graphics.BitmapFactory;

/**
 * Created by Tobz0r on 2016-03-15.
 */
public class Spawner {

    private Handler handler;
    private int scoreKeep = 100; //set to 100 to make level start at 1
    private int level =0;
    private Resources res;
    private Player player;
    private int width,height;

    public Spawner(Handler handler, Resources res, Player player,int w, int h) {
        this.handler = handler;
        this.res=res;
        width=w;
        height=h;
        this.player=player;
    }

    public void tick() {
        scoreKeep++;
        if (scoreKeep >= 100) {
            scoreKeep = 0;
            level++;
            if (level == 1) {
                handler.addObject(new FlyingEnemy(player, BitmapFactory.decodeResource(res, R.drawable.flying1), width, height, handler,5,res));
            }
            if(level%2==0){
                handler.addObject(new FlyingEnemy(player, BitmapFactory.decodeResource(res, R.drawable.flying1), width, height, handler,5,res));
                handler.addObject(new FlyingEnemy(player, BitmapFactory.decodeResource(res, R.drawable.flying1), width, height, handler,5,res));
            }
            if (level % 10 == 0) {
            }
            if (level % 25 == 0) {
            }
            if (level % 5 == 0) {
            }
        }

    }
}


