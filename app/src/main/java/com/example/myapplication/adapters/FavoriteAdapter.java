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
import com.example.myapplication.activitys.FavolistActivity;
import com.example.myapplication.model.Anime;
import com.example.myapplication.model.FavoriteAnime;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.MyViewHolder> {
    private Context mcontext;
    private List<FavoriteAnime> anime;

    public FavoriteAdapter() {
        this.mcontext = mcontext;
    }


    public void setData(List<FavoriteAnime> mData) {
        this.anime = mData;
        notifyDataSetChanged();
    }

    private static ViewBinderHelper viewBinderHelper = new ViewBinderHelper();

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.anime_row_item,parent,false);

        MyViewHolder viewHolder = new MyViewHolder(view);

//        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(mcontext, AnimeActivity.class);
//                i.putExtra("ani_name", anime.get(viewHolder.getAdapterPosition()).getName());
//                i.putExtra("ani_description", anime.get(viewHolder.getAdapterPosition()).getDescription());
//                i.putExtra("ani_studio", anime.get(viewHolder.getAdapterPosition()).getStudio());
//                i.putExtra("ani_categorie", anime.get(viewHolder.getAdapterPosition()).getCategorie());
//                i.putExtra("ani_nb_episode", anime.get(viewHolder.getAdapterPosition()).getNb_episode());
//                i.putExtra("ani_rating", anime.get(viewHolder.getAdapterPosition()).getRating());
//                i.putExtra("ani_img", anime.get(viewHolder.getAdapterPosition()).getImage_url());
//                i.putExtra("idchap", anime.get(viewHolder.getAdapterPosition()).getIdchap());
//                mcontext.startActivity(i);
//            }
//        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_name.setText(anime.get(position).getName());
        holder.tv_rating.setText(anime.get(position).getRating());
        holder.tv_studio.setText(anime.get(position).getStudio());
        holder.tv_category.setText(anime.get(position).getCategorie());

//        Glide.with(mcontext).load(anime.get(position).getImage_url()).into(holder.img_thumbnail);

        viewBinderHelper.bind(holder.revealLayout, String.valueOf(anime.get(position).getId()));
        holder.view_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return anime.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_rating, tv_studio, tv_category;
        ImageView img_thumbnail;
        LinearLayout view_container,view_favorite;
        SwipeRevealLayout revealLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            view_favorite = itemView.findViewById(R.id.ln_favorite);
            view_container = itemView.findViewById(R.id.container);
            tv_name = itemView.findViewById(R.id.anime_name);
            tv_category = itemView.findViewById(R.id.categorie);
            tv_rating = itemView.findViewById(R.id.rating);
            tv_studio = itemView.findViewById(R.id.studio);
            img_thumbnail = itemView.findViewById(R.id.thumbnail);
            revealLayout = itemView.findViewById(R.id.swipereveallayout);
        }
    }



}
