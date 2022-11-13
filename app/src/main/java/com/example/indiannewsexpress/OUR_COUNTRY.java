package com.example.indiannewsexpress;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.indiannewsexpress.models.NewsApiResponse;
import com.example.indiannewsexpress.models.NewsHeadlines;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OUR_COUNTRY extends Fragment {
    RecyclerView ourcountry;
    CustomAdapter adapter;
    ArrayList<NewsHeadlines> newsHeadlines;

    //news related to our countery page

    public OUR_COUNTRY() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_o_u_r__c_o_u_n_t_r_y, container, false);
        ourcountry = view.findViewById(R.id.ourcounteryrecycler);
        newsHeadlines =new ArrayList<>();
        ourcountry.setHasFixedSize(true);
        ourcountry.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter =new CustomAdapter(getContext(),newsHeadlines);
        ourcountry.setAdapter(adapter);

        getnews();
        return view;

    }

    void getnews()
    {
        apputilities.getAppinterface().getNews("in",100,"05e52b05f07a45528a86275897f4ebdc").enqueue(new Callback<NewsApiResponse>() {
            @Override
            public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                if(response.isSuccessful())
                {
                    newsHeadlines.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<NewsApiResponse> call, Throwable t) {

            }
        });
    }



}