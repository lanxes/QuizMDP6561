package com.example.b206.quizmdp6561;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {DBHighScore.class}, version =  1)
public abstract  class appDatabase extends RoomDatabase {
    public  abstract HighScoreDAO highScoreDAO();
}
