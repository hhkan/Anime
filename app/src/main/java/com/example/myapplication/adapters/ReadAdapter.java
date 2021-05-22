package com.example.myapplication.adapters;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.activitys.ListCategoriesActivity;
import com.example.myapplication.activitys.ReadActivity;
import com.example.myapplication.model.Read;


import java.util.ArrayList;

import me.biubiubiu.justifytext.library.JustifyTextView;


public class ReadAdapter extends RecyclerView.Adapter<ReadAdapter.ViewHolder> {
    private Context context;
    private String reads;

    public ReadAdapter(String reads,Context context ) {
        this.reads = reads;
        this.context = context;

    }


    public void setReadss(String readss) {
        this.reads = readss;
        notifyDataSetChanged();
    }
    public String getRead() {
        return reads;
    }
//    public ReadAdapter(String read, Context applicationContext) {
//    }

//    public void setRead(String reads) {
//        this.reads = reads;
//        notifyDataSetChanged();
//    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.item_read,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReadAdapter.ViewHolder holder, int position) {
        String read = reads;
        holder.tvRead.setText(read);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        JustifyTextView tvRead;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRead = itemView.findViewById(R.id.tv_read);
        }
    }



}
