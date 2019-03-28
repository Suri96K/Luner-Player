package com.example.a.myapp;

import android.content.Context;
import android.os.Build;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.a.myapp.Album;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private Context context;
    private List<Album> albumList;
    private LayoutInflater inflater;
    private com.example.a.myapp.RecyclerViewItemClickListener recyclerViewItemClickListener;
    private String filter;

    public String getFilter() {

      
        return filter;
    }


    //ViewHolder class
    //TextView and ImageView holders are binded with relevant views in item of recyclerview.
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView albumName;
        public TextView artistName;
        public ImageView albumCoverImage;
        public int position=0;
        public ViewHolder(View v) {
            super(v);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //When item view is clicked, trigger the itemclicklistener
                    //Because that itemclicklistener is indicated in MainActivity
                    recyclerViewItemClickListener.onItemClick(v,position);
                }
            });
            v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //When item view is clicked long, trigger the itemclicklistener
                    //Because that itemclicklistener is indicated in MainActivity
                    recyclerViewItemClickListener.onItemLongClick(v,position);
                    return true;
                }
            });

            albumName=(TextView)v.findViewById(R.id.album_name);
            artistName=(TextView)v.findViewById(R.id.artist_name);
            albumCoverImage=(ImageView)v.findViewById(R.id.album_cover);
        }
    }

    //Set method of OnItemClickListener object
    public void setOnItemClickListener(RecyclerViewItemClickListener recyclerViewItemClickListener){
        this.recyclerViewItemClickListener=recyclerViewItemClickListener;
    }


    //Constructor of RecyclerViewAdapter
    //It obtains model list coming from MainActivity here
     public RecyclerViewAdapter(Context context,List<Album> albumList) {
        this.context=context;
        this.albumList=albumList;
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    //Adapter request a new item view
    //Create and return it.
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.album_card,parent,false);


        return new ViewHolder(v);
    }

    //Last step before item is placed in recyclerview
    //TextViews and ImageView in viewholder which is attached to view is set with datas in model list
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.position=position;
        holder.albumName.setText(albumList.get(position).getAlbumName());
        holder.artistName.setText(albumList.get(position).getArtistName());
        setImageViewBackgroundWithADrawable(holder.albumCoverImage, albumList.get(position).getAlbumCoverDrawableId());
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public void setFilter(List<Album> listitem)
    {

        albumList=new ArrayList<>();
        albumList.addAll(listitem);
        notifyDataSetChanged();
    }

    //setBackground method is different for some android versions.
    public void setImageViewBackgroundWithADrawable(ImageView image,int drawable){
        if(Build.VERSION.SDK_INT >=22){
            image.setBackground(context.getResources().getDrawable(drawable, null));
        }
        else if(Build.VERSION.SDK_INT >= 16){
            image.setBackground(context.getResources().getDrawable(drawable));
        }else{
            image.setBackgroundDrawable(context.getResources().getDrawable(drawable));
        }
    }

}
