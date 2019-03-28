package com.player.audio.lunarplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.media.MediaPlayer;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.logging.Handler;

public abstract class MainActivity extends AppCompatActivity {

    Button playBtn;
    SeekBar positionBar;
    SeekBar volumeBar;
    TextView elapsedTimeLabel;
    TextView remainingTimeLabel;
    MediaPlayer mp;
    int totalTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playBtn = (Button) findViewById(R.id.play);
        elapsedTimeLabel = (TextView) findViewById(R.id.elapsedTimeLabel);
        remainingTimeLabel = (TextView) findViewById(R.id.remainingTimeLabel);


        mp = MediaPlayer.create(this.R.songs.song);
        mp.setlooping(true);
        mp.seekTo(0);
        mp.setVolume(0.5f, 05f);
        totalTime = mp.getDuration();

        positionBar = (SeekBar) findViewById(R.id.positionBar);
        positionBar = setMax(totalTime);
        positionBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                        if (fromUser) {
                            mp.seekTo(progress);
                            positionBar.setProgress(progress);
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );


        volumeBar = (SeekBar) findViewById(R.id.volumeBar);
        volumeBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        float volumeNum = progress / 100f;
                        mp.setVolume(volumeNum, volumeNum);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );



    private Handler handler = new  Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            int currentPosition = msg.what;

            positionBar.setProgress(currentPosition);

            String elapsedTime = createTimeLabel(currentPosition);
            remainingTimeLabel.setText("-"+remainingTimeLabel);
        }
    };

    public String createTimeLabel(int time)
    {
        String timeLabel = "";
        int min = time/1000/60;
        int sec = time/1000%60;

        timeLabel = min + ":";
        if (sec<10)timeLabel +="0";
        timeLabel += sec;

        return timeLabel;
    }

    public void PlayBtnClick(View view) {
        if (!mp.isPlaying()) {
            mp.Start();
            playBtn.setBackgroundResource(R.drawable.stop);
        } else {
            mp.pause();
            playBtn.setBackgroundResource(R.drawable.play);
        }

    }
}
