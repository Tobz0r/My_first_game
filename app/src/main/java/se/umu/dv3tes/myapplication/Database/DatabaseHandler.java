package se.umu.dv3tes.myapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "highScore",
            TABLE_HIGHSCORE = "players",
            KEY_NAME = "name",
            KEY_SCORE = "score";
    ;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_HIGHSCORE + "(" + KEY_NAME + " TEXT," + KEY_SCORE + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HIGHSCORE);

        onCreate(db);
    }


    public void addHighscore(String name, int score) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME, name);
        values.put(KEY_SCORE, score);
        db.insert(TABLE_HIGHSCORE, null, values);
        db.close();
    }


    public List<PlayerScore> getAllPlayers() {
        List<PlayerScore> players = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_HIGHSCORE, null);
        if (cursor.moveToFirst()) {
            do {
                players.add(new PlayerScore(cursor.getString(0), Integer.parseInt(cursor.getString(1))));
            }
            while (cursor.moveToNext());
        }
        int n = players.size();
        int temp = 0;
        String tempString;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (players.get(j - 1).getScore() > players.get(j).getScore()) {
                    temp = players.get(j - 1).getScore();
                    tempString = players.get(j - 1).getName();
                    players.get(j - 1).setScore(players.get(j).getScore());
                    players.get(j - 1).setName(players.get(j).getName());
                    players.get(j).setScore(temp);
                    players.get(j).setName(tempString);
                }
            }
        }
        cursor.close();
        db.close();
        return players;
    }
    public void clearHighscore() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HIGHSCORE);
        onCreate(db);
    }


}
