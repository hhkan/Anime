package com.example.myapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.myapplication.R;
import com.example.myapplication.activitys.AnimeActivity;
import com.example.myapplication.model.Anime;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter2 extends RecyclerView.Adapter<RecyclerViewAdapter2.MyViewHolder>  {

    private Context mcontext;
    private ArrayList<Anime> anime;

    public RecyclerViewAdapter2(Context mcontext, ArrayList<Anime> mData) {
        this.mcontext = mcontext;
        this.anime = mData;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        view = inflater.inflate(R.layout.anime_row_tiem2, parent, false);

        MyViewHolder viewHolder = new MyViewHolder(view);

        viewHolder.view_container2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position;
                Intent i = new Intent(mcontext, AnimeActivity.class);
                i.putExtra("ani_name", anime.get(viewHolder.getAdapterPosition()).getName());
                i.putExtra("ani_description", anime.get(viewHolder.getAdapterPosition()).getDescription());
                i.putExtra("ani_studio", anime.get(viewHolder.getAdapterPosition()).getStudio());
                i.putExtra("ani_categorie", anime.get(viewHolder.getAdapterPosition()).getCategorie());
                i.putExtra("ani_nb_episode", anime.get(viewHolder.getAdapterPosition()).getNb_episode());
                i.putExtra("ani_rating", anime.get(viewHolder.getAdapterPosition()).getRating());
                i.putExtra("ani_img", anime.get(viewHolder.getAdapterPosition()).getImage_url());
                i.putExtra("idchap", anime.get(viewHolder.getAdapterPosition()).getIdchap());
                mcontext.startActivity(i);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_name2.setText(anime.get(position).getName());
        holder.tv_studio2.setText(anime.get(position).getStudio());

        Glide.with(mcontext).load(anime.get(position).getImage_url()).error(R.drawable.err).into(holder.img_thumbnail2);


    }

    @Override
    public int getItemCount() {
        return anime.size();
    }

    

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name2, tv_studio2;
        ImageView img_thumbnail2;
        LinearLayout view_container2;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            view_container2 = itemView.findViewById(R.id.container2);
            tv_name2 = itemView.findViewById(R.id.anime_name2);
            tv_studio2 = itemView.findViewById(R.id.studio2);
            img_thumbnail2 = itemView.findViewById(R.id.thumbnail2);

        }
    }



}
