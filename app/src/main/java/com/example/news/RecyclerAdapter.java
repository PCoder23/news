package com.example.news;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
    Context context;
    ArrayList<NewsData> data;
    RecyclerAdapter(Context context,ArrayList<NewsData> data){
        this.context=context;
        this.data=data;
    }
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.news_card,parent,false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        NewsData CurData=data.get(position);
    holder.title.setText(CurData.getTitle());
    holder.date.setText(CurData.getDate());
    holder.source.setText(CurData.getSource());
    Picasso.get().load(CurData.getImageUrl()).into(holder.img);
    holder.readMore.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            holder.readMore.setTextColor(Color.parseColor("#FFFFFFFF"));
            Intent intent =new Intent(context,DetailedNews.class);
            intent.putExtra("url",CurData.getUrl());
            context.startActivity(intent);
        }
    });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView source;
        TextView title;
        TextView date;
        TextView readMore;
        public RecyclerViewHolder(@NonNull View view) {
            super(view);
            this.img= view.findViewById(R.id.imgView);
            this.source=view.findViewById(R.id.source);
            this.date=view.findViewById(R.id.date);
            this.title=view.findViewById(R.id.title1);
            this.readMore=view.findViewById(R.id.readMore);

        }
    }
}
