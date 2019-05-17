package com.example.b206.quizmdp6561;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

@Dao
public interface HighScoreDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertHighScore(DBHighScore highScore);
    @Update
    int updateHighScore(DBHighScore highScore);
    @Delete
    int deleteHighScore(DBHighScore highScore);
    @Query("SELECT * FROM tbHighscore order by Score desc")
    DBHighScore[] selectAllHighScore();
}
