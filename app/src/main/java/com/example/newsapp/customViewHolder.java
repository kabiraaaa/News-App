package com.example.newsapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;

public class customViewHolder extends RecyclerView.ViewHolder {
    TextView text_publishedat, text_publushedby, text_headline, text_description;
    ImageView img_news;
    CardView cardView;
    public customViewHolder(@NonNull View itemView) {
        super(itemView);

        text_publishedat = itemView.findViewById(R.id.newspubtime);
        text_publushedby = itemView.findViewById(R.id.newspublisher);
        text_headline = itemView.findViewById(R.id.newsheadline);
        text_description = itemView.findViewById(R.id.newsdesc);
        img_news = itemView.findViewById(R.id.newspic);
        cardView = itemView.findViewById(R.id.mainContainer);

    }
}
