package com.example.myapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.example.myapplication.sqlite.UserDatabase;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> implements Filterable {



    private Context mcontext;
    private ArrayList<Anime> anime;
    List<Anime> animeOld;
    private ViewBinderHelper viewBinderHelper = new ViewBinderHelper();

    public RecyclerViewAdapter(Context mcontext, ArrayList<Anime> mData) {
        this.mcontext = mcontext;
        this.anime = mData;
        animeOld = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        view = inflater.inflate(R.layout.anime_row_item, parent, false);

        MyViewHolder viewHolder = new MyViewHolder(view);

        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
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
        holder.tv_name.setText(anime.get(position).getName());
        holder.tv_rating.setText(anime.get(position).getRating());
        holder.tv_studio.setText(anime.get(position).getStudio());
        holder.tv_category.setText(anime.get(position).getCategorie());

        Glide.with(mcontext).load(anime.get(position).getImage_url()).error(R.drawable.err).into(holder.img_thumbnail);
        viewBinderHelper.bind(holder.revealLayout, anime.get(position).getId());

        holder.view_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = anime.get(position).getName();
                String desciption = anime.get(position).getDescription();
                String studio = anime.get(position).getStudio();

                String rating = anime.get(position).getRating();
                String categories = anime.get(position).getCategorie();
                String img = anime.get(position).getImage_url();
                String idchap = anime.get(position).getIdchap();

                FavoriteAnime favoriteAnime =new FavoriteAnime(name,desciption,studio,categories,rating,img,idchap);

                if (isAniExist(favoriteAnime)){
                    Toast.makeText(mcontext,"da ton tai",Toast.LENGTH_LONG).show();
                    return;
                }
                UserDatabase.getInstance(mcontext).userDAO().insertUser(favoriteAnime);
                Toast.makeText(mcontext,"thanh cong",Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return anime.size();
    }
    private boolean isAniExist(FavoriteAnime favoriteAnimes) {
        List<FavoriteAnime> favoriteAnimess = UserDatabase.getInstance(mcontext).userDAO().checkUser(favoriteAnimes.getName());
        return favoriteAnimes!= null && !favoriteAnimess.isEmpty();
    }
    

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_rating, tv_studio, tv_category;
        ImageView img_thumbnail;
        LinearLayout view_container, view_favorite;
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

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String searchView = constraint.toString();
                if (searchView.isEmpty()) {
                    anime = (ArrayList<Anime>) animeOld;
                } else {
                    List<Anime> anime1 = new ArrayList<>();
                    for (Anime anime : animeOld) {
                        if (anime.getName().toLowerCase().contains(searchView.toLowerCase())) {
                            anime1.add(anime);
                        }
                    }
                    anime = (ArrayList<Anime>) anime1;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = anime;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                anime = (ArrayList<Anime>) results.values;
                notifyDataSetChanged();
            }
        };
    }

}
