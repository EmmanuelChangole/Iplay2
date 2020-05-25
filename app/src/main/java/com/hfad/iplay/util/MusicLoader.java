package com.hfad.iplay.util;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.MediaStore;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

public class MusicLoader
{
    private Context context;
    private ArrayList<String> songsList;
    private ArrayList<String> songsArtist;
    private ArrayList<String> songsDuration;
    private String[] projection = {
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media.DURATION
    };

    @RequiresApi(api = Build.VERSION_CODES.O)
    public MusicLoader(Context context ){
        this.context = context;
       songsList=new ArrayList<>();
       songsArtist=new ArrayList<>();
       songsDuration=new ArrayList<>();
       getMusic();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void getMusic()
    {
        ContentResolver contentResolver=context.getContentResolver();
        Uri songsUri= MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor songCursor=contentResolver.query(songsUri,projection,null,null,null);
        if(songCursor!=null && songCursor.moveToFirst())
        {
            int songTitle=songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int artist=songCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int duration=songCursor.getColumnIndex(MediaStore.Audio.Media.DURATION);
            do
                {
                  songsList.add(songCursor.getString(songTitle));
                  songsArtist.add(songCursor.getString(artist));
                  songsDuration.add(songCursor.getString(duration));

                }
       while (songCursor.moveToNext());


        }


    }

    public ArrayList<String> getSongsList() {
        return songsList;
    }

    public ArrayList<String> getSongsArtist() {
        return songsArtist;
    }



    public ArrayList<String> getSongsDuration() {
        return songsDuration;
    }


}
