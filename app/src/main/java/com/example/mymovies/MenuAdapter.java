package com.example.mymovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MenuAdapter extends BaseAdapter {

    Context context;
    String[] title;
    //ArrayList title = new ArrayList();
    String[] view;
    String[] date;
    String[] pos;
    LayoutInflater inflater;

    public MenuAdapter(Context context, String[] title, String[] view, String[] date, String[] pos) {
        this.context = context;
        this.title = title;
        this.view = view;
        this.date = date;
        this.pos = pos;
        this.inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return title.length;
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
    public View getView(int i, View v, ViewGroup parent) {

        v = inflater.inflate(R.layout.menu_list, null);
        TextView mvTitle = (TextView) v.findViewById(R.id.llTitle);
        TextView mvView = (TextView) v.findViewById(R.id.llView);
        TextView mvDate = (TextView) v.findViewById(R.id.llDate);
        ImageView mvImage = (ImageView) v.findViewById(R.id.llImage);

        mvTitle.setText(title[i]);
        mvView.setText(view[i]);
        mvDate.setText(date[i]);
        Glide.with(context).load(pos.toString()).into(mvImage);

        return v;
    }
}
