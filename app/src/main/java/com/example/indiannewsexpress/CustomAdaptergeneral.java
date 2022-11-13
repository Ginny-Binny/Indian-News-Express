package com.example.indiannewsexpress;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chootdev.csnackbar.Align;
import com.chootdev.csnackbar.Duration;
import com.chootdev.csnackbar.Snackbar;
import com.chootdev.csnackbar.Type;
import com.example.indiannewsexpress.models.NewsHeadlines;
import com.example.indiannewsexpress.models.savedata;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdaptergeneral extends RecyclerView.Adapter<CustomViewHolder> {
    private Context context;
    private List<NewsHeadlines> headlines;
    //adapter to show saved newses
    public CustomAdaptergeneral(Context context, List<NewsHeadlines> headlines) {
        this.context = context;
        this.headlines = headlines;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.news_row,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        String title,imagelink,newslink,discription,source,author,time;
        title=headlines.get(position).getTitle();
        imagelink=headlines.get(position).getUrlToImage();
        newslink=headlines.get(position).getUrl() ;
        discription=headlines.get(position).getDescription();
        source=headlines.get(position).getSource().getName();
        author=headlines.get(position).getAuthor();
        time=headlines.get(position).getPublishedAt();


        holder.text_title.setText(headlines.get(position).getTitle());
        holder.text_source.setText(headlines.get(position).getSource().getName()+" , "+headlines.get(position).getPublishedAt());
        holder.discription.setText(headlines.get(position).getDescription());


        if(headlines.get(position).getUrlToImage()!=null)
        {
            Picasso.get().load(headlines.get(position).getUrlToImage()).into(holder.image_headline);
        }
        holder.savenews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                savedata savedata=new savedata(title,imagelink,newslink,discription,source,author,time);
                FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getUid()).child("saved news").child(headlines.get(position).getTitle()).setValue(savedata).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Snackbar.with(context,null)
                                .type(Type.SUCCESS)
                                .message("news saved successfully")
                                .duration(Duration.LONG)
                                .fillParent(true)
                                .textAlign(Align.LEFT)
                                .show();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {

        return headlines.size();
    }
}
