package com.example.b206.quizmdp6561;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class Home extends AppCompatActivity implements Level.OnFragmentInteractionListener,HighScore.OnFragmentInteractionListener{

    FragmentTransaction ft;
    String nama;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent = getIntent();
        nama = intent.getStringExtra("Nama");
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFragment,new Level());
        ft.commit();
    }

    @Override
    public void onFragmentInteraction(int uri) {
        Intent intent = new Intent(this,Game.class);
        intent.putExtra("Nama",nama);
        if(uri == R.id.easy)
            intent.putExtra("Level","easy");
        else if(uri == R.id.medium)
            intent.putExtra("Level","medium");
        else if(uri == R.id.hard)
            intent.putExtra("Level","hard");
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.level)
        {
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFragment,new Level());
            ft.commit();
        }
        else if(item.getItemId() == R.id.highscore)
        {
            ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFragment,new HighScore());
            ft.commit();
        }
        else if(item.getItemId() == R.id.logout)
        {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
