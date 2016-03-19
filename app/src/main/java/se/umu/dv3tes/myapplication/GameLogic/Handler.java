package se.umu.dv3tes.myapplication.GameLogic;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

import se.umu.dv3tes.myapplication.GameObjects.Enemies.Enemy;
import se.umu.dv3tes.myapplication.GameObjects.GameObject;
import se.umu.dv3tes.myapplication.GameObjects.Player.Player;
import se.umu.dv3tes.myapplication.GameObjects.Projectiles.BasicProjectile;
import se.umu.dv3tes.myapplication.GameObjects.Projectiles.Projectile;

/**
 * Contains all objects used in the game and uses their
 * tick- and drawmethods
 * @author Tobias Estefors
 */
public class Handler {

    private List<GameObject> gameObjects;
    private static Object lockObject=new Object();
    private final int projectileDamage=3;

    public Handler(){
        gameObjects=new ArrayList<>();
    }

    /**
     * Updates every gameobject's gamestate
     */
    public void tick() {
        synchronized (lockObject) {
            for (int i = 0; i < gameObjects.size(); i++) {
                GameObject temp = gameObjects.get(i);
                temp.tick();
                if (temp instanceof Projectile) {
                    for (int j = 0; j < gameObjects.size(); j++) {
                        GameObject temp_enemy = gameObjects.get(j);
                        if (temp_enemy instanceof Enemy && !((Projectile) temp).isHostile()) {
                            if (temp.getBounds().intersect(temp_enemy.getBounds())) {
                                ((Enemy) temp_enemy).attackThis();
                                ((Projectile) temp).finishProjectile();
                                gameObjects.remove(temp);
                            }
                        }
                        else if(temp_enemy instanceof Player &&((Projectile) temp).isHostile()){
                            if (temp.getBounds().intersect(temp_enemy.getBounds())) {
                                ((Player) temp_enemy).attackThis(projectileDamage);
                                ((Projectile) temp).finishProjectile();
                                gameObjects.remove(temp);
                            }
                        }
                    }
                }


            }
        }
    }

    /**
     * Draw every gameobject on the phone
     * @param canvas the drawable screen
     */
    public void draw(Canvas canvas){
        synchronized (lockObject) {
            for(int i=0; i < gameObjects.size();i++){
                gameObjects.get(i).draw(canvas);
            }
        }
    }

    /**
     *Add a gameobject to the handlers list
     * @param object a gameobject
     */
    public void addObject(GameObject object){
        synchronized (lockObject) {
            gameObjects.add(object);
        }
    }

    /**
     * Remove a gameobject from the handlers list
     * @param object a gameobject
     */
    public void removeObject(GameObject object){
        synchronized (lockObject) {
            gameObjects.remove(object);
        }
    }

    /**
     * Gets a list for all the projectiles,
     * used by powersups to not get killed by
     * hostile projectiles
     * @return a list of projectiles
     */
    public  List<BasicProjectile> getProjectiles(){
        List<BasicProjectile> projectiles=new ArrayList<>();
        for(int i=0; i < gameObjects.size(); i++){
            GameObject temp=gameObjects.get(i);
            if(temp instanceof BasicProjectile){
                projectiles.add((BasicProjectile)temp);
            }
        }
        return projectiles;
    }

    /**
     * Empties the list of gameobjects,
     * used in the end of the game
     */
    public void clear(){
        gameObjects.clear();
    }

}
