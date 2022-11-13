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


public class ENTERTAINMENT extends Fragment {
    //set news in recyclerview foe entertainment categories

    RecyclerView ourcountry;
    CustomAdapter adapter;
    ArrayList<NewsHeadlines> newsHeadlines;


    public ENTERTAINMENT() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_e_n_t_e_r_t_a_i_n_m_e_n_t, container, false);
        ourcountry = view.findViewById(R.id.entertainmentrecycler);
        newsHeadlines =new ArrayList<>();
        ourcountry.setHasFixedSize(true);
        ourcountry.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter =new CustomAdapter(getContext(),newsHeadlines);
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
        apputilities.getAppinterface().getNews("in","entertainment",100,"05e52b05f07a45528a86275897f4ebdc").enqueue(new Callback<NewsApiResponse>() {
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