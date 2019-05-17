package com.example.b206.quizmdp6561;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import java.io.Serializable;

@Entity(tableName = "tbHighscore")
public class DBHighScore implements Serializable{
    @PrimaryKey(autoGenerate = true)
    private int idHighscore;
    @ColumnInfo(name = "Mode_Level")
    private String mode_level;
    @ColumnInfo(name = "Nama")
    private String nama;
    @ColumnInfo(name = "Score")
    private int score;

    public DBHighScore(String mode_level, String nama, int score) {
        this.mode_level = mode_level;
        this.nama = nama;
        this.score = score;
    }

    public int getIdHighscore() {return idHighscore;}

    public void setIdHighscore(int idHighscore) { this.idHighscore = idHighscore;}

    public String getMode_level() {return mode_level;}

    public void setMode_level(String mode_level) {this.mode_level = mode_level;}

    public String getNama() {return nama;}

    public void setNama(String nama) {this.nama = nama;}

    public int getScore() {return score;}

    public void setScore(int score) {this.score = score;}

    @Override
    public String toString() {
        return mode_level + " - " + nama + " - " + score;
    }
}
