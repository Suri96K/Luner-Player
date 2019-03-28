package com.example.pc.lunerplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.audiofx.Equalizer;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.support.v4.app.NotificationCompat;

public class PlayerActivity extends AppCompatActivity implements OnCompletionListener, SeekBar.OnSeekBarChangeListener  {

    private ImageButton btnPlay;
    //private ImageButton btnForward;
    //private ImageButton btnBackward;
    private ImageButton btnNext;
    private ImageButton btnPrevious;
    private ImageButton btnPlaylist;
    private ImageButton btnEq;
    private ImageButton btnRepeat;
    private ImageButton btnShuffle;
    private SeekBar songProgressBar;
    private TextView songTitleLabel;
    private TextView songCurrentDurationLabel;
    private TextView songTotalDurationLabel;
    // Media Player
    private MediaPlayer mp;
    // Handler to update UI timer, progress bar etc,.
    private Handler mHandler = new Handler();
    private SongsManager songManager;
    private Utilities utils;
    private int seekForwardTime = 5000; // 5000 milliseconds
    private int seekBackwardTime = 5000; // 5000 milliseconds
    public int currentSongIndex;
    private boolean isShuffle = false;
    private boolean isRepeat = false;
    private ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
    private String songTitle = "";
    private TextView txt_songindex;
    private int nowplayingsongIndex;
    private ImageView songAlburmArtView;
    private NotificationManager nm;
    private byte[] AlburmArt;
    //private int currentlength;
    private int SessionID;
    private TextView songsong;
    final int audiosessionId = 1088112;


    MediaMetadataRetriever mediaInfo = new MediaMetadataRetriever();


    private static final int NOTIFY_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        // All player buttons
        btnPlay = (ImageButton) findViewById(R.id.play_button);
        //btnForward = (ImageButton) findViewById(R.id.btnForward);
        //btnBackward = (ImageButton) findViewById(R.id.btnBackward);
        btnNext = (ImageButton) findViewById(R.id.next_button);
        btnPrevious = (ImageButton) findViewById(R.id.back_button);
        btnPlaylist = (ImageButton) findViewById(R.id.Playlist_Button);
        btnEq = (ImageButton) findViewById(R.id.eq_Button);
        //btnRepeat = (ImageButton) findViewById(R.id.btnRepeat);
        //btnShuffle = (ImageButton) findViewById(R.id.btnShuffle);
        songProgressBar = (SeekBar) findViewById(R.id.seekbar_progress);
        songTitleLabel = (TextView) findViewById(R.id.songid);
        songCurrentDurationLabel = (TextView) findViewById(R.id.songCurrentDurationLabel);
        songTotalDurationLabel = (TextView) findViewById(R.id.songTotalDurationLabel);
        txt_songindex = (TextView) findViewById(R.id.txt_currentsongindex);
        songAlburmArtView = (ImageView) findViewById(R.id.imageview);
        songsong = (TextView)findViewById(R.id.txt_songsong);


        // By default play first song

        // Mediaplayer
        mp = new MediaPlayer();
        songManager = new SongsManager();
        utils = new Utilities();

        // Listeners
        songProgressBar.setOnSeekBarChangeListener(this); // Important
        mp.setOnCompletionListener(this); // Important

        // Getting all songs list
        songsList = songManager.getPlayList();

        if (savedInstanceState != null) {

            int lastplayedsongIndexsaved = savedInstanceState.getInt("CURRENTSONGINDEX");
            currentSongIndex = lastplayedsongIndexsaved;

        }


        int EMPTY = songsList.size() + 1;

        int lastplayedsongIndex = getPreferences(Context.MODE_PRIVATE).getInt("CURRENTSONGINDEX",
                EMPTY);
        if (lastplayedsongIndex != EMPTY) {
            currentSongIndex = lastplayedsongIndex;
        } else
            currentSongIndex = 0;





        mp.setAudioSessionId(audiosessionId);
        SessionID = mp.getAudioSessionId();
        songsong.setText(String.valueOf(SessionID));



        btnPlay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // check for already playing
                if (mp.isPlaying()) {
                    if (mp != null) {
                        mp.pause();
                        // Changing button image to play button
                        btnPlay.setImageResource(R.drawable.btn_play);
                    }
                } else {
                    // Resume song
                    if (mp != null) {
                        mp.start();
                        // Changing button image to pause button
                        btnPlay.setImageResource(R.drawable.btn_pause);
                    }
                }

            }
        });


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        Intent notIntent = new Intent(getApplicationContext(), PlayerActivity.class);
        notIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendInt = PendingIntent.getActivity(this, 0, notIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(pendInt);
        builder.setSmallIcon(R.drawable.play_notify);
        builder.setContentTitle("Playing");
        builder.setContentText(songTitle);

        Notification not = builder.build();

        nm = (NotificationManager) getSystemService(
                NOTIFICATION_SERVICE);

        nm.notify(NOTIFY_ID, not);







        btnNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // check if next song is there or not
                if (currentSongIndex < (songsList.size() - 1)) {
                    playSong(currentSongIndex + 1);
                    currentSongIndex = currentSongIndex + 1;
                } else {
                    // play first song
                    playSong(0);
                    currentSongIndex = 0;
                }

            }
        });

        btnNext.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                forwardsong();

                return true;

            }
        });



        btnPrevious.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (currentSongIndex > 0) {
                    playSong(currentSongIndex - 1);
                    currentSongIndex = currentSongIndex - 1;
                } else {
                    // play last song
                    playSong(songsList.size() - 1);
                    currentSongIndex = songsList.size() - 1;
                }

            }
        });


        btnPlaylist.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivityForResult(i, 100);
            }
        });

        btnEq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent eq = new Intent(getApplicationContext(), EqualizerActivity.class);

                eq.putExtra("ID", SessionID);
                setResult(150, eq);
                startActivity(eq);

            }
        });


    }


    private void forwardsong() {

        // get current song position
        int currentPosition = mp.getCurrentPosition();
        // check if seekForward time is lesser than song duration

        if (currentPosition + seekForwardTime <= mp.getDuration()) {
            // forward song
            mp.seekTo(currentPosition + seekForwardTime);
        } else {
            // forward to end position
            mp.seekTo(mp.getDuration());
        }


    }



    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 100) {
            currentSongIndex = data.getExtras().getInt("songIndex");
            // play selected song
            playSong(currentSongIndex);

        }

    }

    public void playSong(int songIndex) {
        // Play song
        try {
            mp.reset();
            mp.setDataSource(songsList.get(songIndex).get("songPath"));
            mp.prepare();
            mp.start();
            // Displaying Song title
            songTitle = songsList.get(songIndex).get("songTitle");
            songTitleLabel.setText(songTitle);


            nowplayingsongIndex = Integer.valueOf(songIndex);

            txt_songindex.setText(String.valueOf(songIndex));

            mediaInfo.setDataSource(songsList.get(songIndex).get("songPath"));

            try {

                AlburmArt = mediaInfo.getEmbeddedPicture();


                Bitmap songArt = BitmapFactory.decodeByteArray(AlburmArt, 0, AlburmArt.length);


                songAlburmArtView.setImageBitmap(songArt);

            } catch (Exception e) {
                songAlburmArtView.setImageResource(R.drawable.adele);
            }


            // Changing Button Image to pause image
            btnPlay.setImageResource(R.drawable.btn_pause);

            // set Progress bar values
            songProgressBar.setProgress(0);
            songProgressBar.setMax(100);

            // Updating progress bar
            updateProgressBar();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



    public void updateProgressBar() {
        mHandler.postDelayed(mUpdateTimeTask, 100);
    }


    private Runnable mUpdateTimeTask = new Runnable() {
        public void run() {
            long totalDuration = mp.getDuration();
            long currentDuration = mp.getCurrentPosition();

            // Displaying Total Duration time
            songTotalDurationLabel.setText("" + utils.milliSecondsToTimer(totalDuration));
            // Displaying time completed playing
            songCurrentDurationLabel.setText("" + utils.milliSecondsToTimer(currentDuration));

            // Updating progress bar
            int progress = (int) (utils.getProgressPercentage(currentDuration, totalDuration));
            //Log.d("Progress", ""+progress);
            songProgressBar.setProgress(progress);

            // Running this thread after 100 milliseconds
            mHandler.postDelayed(this, 100);
        }
    };


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {

    }


    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // remove message Handler from updating progress bar
        mHandler.removeCallbacks(mUpdateTimeTask);
    }


    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mHandler.removeCallbacks(mUpdateTimeTask);
        int totalDuration = mp.getDuration();
        int currentPosition = utils.progressToTimer(seekBar.getProgress(), totalDuration);

        // forward or backward to certain seconds
        mp.seekTo(currentPosition);

        // update timer progress again
        updateProgressBar();
    }


    @Override
    public void onCompletion(MediaPlayer arg0) {

        // check for repeat is ON or OFF
        if (isRepeat) {
            // repeat is on play same song again
            playSong(currentSongIndex);
        } else if (isShuffle) {
            // shuffle is on - play a random song
            Random rand = new Random();
            currentSongIndex = rand.nextInt((songsList.size() - 1) - 0 + 1) + 0;
            playSong(currentSongIndex);
        } else {
            // no repeat or shuffle ON - play next song
            if (currentSongIndex < (songsList.size() - 1)) {
                playSong(currentSongIndex + 1);
                currentSongIndex = currentSongIndex + 1;
            } else {
                // play first song
                playSong(0);
                currentSongIndex = 0;
            }
        }
    }


    private void savesettings() {


        SharedPreferences.Editor SPeditor = getPreferences(Context.MODE_PRIVATE).edit();
        SPeditor.clear();
        SPeditor.putInt("CURRENTSONGINDEX", nowplayingsongIndex);



        SPeditor.apply();


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putInt("CURRENTSONGINDEX", nowplayingsongIndex);


        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {

        savesettings();

        super.onStop();
    }


    public void OpenPlayListActivity (View view){
        Intent intent2 = new Intent(this, SongsManager.class);
        startActivity(intent2);
    }

}
