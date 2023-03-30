package com.example.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.models.newsheadlines;
import com.squareup.picasso.Picasso;

import java.util.List;

public class customAdapter extends RecyclerView.Adapter<customViewHolder> {
    private Context context;
    private List<newsheadlines> newsheadlines;

    public customAdapter(Context context, List<com.example.newsapp.models.newsheadlines> newsheadlines) {
        this.context = context;
        this.newsheadlines = newsheadlines;
    }

    @NonNull
    @Override
    public customViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new customViewHolder(LayoutInflater.from(context).inflate(R.layout.news_home, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull customViewHolder holder, int position) {

        holder.text_publishedat.setText(newsheadlines.get(position).getAuthor());
        holder.text_publushedby.setText(newsheadlines.get(position).getSource().getNamr());
        holder.text_headline.setText(newsheadlines.get(position).getTitle());
        holder.text_description.setText(newsheadlines.get(position).getDescription());

        if (newsheadlines.get(position).getUrlToImage()!=null){
            Picasso.get().load(newsheadlines.get(position).getUrlToImage()).into(holder.img_news);
        }

    }

    @Override
    public int getItemCount() {
        return newsheadlines.size();
    }
}
