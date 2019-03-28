package com.example.pc.lunerplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void OpenContactUsActivity (View view){
        Intent intent2 = new Intent(this, ContactUsActivity.class);
        startActivity(intent2);
    }

    public void OpenEqualizerActivity (View view){
        Intent intent2 = new Intent(this, EqualizerActivity.class);
        startActivity(intent2);
    }

    public void OpenAboutUsActivity (View view){
        Intent intent2 = new Intent(this, AboutUsActivity.class);
        startActivity(intent2);
    }
}
