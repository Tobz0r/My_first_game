package se.umu.dv3tes.myapplication;

import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tobz0r on 2016-03-15.
 */
public class Handler {

    private List<GameObject> gameObjects;
    private static Object lockObject=new Object();

    public Handler(){
        gameObjects=new ArrayList<>();
    }

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
                                ((Player) temp_enemy).attackThis(1000);
                                ((Projectile) temp).finishProjectile();
                                gameObjects.remove(temp);
                            }
                        }
                    }
                }


            }
        }
    }
    public void draw(Canvas canvas){
        synchronized (lockObject) {
            for(int i=0; i < gameObjects.size();i++){
                gameObjects.get(i).draw(canvas);
            }
        }
    }

    public void addObject(GameObject object){
        synchronized (lockObject) {
            gameObjects.add(object);
        }
    }
    public void removeObject(GameObject object){
        synchronized (lockObject) {
            gameObjects.remove(object);
        }
    }

}
