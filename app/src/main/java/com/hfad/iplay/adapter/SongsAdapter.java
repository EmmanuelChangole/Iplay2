package com.hfad.iplay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hfad.iplay.R;

import java.util.ArrayList;

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.ViewHolder>
{
    ArrayList<String> musicList;
    ArrayList<String> albumList;
    ArrayList <String> duration;
    Context context;

    public SongsAdapter(ArrayList<String> musicList, ArrayList<String> albumList, ArrayList<String> duration, Context context) {
        this.musicList = musicList;
        this.albumList = albumList;
        this.duration = duration;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.items,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.tvMusic.setText(musicList.get(position));
        holder.tvArtist.setText(albumList.get(position));
        holder.tvTime.setText(duration.get(position));

    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvMusic,tvArtist,tvTime;
        ImageView albumArt;
        RelativeLayout rootLayout;


        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            tvMusic=itemView.findViewById(R.id.myItem);
            tvArtist=itemView.findViewById(R.id.itemArtist);
            tvTime=itemView.findViewById(R.id.timeIndicator);
            albumArt=itemView.findViewById(R.id.albumArt);

        }

}

}
