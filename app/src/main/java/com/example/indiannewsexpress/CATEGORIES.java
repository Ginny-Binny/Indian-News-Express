package com.example.indiannewsexpress;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


public class CATEGORIES extends Fragment {
    CardView general,entertainment,health,science,technology,sports,buisness;

    //for the categories page visible in homepage


    public CATEGORIES() {
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
       View view =inflater.inflate(R.layout.fragment_c_a_t_e_g_o_r_i_e_s, container, false);
       general=view.findViewById(R.id.general);
       entertainment=view.findViewById(R.id.entertainment);
       buisness=view.findViewById(R.id.buisness);
       health=view.findViewById(R.id.health);
       science=view.findViewById(R.id.science);
       technology=view.findViewById(R.id.technology);
       sports=view.findViewById(R.id.sports);


               general.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.cardcontainer, new GENERAL()).commit();
               //op
               Toast.makeText(getContext(), "button", Toast.LENGTH_SHORT).show();
           }
       });
        entertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.cardcontainer, new ENTERTAINMENT()).commit();
            }
        });
        buisness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.cardcontainer, new BUISNESS()).commit();
            }
        });
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.cardcontainer, new HEALTH()).commit();
            }
        });
        science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.cardcontainer, new SCIENCE()).commit();
            }
        });
        technology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.cardcontainer, new TECHNOLOGY()).commit();
            }
        });
        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.cardcontainer, new SPORTS()).commit();
            }
        });
       return view;
    }
}