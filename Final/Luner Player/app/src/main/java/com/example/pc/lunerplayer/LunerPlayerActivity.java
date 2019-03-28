package com.example.pc.lunerplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LunerPlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luner_player);
    }

    public void OpenSettingsActivity (View view){
        Intent intent2 = new Intent(this, SettingsActivity.class);
        startActivity(intent2);
    }

    public void OpenSaveAndCheackFilesActivity (View view){
        Intent intent2 = new Intent(this, SaveAndCheackFilesActivity.class);
        startActivity(intent2);
    }

    public void OpenArtistActivity (View view){
        Intent intent2 = new Intent(this, ArtistActivity.class);
        startActivity(intent2);
    }

    public void OpenPlayerActivity (View view){
        Intent intent2 = new Intent(this, PlayerActivity.class);
        startActivity(intent2);
    }
    public void OpenPlayListActivity (View view){
        Intent intent2 = new Intent(this, SongsManager.class);
        startActivity(intent2);
    }

    public void OpenContactUsActivity (View view){
        Intent intent2 = new Intent(this, ContactUsActivity.class);
        startActivity(intent2);
    }

    public void OpenPlayer2Activity (View view){
        Intent intent2 = new Intent(this, Player2Activity.class);
        startActivity(intent2);
    }
}
