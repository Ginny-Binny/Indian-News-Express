package com.example.indiannewsexpress;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.chootdev.csnackbar.Align;
import com.chootdev.csnackbar.Duration;
import com.chootdev.csnackbar.Snackbar;
import com.chootdev.csnackbar.Type;
import com.example.indiannewsexpress.models.NewsHeadlines;
import com.example.indiannewsexpress.models.savedata;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    private Context context;
    private List<NewsHeadlines> headlines;
    private Fragment currentFragment;
    //adapter to show news in the recyclerview

    public CustomAdapter(Context context, Fragment currentFragment, List<NewsHeadlines> headlines) {
        this.context = context;
        this.currentFragment = currentFragment;
        this.headlines = headlines;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.news_row, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        String title, imagelink, newslink, discription, source, author, time;
        title = headlines.get(position).getTitle();
        imagelink = headlines.get(position).getUrlToImage();
        newslink = headlines.get(position).getUrl();
        discription = headlines.get(position).getDescription();
        source = headlines.get(position).getSource().getName();
        author = headlines.get(position).getAuthor();
        time = headlines.get(position).getPublishedAt();


        holder.text_title.setText(headlines.get(position).getTitle());
        holder.text_source.setText(headlines.get(position).getSource().getName() + " , " +
                headlines.get(position).getPublishedAt());
        holder.discription.setText(headlines.get(position).getDescription());


        if (headlines.get(position).getUrlToImage() != null) {
            Picasso.get().load(headlines.get(position).getUrlToImage()).into(holder.image_headline);
        }

        String sanitizedString = title
                .replaceAll("\\.", "")
                .replaceAll("#", "")
                .replaceAll("\\[", "")
                .replaceAll("]", "");

        holder.savenews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                savedata savedData = new savedata(title, imagelink, newslink, discription, source, author, time);

                Log.i("SAVED DATA", savedData.toString());

                DatabaseReference users = FirebaseDatabase.getInstance().getReference("users");

                try {

                    String currentUserInstance = FirebaseAuth.getInstance().getUid();

                    Log.i("CURRENT USER", currentUserInstance);

                    // if this fails then we go to the catch block
                    assert currentUserInstance != null;

                    users.child(currentUserInstance).child("saved news")
                            .child(sanitizedString)
                            .setValue(savedData).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Snackbar.with(context, null)
                                            .type(Type.SUCCESS)
                                            .message("news saved successfully")
                                            .duration(Duration.LONG)
                                            .fillParent(true)
                                            .textAlign(Align.LEFT)
                                            .show();
                                }
                            });
                } catch (Exception e) {
                    Log.e("FIREBASE AUTH ERROR", e.getMessage());
                    // redirect to login page
                    Snackbar.with(context, null)
                            .type(Type.ERROR)
                            .message("Please log in again")
                            .duration(Duration.LONG)
                            .fillParent(true)
                            .textAlign(Align.LEFT)
                            .show();
                    FragmentManager fragmentManager = currentFragment.getActivity()
                            .getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.maincontainer, new LOGIN());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return headlines.size();
    }
}
