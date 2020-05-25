package com.hfad.iplay;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hfad.iplay.adapter.SongsAdapter;
import com.hfad.iplay.util.MusicLoader;

import java.io.File;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SongFragment extends Fragment {
    private ArrayList<String> musicList=new ArrayList<>();
    private ArrayList<String> musicAlbum=new ArrayList<>();
    private ArrayList<String> musicDuration=new ArrayList<>();
    private View view;
    private Context context;
    private RecyclerView recyclerView;
    private SongsAdapter recyclerAdapter;

    public SongFragment(Context context)
    {
        this.context=context;
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_song, container, false);
        intWidgets(view);
        setMusicList();
        return view;


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setMusicList()
    {
        musicList.clear();
        musicAlbum.clear();
        musicDuration.clear();
        MusicLoader musicLoader=new MusicLoader(context);

        musicList=musicLoader.getSongsList();
        musicAlbum=musicLoader.getSongsArtist() ;
        musicDuration=musicLoader.getSongsDuration();

        recyclerAdapter=new SongsAdapter(musicList,musicAlbum,musicDuration,context);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

    }

    @Override
    public void onResume() {
        super.onResume();
        recyclerView.setAdapter(recyclerAdapter);
    }

    private void intWidgets(View view)
    {
         recyclerView =view.findViewById(R.id.songs_recycler);


    }

   /* private void addMusicFrom(String dirPath)
    {
        final File musicDir=new File(dirPath);
        if(!musicDir.exists())
        {
            musicDir.mkdir();
            return;
        }

        final File[] files=musicDir.listFiles();
        for(File file:files)
        {
            final String path=file.getAbsolutePath();
            if(path.endsWith(".mp3"))
            {
                musicList.add(path);
            }
        }

    }
*/
}