package com.example.myapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.activitys.ListCategoriesActivity;
import com.example.myapplication.model.Anime;
import com.example.myapplication.model.Categories;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>  {
    private Context context;
    private ArrayList<Categories> categories;

    public CategoryAdapter(Context context, ArrayList<Categories> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.item_category,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ListCategoriesActivity.class);
                i.putExtra("name_categories", categories.get(viewHolder.getAdapterPosition()).getStudio());
                i.putExtra("id_categories",categories.get(viewHolder.getAdapterPosition()).getIdCategory());
                context.startActivity(i);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_categories.setText(categories.get(position).getStudio());

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_categories;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_categories =itemView.findViewById(R.id.tv_item_category);
            linearLayout = itemView.findViewById(R.id.ln_item_categories);
        }
    }
}
