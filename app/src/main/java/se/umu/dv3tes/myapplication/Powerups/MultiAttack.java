package se.umu.dv3tes.myapplication.Powerups;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.List;
import java.util.Random;

import se.umu.dv3tes.myapplication.GameLogic.Handler;
import se.umu.dv3tes.myapplication.GameObjects.GameObject;
import se.umu.dv3tes.myapplication.GameObjects.Player.Player;
import se.umu.dv3tes.myapplication.GameObjects.Projectiles.BasicProjectile;

/**
 * Powerup who gives the user multitouch to attack
 * on different places at the same time
 * @author Tobias Estefors
 */
public class MultiAttack extends GameObject implements Powerup {
    private Handler handler;
    private Player player;
    private List<BasicProjectile> projectiles;
    private Bitmap image;

    public MultiAttack (Player player, Handler handler,Bitmap image,int x){
        setVelX(0);
        setVelY(5);
        setY(0);
        Random random=new Random();
        setX(random.nextInt(x)+1);
        this.handler=handler;
        this.player=player;
        this.image=image;
        setHeight(image.getHeight() / 3);
        setWidth(image.getWidth() / 3);
    }


    /**
     * Updates the gamestate for this powerup
     */
    @Override
    public void tick() {
        setX(getX() + getVelX());
        setY(getY() + getVelY());
        projectiles=handler.getProjectiles();
        for(int i=0; i < projectiles.size();i++){
            BasicProjectile temp=projectiles.get(i);
            if (getBounds().intersect(temp.getBounds())){
                if(!temp.isHostile()){
                    player.powerUp(Powerups.ATTACK);
                    handler.removeObject(this);
                }
            }
        }
    }

    /**
     * Draws this powerup on the board
     * @param canvas the board
     */
    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, getX(), getY(), null);
    }
}
