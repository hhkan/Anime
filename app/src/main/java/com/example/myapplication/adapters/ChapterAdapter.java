package com.example.myapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.activitys.ReadActivity;
import com.example.myapplication.model.Anime;
import com.example.myapplication.model.Chapter;

import java.util.ArrayList;
import java.util.List;

public class ChapterAdapter extends BaseAdapter implements Filterable {
    private Context mcontext;
    private ArrayList<Chapter> chapters;
    List<Chapter> chaptersOld;
    TextView tvTenChap;

    public ChapterAdapter(Context mcontext, ArrayList<Chapter> chapters) {
        this.mcontext = mcontext;
        this.chapters = chapters;
        this.chaptersOld = chapters;
    }

    @Override
    public int getCount() {
        return chapters.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.item_chapter, null);

        Chapter chapter = this.chapters.get(position);
        tvTenChap = convertView.findViewById(R.id.tv_item_tenchap);
        tvTenChap.setText(chapter.getTenChap());

        return convertView;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String searchView = constraint.toString();
                if (searchView.isEmpty()) {
                    chapters = (ArrayList<Chapter>) chaptersOld;
                } else {
                    List<Chapter> chapters1 = new ArrayList<>();
                    for (Chapter chapter : chaptersOld) {
                        if (chapter.getTenChap().toLowerCase().contains(searchView.toLowerCase())) {
                            chapters1.add(chapter);
                        }
                    }
                    chapters = (ArrayList<Chapter>) chapters1;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = chapters;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                chapters = (ArrayList<Chapter>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
