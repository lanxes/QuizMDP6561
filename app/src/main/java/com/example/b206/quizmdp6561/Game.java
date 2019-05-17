package com.example.b206.quizmdp6561;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Game extends AppCompatActivity {

    Button btn;
    EditText ed;
    TextView tv;
    TextView tv1,tv2;
    TextView tv3;
    Button giveup;
    int life = 5;
    int score = 0;
    int angka1 = 0;
    int angka2 = 0;
    int hasil = 0;
    String nama;
    String level;
    appDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = Room.databaseBuilder(getApplicationContext(),appDatabase.class,"highscoredb").build();
        Intent intent = getIntent();
        level = intent.getStringExtra("Level");
        nama = intent.getStringExtra("Nama");
        int range = 2;
        if(level.equals("easy"))
            range = 11;
        else if(level.equals("medium"))
            range = 101;
        else if(level.equals("hard"))
            range = 1001;

        setContentView(R.layout.activity_game);
        ed = findViewById(R.id.answer);
        Random rnd = new Random();
        angka1 = rnd.nextInt(range);
        angka2 = rnd.nextInt(range);
        hasil = angka1 + angka2;
        tv1 = findViewById(R.id.tvangka1);
        tv2 = findViewById(R.id.tvangka2);
        tv3 = findViewById(R.id.tvLevel);
        tv3.setText("Level : " + level);
        btn = findViewById(R.id.button2);
        tv = findViewById(R.id.textView3);
        giveup = findViewById(R.id.button3);
        tv1.setText(angka1+"");
        tv2.setText(angka2+"");
    }

    public void btnGo(View v)
    {
        if(!btn.getText().toString().equals("Done")) {
            if (!ed.getText().toString().equals("")) {
                int jawaban = Integer.parseInt(ed.getText().toString());
                if (jawaban == hasil) {
                    btn.setText("Done");
                    Toast.makeText(this, "You're Right!", Toast.LENGTH_SHORT).show();
                } else {
                    life--;
                    tv.setText("Life : " + life);
                    if(life == 0)
                    {
                        Toast.makeText(this, "Game Over!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, Home.class);
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(this, "You're Wrong!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Field cannot be empty!", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(this, "You Win!\nYour Score:" + (life*20), Toast.LENGTH_SHORT).show();
            insertDataHighScore(new DBHighScore(level,nama,(life*20)));

            Intent intent = new Intent(this, Home.class);
            intent.putExtra("Nama",nama);
            startActivity(intent);
        }
    }

    public void btnGive(View v)
    {
        Intent intent = new Intent(this, Home.class);
        intent.putExtra("Nama",nama);
        startActivity(intent);
    }

    private void insertDataHighScore(final DBHighScore h){
        new AsyncTask<Void,Void,Long>(){
            @Override
            protected Long doInBackground(Void... voids) {
                long status = db.highScoreDAO().insertHighScore(h);
                return status;
            }

            @Override
            protected void onPostExecute(Long status) {

            }
        }.execute();

    }
}
