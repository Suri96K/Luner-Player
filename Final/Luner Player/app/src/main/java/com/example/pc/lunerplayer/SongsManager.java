package com.example.pc.lunerplayer;

import android.media.MediaMetadataRetriever;
import android.os.Environment;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class SongsManager {

    MediaMetadataRetriever mediaInfo = new MediaMetadataRetriever();

    private static final String SD_Path =
            Environment.getExternalStorageDirectory().getPath()+"/";
    private ArrayList<HashMap<String, String>> songlist = new ArrayList<HashMap<String, String>>();

    public ArrayList<HashMap<String, String>> getPlayList(){

        if (SD_Path != null) {


            File home = new File(SD_Path);
            File[] listFiles = home.listFiles();

            if (listFiles != null && listFiles.length > 0) {
                for (File file : listFiles) {

                    if (file.isDirectory()) {
                        scanDirectory(file);
                    } else {

                        addsongToList(file);

                    }
                }
            }

        }



        return songlist;





    }



    private void scanDirectory(File directory) {
        if (directory != null) {

            File[] listFiles = directory.listFiles();

            if (listFiles != null && listFiles.length > 0) {
                for (File file : listFiles) {
                    if (file.isDirectory()) {
                        scanDirectory(file);
                    } else {

                        addsongToList(file);


                    }

                }
            }

        }



    }

    private void addsongToList(File songFile){

        if (songFile.getName().endsWith(".mp3")){


            HashMap<String, String> song = new HashMap<String, String>();
            song.put("songTitle", songFile.getName().substring(0, (songFile.getName().length() - 4)));
            song.put("songPath", songFile.getPath());
            mediaInfo.setDataSource(songFile.getPath());
            song.put("songArtist", mediaInfo.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST));


            songlist.add(song);

        }


    }

}
