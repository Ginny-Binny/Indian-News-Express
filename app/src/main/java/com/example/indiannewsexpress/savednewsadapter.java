package com.example.indiannewsexpress;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.indiannewsexpress.models.savedata;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class savednewsadapter extends RecyclerView.Adapter<savednewsadapter.Holder> {
    Context context;
    ArrayList<savedata> list;

    public savednewsadapter(Context context, ArrayList<savedata> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public savednewsadapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(context).inflate(R.layout.news_row_savednews,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull savednewsadapter.Holder holder, int position) {
        savedata savedata=list.get(position);
        holder.text_source.setText(savedata.getSource()+" , "+savedata.getTime());
        holder.text_title.setText(savedata.getTitle());
        holder.discription.setText(savedata.getDiscription());
        Picasso.get().load(savedata.getImagelink()).into(holder.image_headline);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public  static class Holder extends RecyclerView.ViewHolder
    {

        TextView text_title,text_source,discription;
        ImageView image_headline;
        CardView cardview;

        public Holder(@NonNull View itemView) {
            super(itemView);
            text_title=itemView.findViewById(R.id.newstitle2);
            text_source=itemView.findViewById(R.id.newssource2);
            image_headline=itemView.findViewById(R.id.newsimage2);
            cardview=itemView.findViewById(R.id.cardbtn2);

            discription=itemView.findViewById(R.id.newssiscription2);
        }
    }


}
