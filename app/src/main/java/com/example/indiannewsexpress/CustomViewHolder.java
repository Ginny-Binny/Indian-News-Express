package com.example.indiannewsexpress;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder extends RecyclerView.ViewHolder {
    TextView text_title,text_source,discription;
    ImageView image_headline;
    CardView cardview,savenews;

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        text_title=itemView.findViewById(R.id.newstitle);
        text_source=itemView.findViewById(R.id.newssource);
        image_headline=itemView.findViewById(R.id.newsimage);
        cardview=itemView.findViewById(R.id.cardbtn);
        savenews=itemView.findViewById(R.id.save_news);
        discription=itemView.findViewById(R.id.newssiscription);
    }
}
