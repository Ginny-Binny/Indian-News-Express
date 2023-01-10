package com.example.indiannewsexpress;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.indiannewsexpress.models.NewsApiResponse;
import com.example.indiannewsexpress.models.NewsHeadlines;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GENERAL extends Fragment {
    RecyclerView ourcountry;
    CustomAdapter adapter;
    ArrayList<NewsHeadlines> newsHeadlines;
    //set news in recyclerview foe general categories


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_g_e_n_e_r_a_l, container, false);
        ourcountry = view.findViewById(R.id.generalrecycler);
        newsHeadlines =new ArrayList<>();
        ourcountry.setHasFixedSize(true);
        ourcountry.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter =new CustomAdapter(getContext(), this, newsHeadlines);
        ourcountry.setAdapter(adapter);

        getnews();
        requireActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).replace(R.id.cardcontainer,new CATEGORIES()).commit();
            }
        });
        return view;

    }

    void getnews()
    {
        apputilities.getAppinterface().getNews("in","general",100,"05e52b05f07a45528a86275897f4ebdc").enqueue(new Callback<NewsApiResponse>() {
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