package com.example.pc.lunerplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OpenLunerPlayerActivity (View view){
        Intent intent2 = new Intent(this, LunerPlayerActivity.class);
        startActivity(intent2);
    }
}
